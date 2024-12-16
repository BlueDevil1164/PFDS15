package mx.unam.dgtic.system.controller.marca;

import jakarta.validation.Valid;
import mx.unam.dgtic.system.dto.MarcaDTO;
import mx.unam.dgtic.system.servicio.marca.IMarcaDTOService;
import mx.unam.dgtic.system.exception.MarcaNoExisteExepcion;
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
@RequestMapping(path = "/api/v2/marcas", produces = MediaType.APPLICATION_JSON_VALUE)
public class MarcaDTOController {

    @Autowired
    private IMarcaDTOService iMarcaDTOService;

    // Obtener todas las marcas
    @GetMapping(path = "/")
    public List<MarcaDTO> getAllDTO() {
        return iMarcaDTOService.getMarcasList();
    }

    // Obtener marca por ID
    @GetMapping(path = "/{id}")
    public ResponseEntity<MarcaDTO> getByIdDTO(@PathVariable int id) {
        Optional<MarcaDTO> marcaDTO = iMarcaDTOService.getMarcaById(id);
        if (marcaDTO.isPresent()) {
            return ResponseEntity.ok(marcaDTO.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una nueva marca
    @PostMapping(path = "/")
    public ResponseEntity<MarcaDTO> createMarcaDTO(
            @Valid @RequestBody MarcaDTO marca) throws ParseException, URISyntaxException, MarcaNoExisteExepcion {
        MarcaDTO marcaDTO = iMarcaDTOService.createMarca(marca);
        URI location = new URI("/api/v2/marcas/" + marcaDTO.getId());
        return ResponseEntity.created(location).body(marcaDTO);
    }

    // Actualizar completamente una marca
    @PutMapping(path = "/{id}")
    public ResponseEntity<MarcaDTO> updateMarcaDTO(@PathVariable int id, @RequestBody MarcaDTO marca) throws ParseException, MarcaNoExisteExepcion {
        marca.setId(id);
        return ResponseEntity.ok(iMarcaDTOService.updateMarca(marca));
    }

    // Actualizar parcialmente una marca
    @PatchMapping(path = "/{id}")
    public ResponseEntity<MarcaDTO> updatePartialMarcaDTO(@PathVariable int id, @RequestBody MarcaDTO marca) throws ParseException, MarcaNoExisteExepcion {
        Optional<MarcaDTO> marcaDB = iMarcaDTOService.getMarcaById(id);
        if (marcaDB.isPresent()) {
            MarcaDTO modificable = marcaDB.get();
            if (marca.getNombre() != null) modificable.setNombre(marca.getNombre());
            if (marca.getRate() != 0) modificable.setRate(marca.getRate());
            if (marca.getElectronicoMatricula() != null) modificable.setElectronicoMatricula(marca.getElectronicoMatricula());
            return ResponseEntity.ok(iMarcaDTOService.updateMarca(modificable));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una marca
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteMarca(@PathVariable int id) {
        if (iMarcaDTOService.deleteMarca(id)) {
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
