package com.forochallenge.forohub.topico;

import com.forochallenge.forohub.curso.DatosCurso;
import com.forochallenge.forohub.usuario.DatosUsuario;

import java.time.LocalDateTime;

public record DatosObtenerTopicoId(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, EstadoTopico status, DatosUsuario autor, DatosCurso curso) {

    public DatosObtenerTopicoId(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                new DatosUsuario(topico.getAutor()),
                new DatosCurso(topico.getCurso()));
    }
}
