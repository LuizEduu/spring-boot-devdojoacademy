package academy.devdojo.repository;

import academy.devdojo.domain.Producer;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProducerHardCodedRepository {
  private static final List<Producer> producers = new ArrayList<>();

  static {
    var now = LocalDateTime.now();
    producers.addAll(List.of(
            Producer.builder().id(1L).name("Mappa").createdAt(now).build(),
            Producer.builder().id(2L).name("Kyoto Animation").createdAt(now).build(),
            Producer.builder().id(3L).name("Madhouse").createdAt(now).build()
    ));
  }

  public List<Producer> findAll(String name) {
    if (name == null) {
      return producers;
    }
    return producers.stream().filter(producer -> producer.getName().equalsIgnoreCase(name)).toList();
  }

  public Optional<Producer> findById(Long id) {
    return producers.stream().filter(p -> p.getId().equals(id)).findFirst();
  }

  public Producer save(Producer producer) {
    producers.add(producer);

    return producer;
  }

  public void delete(Long id) {
    producers.remove(id);
  }
}
