package com.example.lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistReadingDto convertToArtistReadingDto(Artist artist) {
        return new ArtistReadingDto(artist);
    }

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Artist getArtistById(String id) {
        return artistRepository.findById(id).orElse(null);
    }

    public Artist getArtistByName(String name) {
        return artistRepository.findByName(name).orElse(null);
    }

    public Artist getArtistBySong(Song song) {
        return artistRepository.findBySong(song);
    }

    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public void deleteArtist(String id) {
        artistRepository.deleteById(id);
    }

    public Artist updateArtist(Artist artist) {
        return artistRepository.save(artist);
    }
}