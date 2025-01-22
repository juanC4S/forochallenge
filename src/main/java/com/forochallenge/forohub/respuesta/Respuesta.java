package com.forochallenge.forohub.respuesta;

import com.forochallenge.forohub.topico.Topico;
import com.forochallenge.forohub.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "respuesta")

public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    private LocalDateTime fechaCreacion;

    private Boolean solucion;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;


    public Respuesta(Long id, String mensaje, LocalDateTime fechaCreacion, Boolean solucion, Topico topico, Usuario autor) {
        this.id = id;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.solucion = solucion;
        this.topico = topico;
        this.autor = autor;
    }

    public Respuesta (DatosRespuestaTopico datosRespuestaTopico){
        this.mensaje= datosRespuestaTopico.mensaje();
        this.fechaCreacion= datosRespuestaTopico.fechaCreacion();
        this.solucion= datosRespuestaTopico.solucion();
        this.topico= new Topico();
        this.topico.setId(datosRespuestaTopico.idTopico());
        this.autor= new Usuario();
        this.autor.setId(datosRespuestaTopico.idAutor());
    }

    public void actualizarRespuesta (DatosActualizarRespuesta datosActualizarRespuesta){
        if (datosActualizarRespuesta.mensaje() != null){
            this.mensaje= datosActualizarRespuesta.mensaje();
        }
        if (datosActualizarRespuesta.fechaCreacion() != null){
            this.fechaCreacion = datosActualizarRespuesta.fechaCreacion();
        }
        if (datosActualizarRespuesta.solucion()!= null){
            this.solucion= datosActualizarRespuesta.solucion();
        }
    }

}
