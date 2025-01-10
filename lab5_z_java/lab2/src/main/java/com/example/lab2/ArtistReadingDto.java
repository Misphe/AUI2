package com.example.lab2;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ArtistReadingDto {
    private String id;
    private String name;
    private int followers;
    private List<String> songs;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFollowers() {
        return followers;
    }

    public List<String> getSongs() {
        return songs;
    }

    public ArtistReadingDto(Artist artist) {
        this.id = artist.getId();
        this.name = artist.getName();
        this.followers = artist.getFollowers();
        this.songs = artist.getSongs().stream()
                .map(Song::getName)
                .collect(Collectors.toList());
    }
}