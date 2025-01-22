package com.forochallenge.forohub.topico;

import com.forochallenge.forohub.curso.Curso;
import com.forochallenge.forohub.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record DatosCrearTopico (
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @DateTimeFormat
        LocalDateTime fechaCreacion,
        @NotNull
        Usuario autor,
        @NotNull
        Curso curso

){
}
