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

    private static List<Anime> animes = new ArrayList<>();
    static  {
        var now = LocalDateTime.now();
        animes.addAll(List.of(new Anime(1L, "One piece", now),new Anime(2L, "Dbz", now), new Anime(3L, "Jujustu kaisen", now)));
    }

    public static List<Anime> getAnimes() {
        return animes;
    }
}
