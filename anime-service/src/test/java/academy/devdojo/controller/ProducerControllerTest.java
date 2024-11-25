package academy.devdojo.controller;

import academy.devdojo.domain.Producer;
import academy.devdojo.mapper.ProducerMapperImpl;
import academy.devdojo.repository.ProducerData;
import academy.devdojo.repository.ProducerHardCodedRepository;
import academy.devdojo.service.ProducerService;
import org.junit.jupiter.api.*;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers = ProducerController.class) //slices tests
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Import({ProducerMapperImpl.class, ProducerService.class, ProducerHardCodedRepository.class, ProducerData.class})
class ProducerControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private ProducerData producerData;
  private List<Producer> producerList = new ArrayList<>();

  @BeforeEach
  void init() {
    var now = LocalDateTime.now();
    producerList.addAll(List.of(
            Producer.builder().id(1L).name("ufotable").createdAt(now).build(),
            Producer.builder().id(2L).name("wit Studio").createdAt(now).build(),
            Producer.builder().id(3L).name("studio ghibli").createdAt(now).build()
    ));
  }

  @Test
  @DisplayName("findAll returns a list with all producers when argument is null")
  @Order(1)
  void findAllReturnsAllProducers_WhenArgumentIsNull() throws Exception {
    BDDMockito.when(producerData.getProducers()).thenReturn(producerList);
    mockMvc.perform(MockMvcRequestBuilders.get("/v1/producers"))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk());
    //.andExpect()
  }
}