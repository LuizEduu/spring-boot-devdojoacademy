package academy.devdojo.service;

import academy.devdojo.domain.Anime;
import academy.devdojo.repository.AnimeHardCodedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {
  private final AnimeHardCodedRepository repository;

  public Anime save(Anime anime) {
      return repository.save(anime);
  }

  public List<Anime> findAll(String name) {
    return name == null ? repository.findAll() : repository.findByName(name);
  }

  public Anime findById(Long id) {
    return repository.findById(id).orElse(null);
  }
}
