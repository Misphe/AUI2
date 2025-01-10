package com.example.lab2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, String> {
    Optional<Artist> findByName(String name);

    @Query("SELECT a FROM Artist a JOIN a.songs s WHERE s = :song")
    Artist findBySong(@Param("song") Song song);
}
