package academy.devdojo.controller;

import academy.devdojo.mapper.AnimeMapper;
import academy.devdojo.request.AnimePostRequest;
import academy.devdojo.response.AnimeGetResponse;
import academy.devdojo.response.AnimePostResponse;
import academy.devdojo.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("v1/animes")
@Slf4j
@RequiredArgsConstructor
public class AnimeController {
  private final AnimeMapper mapper;
  private final AnimeService service;

  @GetMapping
  public ResponseEntity<List<AnimeGetResponse>> findAll(@RequestParam(required = false) String name) {
    var animes = service.findAll(name);

    var animesGetResponse = mapper.toAnimeGetResponseList(animes);

    return ResponseEntity.ok(animesGetResponse);
  }

  @GetMapping("{id}")
  public ResponseEntity<AnimeGetResponse> findById(@PathVariable Long id) {
    var anime = service.findById(id);

    var response = mapper.toAnimeGetResponse(anime);

    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest body) {

    var parsedAnime = mapper.toAnime(body);
    var anime = service.save(parsedAnime);
    var animePostResponse = mapper.toAnimePostResponse(anime);

    return ResponseEntity.status(HttpStatus.CREATED).body(animePostResponse);
  }

}
