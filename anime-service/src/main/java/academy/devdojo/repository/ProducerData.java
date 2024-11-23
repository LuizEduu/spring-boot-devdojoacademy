package academy.devdojo.repository;

import academy.devdojo.domain.Producer;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class ProducerData {
  private final List<Producer> producers = new ArrayList<>();

  {
    var now = LocalDateTime.now();
    producers.addAll(List.of(
            Producer.builder().id(1L).name("Mappa").createdAt(now).build(),
            Producer.builder().id(2L).name("Kyoto Animation").createdAt(now).build(),
            Producer.builder().id(3L).name("Madhouse").createdAt(now).build()
    ));
  }
}
