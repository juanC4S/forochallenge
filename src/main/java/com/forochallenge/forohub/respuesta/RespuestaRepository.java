package com.forochallenge.forohub.respuesta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RespuestaRepository  extends JpaRepository<Respuesta,Long> {

    @Query (value = "SELECT * FROM Respuesta r WHERE r.topico_id = :id", nativeQuery = true)
    List<Respuesta> findByIdTopico (@Param("id") Long id);

}
