package academy.devdojo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AnimeGetResponse {
    private Long id;
    private String name;
}
