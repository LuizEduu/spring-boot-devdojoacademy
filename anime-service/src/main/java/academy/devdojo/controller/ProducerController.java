package academy.devdojo.controller;

import academy.devdojo.domain.Producer;
import academy.devdojo.mapper.ProducerMapper;
import academy.devdojo.request.ProducerPostRequest;
import academy.devdojo.response.ProducerGetResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("v1/producers")
public class ProducerController {
  private static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest body) {
    var producer = MAPPER.toProducer(body);
    var response = MAPPER.toProducerGetResponse(producer);
    Producer.getProducers().add(producer);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("{id}")
  public ResponseEntity<ProducerGetResponse> findById(@PathVariable Long id) {
    var producer = Producer.getProducers().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    var response = MAPPER.toProducerGetResponse(producer);

    return ResponseEntity.ok(response);
  }

  @GetMapping()
  public ResponseEntity<List<ProducerGetResponse>> findAll(@RequestParam(required = false) String name) {
    var producers = Producer.getProducers();
    var producersGetResponse = MAPPER.toProducerGetResponseList(producers);
    if (name == null) return ResponseEntity.ok(producersGetResponse);

    var filterProducersByName = producersGetResponse.stream().filter(p -> p.getName().equalsIgnoreCase(name)).toList();

    return ResponseEntity.ok(filterProducersByName);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    var producer = Producer.getProducers()
            .stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producer not Found"));

    Producer.getProducers().remove(producer);

    return ResponseEntity.noContent().build();
  }

}

