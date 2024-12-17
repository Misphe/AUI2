package com.example.lab2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;
    private final ArtistService artistService;

    public SongController(SongService songService, ArtistService artistService) {
        this.songService = songService;
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<List<SongReadingDto>> getAllSongs() {
        List<Song> songs = songService.getAllSongs();

        if (songs == null) {
            return ResponseEntity.notFound().build();
        }
        List<SongReadingDto> songsReadingDto = songs.stream()
                .map(SongReadingDto::new)
                .toList();

        return ResponseEntity.ok(songsReadingDto);
    }

    @GetMapping("/{songId}")
    public ResponseEntity<SongReadingDto> getSongs(@PathVariable String songId) {
        Song song = songService.getSongById(songId);

        if (song == null) {
            return ResponseEntity.notFound().build();
        }

        SongReadingDto songReadingDto = new SongReadingDto(song);
        return ResponseEntity.ok(songReadingDto);
    }

    @GetMapping("/artist/{artistId}")
    public ResponseEntity<List<SongReadingDto>> getSongsByArtistId(@PathVariable String artistId) {
        List<SongReadingDto> songs = songService.getSongsByArtistId(artistId);

        if (songs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(songs);
        }
    }

    @PostMapping("/{artistId}")
    public ResponseEntity<String> createSong(@PathVariable String artistId, @RequestBody @Valid SongCreateUpdateDto dto) {
        try {
            Artist artist = artistService.getArtistById(artistId);
            if (artist == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "artist not found");

            Song song = new Song.SongBuilder()
                    .name(dto.name)
                    .length(dto.length)
                    .artist(artist)
                    .build();

            songService.createSong(song);

            return ResponseEntity.ok("song created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "error when create song");
        }
    }

    @DeleteMapping("/{songId}")
    public ResponseEntity<Map<String, String>> deleteSong(@PathVariable String songId) {
        try {
            Song song = songService.getSongById(songId);
            if (song == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "song not found");
            }

            songService.deleteSong(song);
            Map<String, String> response = new HashMap<>();
            response.put("message", "artist deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "song not found");
        }
    }

    @PutMapping("/{songId}")
    public ResponseEntity<SongReadingDto> updateSong(@PathVariable String songId, @RequestBody SongCreateUpdateDto dto) {
        Song song = songService.getSongById(songId);
        if (song == null) {
            return ResponseEntity.notFound().build();
        }

        song.setName(dto.getName());
        song.setLength(dto.getLength());
        Song updatedSong = songService.createSong(song);

        return ResponseEntity.ok(new SongReadingDto(updatedSong));
    }

}