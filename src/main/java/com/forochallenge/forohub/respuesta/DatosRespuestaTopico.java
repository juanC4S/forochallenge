package com.forochallenge.forohub.respuesta;
import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        String mensaje,
        Long idTopico,
        LocalDateTime fechaCreacion,
        Boolean solucion,
        Long idAutor
) {
    public DatosRespuestaTopico (Respuesta respuesta){
        this (
                respuesta.getMensaje(),
                respuesta.getTopico().getId(),
                respuesta.getFechaCreacion(),
                respuesta.getSolucion(),
                respuesta.getAutor().getId());
    }
}
