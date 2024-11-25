package academy.devdojo.repository;

import academy.devdojo.domain.Anime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class AnimeData {
  private final List<Anime> animes = new ArrayList<>();

  {
    var now = LocalDateTime.now();
    animes.addAll(List.of(new Anime(1L, "One piece", now), new Anime(2L, "Dbz", now), new Anime(3L, "Jujustu kaisen", now)));
  }
}
