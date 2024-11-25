package academy.devdojo.service;

import academy.devdojo.domain.Producer;
import academy.devdojo.repository.ProducerHardCodedRepository;
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
class ProducerServiceTest {

  @InjectMocks
  private ProducerService sut;

  @Mock
  private ProducerHardCodedRepository repository;

  private final List<Producer> producersList = new ArrayList<>();

  @BeforeEach
  void init() {
    var now = LocalDateTime.now();
    producersList.addAll(List.of(
            Producer.builder().id(1L).name("Ufotable").createdAt(now).build(),
            Producer.builder().id(2L).name("Wit Studio").createdAt(now).build(),
            Producer.builder().id(3L).name("StudioGhibli").createdAt(now).build()
    ));
  }

  @Test
  @DisplayName("findAll returns a list with all producers when argument is null")
  @Order(1)
  void findAll_ReturnsAllProducers_WhenArgumentIsNull() {
    BDDMockito.when(repository.findAll(null)).thenReturn(producersList);

    var producers = sut.findAll(null);
    Assertions.assertThat(producers).hasSameElementsAs(producersList);
  }

  @Test
  @DisplayName("findAll returns a list with producers when argument is passed")
  @Order(2)
  void findAll_ReturnsProducersFilteredList_WhenArgumentIsPassed() {
    var expectedName = producersList.getLast().getName();
    var producer = producersList.getLast();
    var expectedReturn = Collections.singletonList(producer);
    BDDMockito.when(repository.findAll(expectedName)).thenReturn(expectedReturn);

    var findProducer = sut.findAll(expectedName);

    Assertions.assertThat(findProducer).hasSameElementsAs(expectedReturn);
  }

  @Test
  @DisplayName("findById returns a anime when given id")
  @Order(3)
  void findById_ReturnsAnimeWhenGivenId_Successfull() {
    var id = producersList.getFirst().getId();
    var expectedReturn = Optional.of(producersList.getFirst());
    BDDMockito.when(repository.findById(id)).thenReturn(expectedReturn);

    var producer = sut.findByIdOrThrowNotFound(id);

    Assertions.assertThat(producer).isEqualTo(expectedReturn.get());
  }

  @Test
  @DisplayName("findById throws ResponseStatusException when producer not found")
  @Order(4)
  void findById_ThorwsResponseStatusException_WhenIdNotExists() {
    var expectedId = 200L;
    Optional<Producer> expectedReturn = Optional.empty();
    BDDMockito.when(repository.findById(expectedId)).thenReturn(expectedReturn);

    Assertions.assertThatException()
            .isThrownBy(() -> sut.findByIdOrThrowNotFound(expectedId))
            .isInstanceOf(ResponseStatusException.class);

  }

  @Test
  @DisplayName("findByName returns anime when given name")
  @Order(5)
  void findByName_ReturnsAnimeWhenGivenName_Successfull() {
    var producer = producersList.getFirst();
    var expectedName = producer.getName();
    Optional<Producer> expectedReturn = Optional.of(producer);
    BDDMockito.when(repository.findByName(expectedName)).thenReturn(expectedReturn);

    var returnedProducer = sut.findByName(expectedName);

    Assertions.assertThat(returnedProducer).isPresent().contains(producer);
  }

  @Test
  @DisplayName("save creates a new producer")
  @Order(6)
  void save_CreatesANewProducer_Successfull() {
    var expectedId = 20L;
    var newProducer = Producer.builder().id(expectedId).name("Kyoto Animation").createdAt(LocalDateTime.now()).build();
    BDDMockito.when(repository.save(newProducer)).thenReturn(newProducer);

    var createdProducer = sut.save(newProducer);

    Assertions.assertThat(createdProducer).isEqualTo(newProducer).hasNoNullFieldsOrProperties();
  }

  @Test
  @DisplayName("delete a producer")
  @Order(7)
  void delete_Producer_Successfull() {
    Optional<Producer> producerToDelete = Optional.of(producersList.getFirst());
    var expectedId = producerToDelete.get().getId();
    BDDMockito.when(repository.findById(expectedId)).thenReturn(producerToDelete);
    BDDMockito.doNothing().when(repository).delete(producerToDelete.get());
    Assertions.assertThatNoException().isThrownBy(() -> sut.delete(expectedId));
  }

  @Test
  @DisplayName("delete throws ResponseStatusException when producer is not found")
  @Order(8)
  void delete_ThrowsResponseStatusException_WhenProducerIsNotFound() {
    var producerToDelete = Optional.of(producersList.getLast());
    BDDMockito.when(repository.findById(producerToDelete.get().getId())).thenReturn(Optional.empty());

    Assertions.assertThatException()
            .isThrownBy(() -> sut.delete(producerToDelete.get().getId()))
            .isInstanceOf(ResponseStatusException.class);
  }
}