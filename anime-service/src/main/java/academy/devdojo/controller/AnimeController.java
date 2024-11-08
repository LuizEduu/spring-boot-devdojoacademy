package academy.devdojo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("v1/animes")
public class AnimeController {

    @GetMapping
    public List<String> findAll() {
        return List.of("One piece", "Dragon ball Z", "Jujutsu Kaisen", "Attack on Titan");
    }
}
