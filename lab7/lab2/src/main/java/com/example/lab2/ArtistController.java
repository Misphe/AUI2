package com.example.lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private SongService songService;

    @GetMapping("/{id}")
    public ResponseEntity<ArtistReadingDto> getArtist(@PathVariable String id) {
        Artist artist = (Artist) artistService.getArtistById(id);
        if (artist == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(artistService.convertToArtistReadingDto(artist));
    }

    @GetMapping
    public ResponseEntity<List<ArtistReadingDto>> getAllArtist() {
        List<Artist> artists = artistService.getAllArtists();
        if (artists == null) {
            return ResponseEntity.notFound().build();
        }
        List<ArtistReadingDto> artistsReadingDto = artists.stream()
                .map(ArtistReadingDto::new)
                .toList();

        return ResponseEntity.ok(artistsReadingDto);
    }

    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody ArtistCreateUpdateDto dto) {
        Artist newArtist = new Artist.ArtistBuilder()
                .name(dto.getName())
                .followers(dto.getFollowers())
                .build();
        return ResponseEntity.ok(artistService.createArtist(newArtist));
    }

    @GetMapping("/{id}/songs")
    public ResponseEntity<List<SongReadingDto>> getAllSongsByArtist(@PathVariable String id) {
        Artist artist = artistService.getArtistById(id);
        if (artist == null) {
            return ResponseEntity.notFound().build();
        }

        List<Song> songs = songService.getSongsByArtist(artist);
        if (songs == null || songs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<SongReadingDto> songDtos = songs.stream()
                .map(SongReadingDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(songDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteArtist(@PathVariable String id) {
        Artist artist = artistService.getArtistById(id);
        if (artist == null) {
            return ResponseEntity.notFound().build();
        }

        artistService.deleteArtist(artist.getId());
        Map<String, String> response = new HashMap<>();
        response.put("message", "artist deleted successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{artistId}")
    public ResponseEntity<ArtistReadingDto> updateArtist(@PathVariable String artistId, @RequestBody ArtistCreateUpdateDto dto) {
        Artist artist = artistService.getArtistById(artistId);
        if (artist == null) {
            return ResponseEntity.notFound().build();
        }

        artist.setName(dto.getName());
        artist.setFollowers(dto.getFollowers());
        Artist updatedArtist = artistService.updateArtist(artist);

        return ResponseEntity.ok(artistService.convertToArtistReadingDto(updatedArtist));
    }
}

