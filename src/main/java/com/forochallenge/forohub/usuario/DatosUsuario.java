package com.forochallenge.forohub.usuario;

import jakarta.validation.constraints.NotBlank;


public record DatosUsuario(
        @NotBlank
        String nombre
)
{
        public DatosUsuario (Usuario usuario) {
                this(
                        usuario.getNombre()
                );

        }
}

