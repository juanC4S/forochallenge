package com.forochallenge.forohub.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    //Page <Topico> findByStatus(Pageable paginacion);

    @Query("SELECT COUNT(t) > 0 FROM Topico t WHERE t.titulo = :titulo AND t.mensaje = :mensaje")
    boolean existsByTituloAndMensaje(@Param("titulo") String titulo, @Param("mensaje") String mensaje);

    @Query (value = "SELECT * FROM Topico t WHERE YEAR (fecha_creacion)= :year", nativeQuery = true)
    List<Topico> findByYear(@Param("year")int year);

    @Query (value = "SELECT t.* FROM topico t JOIN cursos c ON t.curso_id = c.id WHERE c.nombre = :nombreCurso", nativeQuery = true)
    List<Topico> findByNombreCurso(@Param("nombreCurso") String nombre);
}
