package com.example.lab2;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

public class SongCreateUpdateDto implements Comparable<SongCreateUpdateDto> {
    @NotBlank
    String name;
    @Positive
    int length;

    public SongCreateUpdateDto(Song song) {
        this.name = song.name;
        this.length = song.length;
    }

    public SongCreateUpdateDto() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SongDto SongCreateUpdateDto)) return false;
        return length == SongCreateUpdateDto.length && Objects.equals(name, SongCreateUpdateDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, length);
    }

    @Override
    public int compareTo(SongCreateUpdateDto o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", length=" + length +
                '}';
    }
}
