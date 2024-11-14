package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import academy.devdojo.mapper.AnimeMapper;
import academy.devdojo.request.AnimePostRequest;
import academy.devdojo.response.AnimeGetResponse;
import academy.devdojo.response.AnimePostResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("v1/animes")
@Slf4j
public class AnimeController {
  private static final AnimeMapper MAPPER = AnimeMapper.INSTANCE;

  @GetMapping
  public ResponseEntity<List<AnimeGetResponse>> findAll(@RequestParam(required = false) String name) {
    var animes = Anime.getAnimes();
    List<AnimeGetResponse> animesGetResponse = MAPPER.toAnimeGetResponseList(animes);

    if (name == null) return ResponseEntity.ok(animesGetResponse);

    var animesFiltered = animesGetResponse
            .stream()
            .filter(anime -> anime.getName().equalsIgnoreCase(name))
            .toList();

    return ResponseEntity.ok(animesFiltered);
  }

  @GetMapping("{id}")
  public ResponseEntity<AnimeGetResponse> findById(@PathVariable Long id) {
    var response = Anime.getAnimes().stream().filter(a -> a.getId().equals(id)).findFirst().map(MAPPER::toAnimeGetResponse).orElse(null);

    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest body) {
    var anime = MAPPER.toAnime(body);
    Anime.getAnimes().add(anime);

    var response = MAPPER.toAnimePostResponse(anime);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

}
