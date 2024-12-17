package com.example.lab2;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "artists")
public class Artist implements Comparable<Artist>, Serializable {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private String id = UUID.randomUUID().toString();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "followers", nullable = false)
    private int followers;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Song> songs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist artist)) return false;
        return this.id.equals(((Artist) o).id);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, followers);
    }

    @Override
    public String toString() {
        String songsString = "";
        for (int i=0; i<songs.size(); i++) {
            songsString += songs.get(i).toString() + "\n";
        }
        /*return "Artist {\n" +
                "name='" + name + '\'' +
                ", followers=" + followers +
                ", songs:\n" + songsString +
                "}";*/
        return "Artist {\n" +
                "name='" + name + '\'' +
                ",\nfollowers=" + followers +
                "\n}";
    }

    @Override
    public int compareTo(Artist o) {
        return this.id.compareTo(o.id);
    }

    private Artist(ArtistBuilder ab) {
        this.name = ab.name;
        this.followers = ab.followers;
        this.songs = ab.songs;
    }

    public Artist() {
        this.name = "";
        this.followers = 0;
        this.songs = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public static class ArtistBuilder {
        private String name;
        private int followers;
        private List<Song> songs = new ArrayList<>();

        public ArtistBuilder() {
            this.name = null;
            this.followers = 0;
        }

        public ArtistBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ArtistBuilder followers(int followers) {
            this.followers = followers;
            return this;
        }

        public ArtistBuilder song(Song song) {
            if (song != null) {
                this.songs.add(song);
            }
            return this;
        }

        public Artist build() {
            Artist newArtist = new Artist(this);
            for (int i = 0; i<newArtist.songs.size(); i++) {
                if (newArtist.songs.get(i).artist == null) {
                    newArtist.songs.get(i).artist = newArtist;
                }
            }
            return newArtist;
        }
    }
}
