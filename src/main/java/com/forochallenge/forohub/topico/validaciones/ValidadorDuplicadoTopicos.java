package com.forochallenge.forohub.topico.validaciones;

import com.forochallenge.forohub.topico.DatosCrearTopico;
import com.forochallenge.forohub.topico.TopicoRepository;
import com.forochallenge.forohub.usuario.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDuplicadoTopicos {

    @Autowired
    private TopicoRepository repository;

    public void validar(DatosCrearTopico datos){
        var topicoDuplicado = repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if(topicoDuplicado){
            throw new ValidacionException("Titulo y mensaje duplicado, ingrese otros");
        }
    }
}
