package academy.devdojo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Anime {
    private Long id;
    private String name;
    private LocalDateTime createdAt;


}
