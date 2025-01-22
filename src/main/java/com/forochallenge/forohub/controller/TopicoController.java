package com.forochallenge.forohub.controller;



import com.forochallenge.forohub.dto.GenericResponseDto;
import com.forochallenge.forohub.topico.*;
import com.forochallenge.forohub.topico.*;
import com.forochallenge.forohub.topico.validaciones.ValidadorConsultaExistente;
import com.forochallenge.forohub.topico.validaciones.ValidadorDuplicadoTopicos;
import com.forochallenge.forohub.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ValidadorDuplicadoTopicos validadorDuplicadoTopicos;

    @Autowired
    private ValidadorConsultaExistente validadorConsultaExistente;
//Crear un nuevo topico
    @PostMapping
    public ResponseEntity<GenericResponseDto> registrarTopico(@RequestBody @Valid DatosCrearTopico datosCrearTopico,
                                                              UriComponentsBuilder uriComponentsBuilder) {
        validadorDuplicadoTopicos.validar(datosCrearTopico);
        Topico crearTopico = topicoRepository.save(new Topico(datosCrearTopico));
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(crearTopico.getId()).toUri();
        return ResponseEntity.created(url).body(new GenericResponseDto("Operación exitosa","Topico creado exitosamente"));
    }
//Mostrar topico especifico por id
    @GetMapping ("/{id}")
    public ResponseEntity<DatosObtenerTopicoId> retornaDatosTopico (@PathVariable Long id){
        Topico respuestasTopico = topicoRepository.getReferenceById(id);
        System.out.println(respuestasTopico.toString());
        DatosObtenerTopicoId response = new DatosObtenerTopicoId(respuestasTopico);
        return ResponseEntity.ok(new DatosObtenerTopicoId(respuestasTopico));
    }
//actualizar un topico
    @PutMapping  ("/{id}")
    @Transactional
    public  ResponseEntity<DatosActualizarTopico> datosTopico (@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosTopico){
        Optional<Topico> actualizarTopico = topicoRepository.findById(id);
        if (actualizarTopico.isPresent()){
            Topico topico = actualizarTopico.get();
            topico.actualizarTopico(datosTopico);
            topicoRepository.save(topico);
        } else {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok(datosTopico);
    }
//eliminar un topico
    @DeleteMapping ("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico (@PathVariable @NotNull Long id){
        Optional<Topico> deleteTopico = topicoRepository.findById(id);
        if (deleteTopico.isPresent()){
            topicoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.noContent().build();
    }
//Mostrar todos los topicos creados
    @GetMapping
    public ResponseEntity<List<DatosObtenerTopicoId>> retornaTodosLosTopicos() {
        List<Topico> topicos = topicoRepository.findAll();
        List<DatosObtenerTopicoId> response = topicos.stream()
                .map(DatosObtenerTopicoId::new)
                .sorted((e1, e2) -> e1.fechaCreacion().compareTo(e2.fechaCreacion()))
                .collect(Collectors.toCollection(ArrayList::new));
        return ResponseEntity.ok(response); // Devuelve la lista como respuesta
    }

    @GetMapping ("/year/{year}")
    public ResponseEntity<List<DatosObtenerTopicoId>> topicoPorYear(@PathVariable @NotNull int year){
        List<Topico> topicos = topicoRepository.findByYear(year);
        validadorConsultaExistente.validar(topicos);
        List<DatosObtenerTopicoId> response = topicos.stream()
                .map(DatosObtenerTopicoId::new)
                .toList();
        return ResponseEntity.ok(response);

    }

    @GetMapping ("/curso/{nombre}")
    public ResponseEntity<List<DatosObtenerTopicoId>> nombreCurso(@PathVariable @NotNull String nombre){
        List<Topico> topicos = topicoRepository.findByNombreCurso(nombre);
        validadorConsultaExistente.validar(topicos);
        List<DatosObtenerTopicoId> response = topicos.stream()
                .map(DatosObtenerTopicoId::new)
                .toList();
        return ResponseEntity.ok(response);

    }

}

