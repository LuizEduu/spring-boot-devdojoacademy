package academy.devdojo.service;

import academy.devdojo.domain.Anime;
import academy.devdojo.repository.AnimeHardCodedRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnimeServiceTest {

  @InjectMocks
  private AnimeService sut;

  @Mock
  private AnimeHardCodedRepository repository;

  private List<Anime> animesList = new ArrayList<>();

  @BeforeEach
  void init() {
    var now = LocalDateTime.now();
    animesList.addAll(List.of(new Anime(1L, "One piece", now), new Anime(2L, "Dbz", now), new Anime(3L, "Jujustu kaisen", now)));
  }

  @Test
  @DisplayName("save creates a new anime")
  @Order(1)
  void save_CreateANewAnime_Successfull() {
    var animeToCreate = Anime.builder().id(200L).name("Hunter x Hunter").createdAt(LocalDateTime.now()).build();
    BDDMockito.when(repository.save(animeToCreate)).thenReturn(animeToCreate);

    var createdAnime = sut.save(animeToCreate);

    Assertions.assertThat(createdAnime).isEqualTo(animeToCreate).hasNoNullFieldsOrProperties();
  }

  @Test
  @DisplayName("findAll animes return list of all animes when name is null")
  @Order(2)
  void findAll_AnimesReturnListOffAllAnimesWhenNameIsNull_Successfull() {
    BDDMockito.when(repository.findAll()).thenReturn(animesList);

    var animes = sut.findAll(null);

    Assertions.assertThat(animes).hasSameElementsAs(animesList);
  }

  @Test
  @DisplayName("findAll filter animes by name and return list of one anime")
  @Order(3)
  void findAll_FilterAnimesByNameAndReturnListOfOneAnime_Successfull() {
    var expectedAnime = animesList.getLast();
    var expectedName = expectedAnime.getName();
    BDDMockito.when(repository.findByName(expectedName)).thenReturn(Collections.singletonList(expectedAnime));

    var findAnime = sut.findAll(expectedName);

    Assertions.assertThat(findAnime).hasSameElementsAs(Collections.singletonList(expectedAnime));
  }

  @Test
  @DisplayName("findAll returns a empty list when name is not contains")
  @Order(4)
  void findAll_ReturnsAEmptyListWhenNameIsNotContains_Successfull() {
    var expectedName = "not contains name";
    BDDMockito.when(repository.findByName(expectedName)).thenReturn(Collections.emptyList());

    var findAnime = sut.findAll(expectedName);

    Assertions.assertThat(findAnime).isEmpty();
  }

  @Test
  @DisplayName("findById returns anime when given id")
  @Order(5)
  void findById_ReturnsAnimeWhenGivenId_Successfull() {
    var expectedAnime = animesList.getFirst();
    var expectedId = expectedAnime.getId();
    BDDMockito.when(repository.findById(expectedId)).thenReturn(Optional.of(expectedAnime));

    var anime = sut.findById(expectedId);

    Assertions.assertThat(anime).hasNoNullFieldsOrProperties().isEqualTo(expectedAnime);
  }

  @Test
  @DisplayName("findById throws ResponseStatusException when anime is not found")
  @Order(8)
  void findById_ThrowsResponseStatusException_WhenAnimeIsNotFound() {
    var animeToFind = Optional.of(animesList.getLast());
    var expectedId = animeToFind.get().getId();
    BDDMockito.when(repository.findById(expectedId)).thenReturn(Optional.empty());

    Assertions.assertThatException()
            .isThrownBy(() -> sut.findById(expectedId))
            .isInstanceOf(ResponseStatusException.class);
  }
}
