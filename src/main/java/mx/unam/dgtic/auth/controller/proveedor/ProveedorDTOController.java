package mx.unam.dgtic.auth.controller.proveedor;

import jakarta.validation.Valid;
import mx.unam.dgtic.auth.dto.ProveedorDTO;
import mx.unam.dgtic.servicio.proveedor.IProveedorDTOService;
import mx.unam.dgtic.auth.exception.ProveedorNoExisteExepcion;
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
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(path = "/api/v2/proveedores", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProveedorDTOController {

    @Autowired
    private IProveedorDTOService iProveedorDTOService;

    // Obtener todos los proveedores
    @GetMapping(path = "/")
    public List<ProveedorDTO> getAllDTO() {
        return iProveedorDTOService.getProveedoresList();
    }

    // Obtener proveedor por ID
    @GetMapping(path = "/{id}")
    public ResponseEntity<ProveedorDTO> getByIdDTO(@PathVariable int id) {
        Optional<ProveedorDTO> proveedorDTO = iProveedorDTOService.getProveedorById(id);
        if (proveedorDTO.isPresent()) {
            return ResponseEntity.ok(proveedorDTO.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo proveedor
    @PostMapping(path = "/")
    public ResponseEntity<ProveedorDTO> createProveedorDTO(
            @Valid @RequestBody ProveedorDTO proveedor) throws URISyntaxException, ProveedorNoExisteExepcion {
        ProveedorDTO proveedorDTO = iProveedorDTOService.createProveedor(proveedor);
        URI location = new URI("/api/v2/proveedores/" + proveedorDTO.getId());
        return ResponseEntity.created(location).body(proveedorDTO);
    }

    // Actualizar completamente un proveedor
    @PutMapping(path = "/{id}")
    public ResponseEntity<ProveedorDTO> updateProveedorDTO(@PathVariable int id, @RequestBody ProveedorDTO proveedor) throws ProveedorNoExisteExepcion {
        proveedor.setId(id);
        return ResponseEntity.ok(iProveedorDTOService.updateProveedor(proveedor));
    }

    // Actualizar parcialmente un proveedor
    @PatchMapping(path = "/{id}")
    public ResponseEntity<ProveedorDTO> updatePartialProveedorDTO(@PathVariable int id, @RequestBody ProveedorDTO proveedor) throws ProveedorNoExisteExepcion {
        Optional<ProveedorDTO> proveedorDB = iProveedorDTOService.getProveedorById(id);
        if (proveedorDB.isPresent()) {
            ProveedorDTO modificable = proveedorDB.get();
            if (proveedor.getProveedor() != null) modificable.setProveedor(proveedor.getProveedor());
            if (proveedor.getElectronicos() != null) modificable.setElectronicos(proveedor.getElectronicos());
            return ResponseEntity.ok(iProveedorDTOService.updateProveedor(modificable));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un proveedor
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteProveedor(@PathVariable int id) {
        if (iProveedorDTOService.deleteProveedor(id)) {
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
