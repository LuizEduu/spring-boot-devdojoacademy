package academy.devdojo.domain;

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
}
