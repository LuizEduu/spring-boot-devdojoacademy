package academy.devdojo.service;

import academy.devdojo.domain.Producer;
import academy.devdojo.repository.ProducerHardCodedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProducerService {

  private final ProducerHardCodedRepository repository;

  public List<Producer> findAll(String name) {
    return repository.findAll(name);
  }

  public Optional<Producer> findById(Long id) {
    return repository.findById(id);
  }

  public Optional<Producer> findByName(String name) {
    return repository.findByName(name);
  }

  public Producer save(Producer producer) {
    return repository.save(producer);
  }

  public void delete(Long id) {
    var producer = repository.findById(id);

    if (producer.isEmpty()) {
      return;
    }

    repository.delete(producer.get());
  }
}
