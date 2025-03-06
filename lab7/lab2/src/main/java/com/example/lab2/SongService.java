package com.example.lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongService {
    @Autowired
    private final SongRepository songRepository;
    @Autowired
    private final ArtistRepository artistRepository;

    @Autowired
    public SongService(SongRepository songRepository, ArtistRepository artistRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Song getSongById(String id) {
        return songRepository.findById(id).orElse(null);
    }
    public Song getSongByName(String name) {
        return songRepository.findByName(name).orElse(null);
    }

    public List<Song> getSongsByArtist(Artist artist) {
        return songRepository.findByArtist(artist);
    }

    public Song createSong(Song song) {
        return songRepository.save(song);
    }

    public List<SongReadingDto> getSongsByArtistId(String artistId) {
        List<Song> songs = songRepository.findByArtistId(artistId);

        return songs.stream()
                .map(song -> new SongReadingDto(song))
                .collect(Collectors.toList());
    }

    public void deleteSongById(String id) {
        songRepository.deleteById(id);
    }

    public void deleteSong(Song song) {
        songRepository.delete(song);
    }
}
