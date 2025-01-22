package com.forochallenge.forohub.infra.error;

import com.forochallenge.forohub.dto.GenericResponseDto;
import com.forochallenge.forohub.usuario.ValidacionException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class TratadorDeErrores {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<GenericResponseDto> tratarError404(){
        return new ResponseEntity<>
                (new GenericResponseDto("No se encontro el contenido solicitado", "Favor de validar la informacion"),
                        HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity<GenericResponseDto> tratarErrorDeValidacion(ValidacionException e){
        return ResponseEntity.badRequest().body
                (new GenericResponseDto("Error al guardar los datos", e.getMessage()));
    }

    private record DatosErrorValidacion(String campo, String error){
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
