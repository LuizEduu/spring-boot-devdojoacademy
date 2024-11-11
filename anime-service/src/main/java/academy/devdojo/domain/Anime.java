package academy.devdojo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Anime {
    private Long id;
    private String name;
    private static List<Anime> animes = new ArrayList<>();
    static  {
        animes.addAll(List.of(new Anime(1L, "One piece"),new Anime(2L, "Dbz"), new Anime(3L, "Jujustu kaisen")));
    }

    public static List<Anime> getAnimes() {
        return animes;
    }
}
