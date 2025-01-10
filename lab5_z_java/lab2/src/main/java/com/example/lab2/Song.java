package com.example.lab2;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "songs")
public class Song implements Comparable<Song>, Serializable {
    public void setName(String name) {
        this.name = name;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Song() {
        this.name = "";
        this.length = 0;
        this.artist = null;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getId() {
        return id;
    }

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private String id = UUID.randomUUID().toString();

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "length", nullable = false)
    int length; // in seconds

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    Artist artist;

    @Override
    public int compareTo(Song o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", artist=" + artist.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song song)) return false;
        return length == song.length && Objects.equals(name, song.name);
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, length);
    }

    private Song(SongBuilder sb) {
        this.name = sb.name;
        this.length = sb.length;
        this.artist = sb.artist;
    }

    public static class SongBuilder {
        String name;
        int length;
        Artist artist;

        public SongBuilder() {
            this.name = null;
            this.length = 0;
            this.artist = null;
        }

        public SongBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SongBuilder length(int length) {
            this.length = length;
            return this;
        }

        public SongBuilder artist(Artist artist) {
            this.artist = artist;
            return this;
        }

        public Song build() {
            return new Song(this);
        }
    }
}
