package com.forochallenge.forohub.topico;

import java.time.LocalDateTime;

public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion
        ) {
}
