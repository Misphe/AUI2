package com.example.lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ApplicationsCommandLineRunner implements CommandLineRunner {

    private final ArtistService artistService;
    private final SongService songService;

    @Autowired
    public ApplicationsCommandLineRunner(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        System.out.println("app started");

        while (running) {
            System.out.println("Commands:");
            System.out.println("1. List all artists");
            System.out.println("2. List all songs");
            System.out.println("3. Add a new song");
            System.out.println("4. Delete an existing song");
            System.out.println("5. Exit");
            System.out.print("Enter command number:");

            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    listAllArtists();
                    break;
                case "2":
                    listAllSongs();
                    break;
                case "3":
                    addNewSong(scanner);
                    break;
                case "4":
                    deleteSong(scanner);
                    break;
                case "5":
                    running = false;
                    System.out.println("exiting");
                    break;
                default:
                    System.out.println("invalid command");
                    break;
            }
        }
        scanner.close();
    }

    private void listAllArtists() {
        List<Artist> artists = artistService.getAllArtists();
        if (artists.isEmpty()) {
            System.out.println("no artists found");
        } else {
            System.out.println("\nArtists:");
            for (int i=1; i<= artists.size(); i++) {
                Artist artist = artists.get(i-1);
                System.out.println(i + " - " + artist.getName() + " (" + artist.getFollowers() + " followers)");
            }
        }
    }

    private void listAllSongs() {
        List<Song> songs = songService.getAllSongs();
        if (songs.isEmpty()) {
            System.out.println("no songs found");
        } else {
            System.out.println("\nSongs:");
            for (int i=1; i <= songs.size(); i++) {
                Song song = songs.get(i-1);
                System.out.println(i + " - " + song.getName() + " by " +
                        artistService.getArtistBySong(song).getName() +
                        " (" + song.getLength() + " seconds)");
            }
        }
    }

    private void addNewSong(Scanner scanner) {
        System.out.println("\nEnter song details:");
        System.out.print("Song name:");
        String songName = scanner.nextLine();
        System.out.print("Song length (seconds): ");
        int songLength = Integer.parseInt(scanner.nextLine());

        List<Artist> artists = artistService.getAllArtists();
        if (artists.isEmpty()) {
            System.out.println("no artists to add to");
            return;
        }

        System.out.println("\nSelect an artist:");
        for (int i = 0; i < artists.size(); i++) {
            System.out.println((i + 1) + ". " + artists.get(i).getName());
        }

        int artistChoice;
        while (true) {
            System.out.print("Enter artist number:");
            artistChoice = Integer.parseInt(scanner.nextLine()) - 1;
            if (artistChoice >= 0 && artistChoice < artists.size()) {
                break;
            }
            System.out.println("invalid choice");
        }

        Artist selectedArtist = artists.get(artistChoice);
        Song newSong = new Song.SongBuilder()
                .name(songName)
                .length(songLength)
                .artist(selectedArtist)
                .build();

        songService.createSong(newSong);
        System.out.println("song added successfully");
    }

    private void deleteSong(Scanner scanner) {
        List<Song> songs = songService.getAllSongs();
        if (songs.isEmpty()) {
            System.out.println("no songs to delete");
            return;
        }

        System.out.println("\nSelect a song to delete:");
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i).getName() + " by " + artistService.getArtistBySong(songs.get(i)).getName());
        }

        int songChoice;
        while (true) {
            System.out.print("Enter song number:");
            songChoice = Integer.parseInt(scanner.nextLine()) - 1;
            if (songChoice >= 0 && songChoice < songs.size()) {
                break;
            }
            System.out.println("invalid choice");
        }

        Song selectedSong = songs.get(songChoice);
        songService.deleteSongById(selectedSong.getId());
        System.out.println("song deleted successfully");
    }
}
