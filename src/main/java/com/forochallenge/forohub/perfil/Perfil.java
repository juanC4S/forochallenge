package com.forochallenge.forohub.perfil;

import com.forochallenge.forohub.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Entity
@Table (name = "perfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Perfil(@NotBlank String nombre, String contrasena, @NotBlank @Email String email) {
    }

    public Perfil (DatosCrearPerfil crearPerfil){
        this.nombre= crearPerfil.nombre();
    }
}

