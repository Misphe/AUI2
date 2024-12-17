package com.example.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SongCollectionReadingDto  implements Comparable<SongCollectionReadingDto>{
    String artist;
    List<SongDto> songs;

    public SongCollectionReadingDto(List<Song> songs, Artist artist) {
        this.artist = artist.getName();
        this.songs = songs.stream()
                .map(SongDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SongCollectionReadingDto songCollectionReadingDto)) return false;
        return songs.equals(songCollectionReadingDto.songs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songs, artist);
    }

    @Override
    public int compareTo(SongCollectionReadingDto o) {
        return artist.compareTo(o.artist);
    }

    @Override
    public String toString() {
        return "collection of " + artist;
    }
}
