package com.forochallenge.forohub.perfil;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosCrearPerfil(
        @NotBlank
        String nombre
) {
}
