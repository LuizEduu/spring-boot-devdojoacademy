package academy.devdojo.repository;

import academy.devdojo.domain.Anime;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AnimeHardCodedRepository {
  private static final List<Anime> animes = new ArrayList<>();

  static {
    var now = LocalDateTime.now();
    animes.addAll(List.of(new Anime(1L, "One piece", now), new Anime(2L, "Dbz", now), new Anime(3L, "Jujustu kaisen", now)));
  }

  public Anime save(Anime anime) {
    animes.add(anime);

    return anime;
  }

  public List<Anime> findAll() {
    return animes;
  }

  public List<Anime> findByName(String name) {
    return animes.stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();
  }

  public Optional<Anime> findById(Long id) {
    return animes.stream().filter(anime -> anime.getId().equals(id)).findFirst();
  }

}
