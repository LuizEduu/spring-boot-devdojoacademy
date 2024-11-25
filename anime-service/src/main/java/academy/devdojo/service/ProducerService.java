package academy.devdojo.service;

import academy.devdojo.domain.Producer;
import academy.devdojo.repository.ProducerHardCodedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProducerService {

  private final ProducerHardCodedRepository repository;

  public List<Producer> findAll(String name) {
    return repository.findAll(name);
  }

  public Producer findByIdOrThrowNotFound(Long id) {
    return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producer not Found"));
  }


  public Optional<Producer> findByName(String name) {
    return repository.findByName(name);
  }

  public Producer save(Producer producer) {
    return repository.save(producer);
  }

  public void delete(Long id) {
    var producer = findByIdOrThrowNotFound(id);
    repository.delete(producer);
  }
}
