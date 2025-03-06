package com.example.lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
public class ExampleDataInitializer {

    private final ArtistService artistService;
    private final SongService songService;

    @Autowired
    public ExampleDataInitializer(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @PostConstruct
    public void init() {

        Artist artist1 = new Artist.ArtistBuilder().name("PRO8L3M").followers(1500000).build();
        Artist artist2 = new Artist.ArtistBuilder().name("Chivas").followers(1300000).build();
        Artist artist3 = new Artist.ArtistBuilder().name("Quebonafide").followers(1700000).build();

        artistService.createArtist(artist1);
        artistService.createArtist(artist2);
        artistService.createArtist(artist3);

        Song song1 = new Song.SongBuilder().name("Skrable").length(186).artist(artist1).build();
        Song song2 = new Song.SongBuilder().name("Interpol").length(154).artist(artist1).build();
        Song song3 = new Song.SongBuilder().name("Koleżanko mojej byłej").length(179).artist(artist2).build();
        Song song4 = new Song.SongBuilder().name("Narcyz").length(204).artist(artist2).build();
        Song song5 = new Song.SongBuilder().name("GAZPROM").length(165).artist(artist3).build();
        Song song6 = new Song.SongBuilder().name("SZUBIENICAPESTYCYDYBRON").length(235).artist(artist3).build();

        songService.createSong(song1);
        songService.createSong(song2);
        songService.createSong(song3);
        songService.createSong(song4);
        songService.createSong(song5);
        songService.createSong(song6);

        System.out.println("ExampleDataInitializer complete");
    }
}
