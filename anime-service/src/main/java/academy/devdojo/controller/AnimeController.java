package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;

@RestController()
@RequestMapping("v1/animes")
@Slf4j
public class AnimeController {
    @GetMapping
    public List<Anime> findAll(@RequestParam(required = false) String name) {
        if (name == null) {
            return Anime.getAnimes();
        }

        return Anime.getAnimes().stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();
    }

    @GetMapping("{id}")
    public Anime findById(@PathVariable Long id) {
        return Anime.getAnimes().stream().filter(anime -> anime.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping
    public Anime save(@RequestBody Anime body) {
        var id = ThreadLocalRandom.current().nextLong(1000_000);
        var anime = new Anime(id, body.getName());
        Anime.getAnimes().add(anime);

        return anime;
    }

}
