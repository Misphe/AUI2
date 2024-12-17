package com.example.lab2;

import java.util.Objects;

public class SongReadingDto implements Comparable<SongReadingDto> {
    String name;
    String id;
    String artist;
    int length;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public SongReadingDto() {
    }


    public void setLength(int length) {
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public SongReadingDto(Song song) {
        this.name = song.name;
        this.id = song.getId();
        this.artist = song.artist.getName();
        this.length = song.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SongReadingDto song)) return false;
        return this.id.equals(((SongReadingDto) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public int compareTo(SongReadingDto o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                "id='" + id + '\'' +
                '}';
    }
}
