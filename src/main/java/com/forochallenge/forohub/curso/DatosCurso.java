package com.forochallenge.forohub.curso;

public record DatosCurso(
        Long id,
        String nombre,
        Categoria categoria
) {
    public DatosCurso(Curso curso){
        this (
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        );
    }
}
