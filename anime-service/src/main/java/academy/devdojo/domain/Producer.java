package academy.devdojo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producer {
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private static List<Producer> producers = new ArrayList<>();
    static  {
        producers.addAll(List.of(
                 Producer.builder().id(1L).name("Mappa").build(),
                Producer.builder().id(2L).name("Kyoto Animation").build(),
                Producer.builder().id(3L).name("Madhouse").build()
        ));
    }

    public static List<Producer> getProducers() {
        return producers;
    }
}
