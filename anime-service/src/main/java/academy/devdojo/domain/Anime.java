package academy.devdojo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Anime {
  private Long id;
  private String name;
  private LocalDateTime createdAt;
}
