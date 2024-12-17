package com.example.lab2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, String> {
    List<Song> findByArtist(Artist artist);
    Optional<Song> findByName(String name);
    List<Song> findByArtistId(String artistId);
}
