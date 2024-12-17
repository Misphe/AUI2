package com.example.lab2;

public class ArtistCreateUpdateDto {
    private String name;
    private int followers;

    public String getName() {
        return name;
    }

    public ArtistCreateUpdateDto(String name, int followers) {
        this.name = name;
        this.followers = followers;
    }

    public ArtistCreateUpdateDto(Artist a) {
        this.name = a.getName();
        this.followers = a.getFollowers();
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
}