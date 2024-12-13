package mx.unam.dgtic.auth.controller.categoria;

import jakarta.validation.Valid;
import mx.unam.dgtic.auth.dto.CategoriaDTO;
import mx.unam.dgtic.auth.exception.CategoriaNoExisteExepcion;
import mx.unam.dgtic.servicio.categoria.ICategoriaDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(path = "/api/v2/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaDTOController {

    @Autowired
    private ICategoriaDTOService iCategoriaDTOService;

    // Obtener todas las categorías
    @GetMapping(path = "/")
    public List<CategoriaDTO> getAllDTO() {
        return iCategoriaDTOService.getCategoriasList();
    }

    // Obtener categoría por ID
    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoriaDTO> getByIdDTO(@PathVariable int id) {
        Optional<CategoriaDTO> categoriaDTO = iCategoriaDTOService.getCategoriaById(id);
        if (categoriaDTO.isPresent()) {
            return ResponseEntity.ok(categoriaDTO.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una nueva categoría
    @PostMapping(path = "/")
    public ResponseEntity<CategoriaDTO> createCategoriaDTO(
            @Valid @RequestBody CategoriaDTO categoria) throws ParseException, URISyntaxException, CategoriaNoExisteExepcion {
        CategoriaDTO categoriaDTO = iCategoriaDTOService.createCategoria(categoria);
        URI location = new URI("/api/v2/categorias/" + categoriaDTO.getIdCategoria());
        return ResponseEntity.created(location).body(categoriaDTO);
    }

    // Actualizar completamente una categoría
    @PutMapping(path = "/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoriaDTO(@PathVariable int id, @RequestBody CategoriaDTO categoria) throws ParseException, CategoriaNoExisteExepcion {
        categoria.setIdCategoria(id);
        return ResponseEntity.ok(iCategoriaDTOService.updateCategoria(categoria));
    }

    // Actualizar parcialmente una categoría
    @PatchMapping(path = "/{id}")
    public ResponseEntity<CategoriaDTO> updatePartialCategoriaDTO(@PathVariable int id, @RequestBody CategoriaDTO categoria) throws ParseException, CategoriaNoExisteExepcion {
        Optional<CategoriaDTO> categoriaDB = iCategoriaDTOService.getCategoriaById(id);
        if (categoriaDB.isPresent()) {
            CategoriaDTO modificable = categoriaDB.get();
            if (categoria.getCategoria() != null) modificable.setCategoria(categoria.getCategoria());
            if (categoria.getAbreviatura() != null) modificable.setAbreviatura(categoria.getAbreviatura());
            return ResponseEntity.ok(iCategoriaDTOService.updateCategoria(modificable));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una categoría
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable int id) {
        if (iCategoriaDTOService.deleteCategoria(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Manejo de excepciones de formato incorrecto
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> errorFormatoDeCliente(HttpMessageNotReadableException ex) {
        HashMap<String, String> detalles = new HashMap<>();
        detalles.put("mensaje", "El formato de los datos es incorrecto");
        detalles.put("detalle", ex.getMessage());
        detalles.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(detalles);
    }

    // Manejo de excepciones de validación
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratamientoValidacion(MethodArgumentNotValidException ex) {
        HashMap<String, Object> detalles = new HashMap<>();
        detalles.put("mensaje", "Error de validación de campos. Revisa la entrada");
        detalles.put("statusCode", ex.getStatusCode().toString());
        detalles.put("timestamp", LocalDateTime.now().toString());
        HashMap<String, String> detalleCampos = new HashMap<>();

        int i = 1;
        for (FieldError campoError : ex.getBindingResult().getFieldErrors()) {
            detalleCampos.put(campoError.getField() + i++, campoError.getDefaultMessage());
        }
        detalles.put("errores", detalleCampos);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(detalles);
    }

    // Método para pruebas
    @GetMapping(path = "/ping/{veces}")
    public ResponseEntity<String> ping(@PathVariable int veces) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < veces; i++) {
            str.append("\n TTL ").append(i);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.TEXT_PLAIN)
                .body(str.toString());
    }
}
