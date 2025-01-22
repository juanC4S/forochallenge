package com.forochallenge.forohub.respuesta;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizarRespuesta(
        @NotNull
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean solucion
) {
}
