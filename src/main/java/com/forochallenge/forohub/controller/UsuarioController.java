package com.forochallenge.forohub.controller;


import com.forochallenge.forohub.dto.GenericResponseDto;
import com.forochallenge.forohub.usuario.DatosDetalleUsuario;
import com.forochallenge.forohub.usuario.Usuario;
import com.forochallenge.forohub.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<GenericResponseDto> insertUsuario (@RequestBody @Valid DatosDetalleUsuario crearUsuario,
                                                             UriComponentsBuilder uriComponentsBuilder){

        Usuario usuarioGuardado = usuarioRepository.save(new Usuario(crearUsuario));
        URI uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuarioGuardado.getId()).toUri();

        return ResponseEntity.created(uri).body(new GenericResponseDto("Operacion Exitosa", "Perfil creado exitosamente"));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<DatosDetalleUsuario> retornaDatosUsuario (@PathVariable Long id){
        Usuario usuarioConsulta = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleUsuario(usuarioConsulta.getNombre(),usuarioConsulta.getEmail(),
                usuarioConsulta.getContrasena(),usuarioConsulta.getPerfiles()));
    }

    @DeleteMapping ("/{id}")
    @Transactional
    public  ResponseEntity<GenericResponseDto> eliminarUsuario (@PathVariable  @NotNull Long id){
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().body(new GenericResponseDto("Operacion Exitosa", String.format("Usuario %s eliminado exitosamente",id)));
    }




}
