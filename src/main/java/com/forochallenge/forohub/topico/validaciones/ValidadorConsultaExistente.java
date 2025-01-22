package com.forochallenge.forohub.topico.validaciones;

import com.forochallenge.forohub.topico.Topico;
import com.forochallenge.forohub.topico.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidadorConsultaExistente {
    @Autowired
    private TopicoRepository repository;

    public void validar(List<Topico> lista){
        if(lista.isEmpty()){
            throw new EntityNotFoundException();
        }
    }
}
