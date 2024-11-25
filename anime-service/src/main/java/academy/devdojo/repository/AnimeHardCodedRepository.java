package academy.devdojo.repository;

import academy.devdojo.domain.Anime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Getter
@RequiredArgsConstructor
public class AnimeHardCodedRepository {
  private final AnimeData animes;

  public Anime save(Anime anime) {
    animes.getAnimes().add(anime);

    return anime;
  }

  public List<Anime> findAll() {
    return animes.getAnimes();
  }

  public List<Anime> findByName(String name) {
    return animes.getAnimes().stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();
  }

  public Optional<Anime> findById(Long id) {
    return animes.getAnimes().stream().filter(anime -> anime.getId().equals(id)).findFirst();
  }

}
