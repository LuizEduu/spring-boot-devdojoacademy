package academy.devdojo.repository;

import academy.devdojo.domain.Producer;
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

//@ExtendWith(SpringExtension.class) caso eu queira utilizar as funcionalidades do spring nos testes
@ExtendWith(MockitoExtension.class) //mockito para mocks
class ProducerHardCodedRepositoryTest {
  @Mock
  private ProducerData producerData;
  @InjectMocks
  private ProducerHardCodedRepository sut;

  private final List<Producer> producers = new ArrayList<>();

  @BeforeEach
  void init() {
    var now = LocalDateTime.now();
    producers.addAll(List.of(
            Producer.builder().id(1L).name("ufotable").createdAt(now).build(),
            Producer.builder().id(2L).name("wit Studio").createdAt(now).build(),
            Producer.builder().id(3L).name("studio ghibli").createdAt(now).build()
    ));
    BDDMockito.when(producerData.getProducers()).thenReturn(producers);
  }

  @Test
  @DisplayName("findAll returns a list with all producers")
  void findAll_ReturnsAllProducers_WhenSuccessfull() {
    var response = sut.findAll(null);
    Assertions.assertThat(response).isNotNull().hasSameElementsAs(producers);
  }

  @Test
  @DisplayName("findById returns a producer with given id")
  void findByIdReturnsProducerById_WhenSuccessfull() {
    BDDMockito.when(producerData.getProducers()).thenReturn(producers);
    var expectedProducer = producers.getFirst();
    var producer = sut.findById(expectedProducer.getId());
    Assertions.assertThat(producer).isPresent().contains(expectedProducer);
  }

  @Test
  @DisplayName("findByName returns a producer with given name")
  void findByNameReturnsProducerByName_WhenSuccessfull() {
    BDDMockito.when(producerData.getProducers()).thenReturn(producers);

    var expectedProducer = producers.getLast();
    var producer = sut.findByName(expectedProducer.getName());
    Assertions.assertThat(producer).isPresent().contains(expectedProducer);
  }

  @Test
  @DisplayName("findByName returns a empty list when name is not found")
  void findByNameReturnsEmptyListWithNameNotFound_WhenSuccessfull() {
    BDDMockito.when(producerData.getProducers()).thenReturn(producers);

    var producer = sut.findByName("not found name");
    Assertions.assertThat(producer).isNotPresent();
  }

  @Test
  @DisplayName("findAll returns all producers when name is null")
  void findAllReturnsAllProducersWhenNameIsNull_WhenSuccessfull() {
    BDDMockito.when(producerData.getProducers()).thenReturn(producers);

    var expectedProducers = sut.findAll(null);
    Assertions.assertThat(expectedProducers).isNotNull().hasSameElementsAs(producers);
  }

  @Test
  @DisplayName("findAll returns a producer with given a name")
  void findAllReturnsProducerByName_WhenSuccessfull() {
    BDDMockito.when(producerData.getProducers()).thenReturn(producers);

    var expectedProducer = producers.getLast();
    var producers = sut.findAll(expectedProducer.getName());
    Assertions.assertThat(producers).isNotNull().hasSameElementsAs(List.of(expectedProducer));
  }

  @Test
  @DisplayName("findAll returns a empty list when name is not found")
  void findAllReturnsEmptyListWithNameNotFound_WhenSuccessfull() {
    BDDMockito.when(producerData.getProducers()).thenReturn(producers);

    var producers = sut.findAll("not found name");
    Assertions.assertThat(producers).isNotNull().hasSameElementsAs(List.of());
  }
}