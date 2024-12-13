package mx.unam.dgtic.auth.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ManejadorGlobalExcepciones {

    @ExceptionHandler(CategoriaNoExisteExepcion.class)
    public ResponseEntity<DetalleError> errorDeRestriccion(
            CategoriaNoExisteExepcion ex,
            HttpServletRequest request
    ){
        DetalleError detalle = new DetalleError();
        detalle.setMessage(ex.getMessage());
        detalle.setDetalle("Error de catalogo de Categorias");
        detalle.setStatusCode(HttpStatus.CONFLICT.toString());
        detalle.setTimeStamp(LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(detalle);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<DetalleError> recursoNoExiste(
            NoResourceFoundException ex,
            HttpServletRequest request
    ){
        DetalleError detalleError = new DetalleError();
        detalleError.setMessage("Ese recurso no existe: "+ request.getRequestURI());
        detalleError.setTimeStamp(LocalDateTime.now());
        detalleError.setStatusCode(HttpStatus.NOT_FOUND.toString());
        detalleError.setDetalle(request.getRequestURI()+ "- "+ request.getContextPath());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(detalleError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<DetalleError> errorDeConversion(MethodArgumentTypeMismatchException ex){
        DetalleError detalleError = new DetalleError();
        detalleError.setMessage(ex.getMessage());
        detalleError.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        detalleError.setTimeStamp(LocalDateTime.now());
        detalleError.setDetalle("propiedad: " + ex.getPropertyName()+ "Tipo de dato: "+ ex.getRequiredType());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(detalleError);
    }
}
