package academy.devdojo.controller;

import academy.devdojo.mapper.ProducerMapper;
import academy.devdojo.request.ProducerPostRequest;
import academy.devdojo.response.ProducerGetResponse;
import academy.devdojo.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("v1/producers")
@RequiredArgsConstructor
public class ProducerController {
  private  final ProducerMapper mapper;
  private final ProducerService service;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest body) {
    var producerPostRequest = mapper.toProducer(body);
    var producer = service.save(producerPostRequest);

    var response = mapper.toProducerGetResponse(producer);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("{id}")
  public ResponseEntity<ProducerGetResponse> findById(@PathVariable Long id) {
    var producer = service.findByIdOrThrowNotFound(id);

    var response = mapper.toProducerGetResponse(producer);
    return ResponseEntity.ok(response);
  }

  @GetMapping()
  public ResponseEntity<List<ProducerGetResponse>> findAll(@RequestParam(required = false) String name) {
    var producers = service.findAll(name);
    var response = mapper.toProducerGetResponseList(producers);

    return ResponseEntity.ok(response);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }

}

