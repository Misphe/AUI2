package com.example.lab2;

import java.util.Objects;

public class SongDto implements Comparable<SongDto> {
    String name;
    int length;
    String artist;

    public SongDto(Song song) {
        this.name = song.name;
        this.length = song.length;
        this.artist = song.artist.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SongDto songDto)) return false;
        return length == songDto.length && Objects.equals(name, songDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, length);
    }

    @Override
    public int compareTo(SongDto o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", artist=" + artist +
                '}';
    }
}
