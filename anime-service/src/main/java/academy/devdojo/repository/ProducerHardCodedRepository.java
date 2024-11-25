package academy.devdojo.repository;

import academy.devdojo.domain.Producer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ProducerHardCodedRepository {
  private final ProducerData producers;

  public List<Producer> findAll(String name) {
    if (name == null) {
      return producers.getProducers();
    }
    return producers.getProducers().stream().filter(producer -> producer.getName().equalsIgnoreCase(name)).toList();
  }


  public Optional<Producer> findById(Long id) {
    return producers.getProducers().stream().filter(p -> p.getId().equals(id)).findFirst();
  }

  public Optional<Producer> findByName(String name) {
    return producers.getProducers().stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst();
  }

  public Producer save(Producer producer) {
    producers.getProducers().add(producer);

    return producer;
  }

    public void delete(Producer producer) {
    producers.getProducers().remove(producer);
  }
}
