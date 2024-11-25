package academy.devdojo.repository;

import academy.devdojo.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AnimeHardCodedRepositoryTest {

  @InjectMocks
  private AnimeHardCodedRepository sut;

  @Mock
  private AnimeData animeData;

  private final List<Anime> animes = new ArrayList<>();

  @BeforeEach
  void init() {
    var now = LocalDateTime.now();
    animes.addAll(List.of(new Anime(1L, "One piece", now), new Anime(2L, "Dbz", now), new Anime(3L, "Jujustu kaisen", now)));

    BDDMockito.when(animeData.getAnimes()).thenReturn(animes);
  }

  @Test
  @DisplayName("save creates a new anime")
  void save_createsAnime_WhenSuccessfull() {
    var animeToSave = Anime.builder().id(200L).name("Hunter x Hunter").createdAt(LocalDateTime.now()).build();

    var savedAnime = sut.save(animeToSave);

    var findAnime = sut.findById(savedAnime.getId());

    Assertions.assertThat(savedAnime).hasNoNullFieldsOrProperties().isEqualTo(animeToSave);
    Assertions.assertThat(findAnime).isPresent().contains(savedAnime);
  }

  @Test
  @DisplayName("findAll returns a list of animes")
  void findAll_returnsAListOfAnimes_WhenSuccessfull() {
    var findAnimes = sut.findAll();

    Assertions.assertThat(findAnimes).hasSize(animes.size()).hasSameElementsAs(animes);
  }

  @Test
  @DisplayName("findByName when give a name")
  void findByName_returnsAAnime_WhenSuccessfull() {
    var expectedAnime = animes.getLast();

    var findAnime = sut.findByName(expectedAnime.getName());

    Assertions.assertThat(findAnime).isNotNull().hasSize(1).hasSameElementsAs(List.of(expectedAnime));

  }

  @Test
  @DisplayName("findByName returns a empty list when name is not contains")
  void findByName_returnsAEmptyListIfNameIsNotContains_WhenSuccessfull() {
    var name = "not contains name";
    var findAnime = sut.findByName(name);

    Assertions.assertThat(findAnime).isNotNull().isEmpty();

  }
}