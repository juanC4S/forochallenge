package com.forochallenge.forohub.controller;


import com.forochallenge.forohub.dto.GenericResponseDto;
import com.forochallenge.forohub.respuesta.DatosActualizarRespuesta;
import com.forochallenge.forohub.respuesta.DatosRespuestaTopico;
import com.forochallenge.forohub.respuesta.Respuesta;
import com.forochallenge.forohub.respuesta.RespuestaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/respuesta")
public class RespuestasController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @PostMapping
    public ResponseEntity<GenericResponseDto> responderTopico (@RequestBody @Valid DatosRespuestaTopico datosRespuestaTopico,
                                                               UriComponentsBuilder uriComponentsBuilder) {
        Respuesta crearRespuesta = respuestaRepository.save(new Respuesta(datosRespuestaTopico));
        URI url = uriComponentsBuilder.path("/respuesta/{id}").buildAndExpand(crearRespuesta.getId()).toUri();
        return ResponseEntity.created(url).body(new GenericResponseDto("Operacion exitosa","Repuesta creada con éxito"));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<List<DatosRespuestaTopico>> retornaRespuestas (@PathVariable Long id){
        List<Respuesta> respuestas = respuestaRepository.findByIdTopico(id);
        List<DatosRespuestaTopico> response = respuestas.stream()
                .map(DatosRespuestaTopico::new).toList();
        return ResponseEntity.ok(response);
    }


    @PutMapping ("/{id}")
    @Transactional
    public ResponseEntity<DatosActualizarRespuesta> datosRespuesta (@PathVariable Long id, @RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta) {
        Optional<Respuesta> actualizarRespuesta = respuestaRepository.findById(id);
        if (actualizarRespuesta.isPresent()) {
            Respuesta respuestaTopico = actualizarRespuesta.get();
            respuestaTopico.actualizarRespuesta(datosActualizarRespuesta);
            respuestaRepository.save(respuestaTopico);
        } else {
            throw  new EntityNotFoundException();
        }
        return  ResponseEntity.ok(datosActualizarRespuesta);
    }

    @DeleteMapping ("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta (@PathVariable @NotNull Long id){
        Optional<Respuesta> deleteRespuesta = respuestaRepository.findById(id);
        if (deleteRespuesta.isPresent()){
            respuestaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        } return ResponseEntity.noContent().build();
    }

}
