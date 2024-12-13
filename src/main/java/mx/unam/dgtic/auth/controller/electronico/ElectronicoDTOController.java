package mx.unam.dgtic.auth.controller.electronico;

import jakarta.validation.Valid;
import mx.unam.dgtic.auth.dto.ElectronicoDTO;
import mx.unam.dgtic.auth.exception.CategoriaNoExisteExepcion;
import mx.unam.dgtic.servicio.electronico.IElectronicoDTOService;
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
@RequestMapping(path = "/api/v2/electronicos",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ElectronicoDTOController {

    @Autowired
    private IElectronicoDTOService iElectronicoDTOService;

    // Obtener todos
    @GetMapping(path = "/")
    public List<ElectronicoDTO> getAllDTO() {
        return iElectronicoDTOService.getElectronicosList();
    }

    // Obtener por matrícula
    @GetMapping(path = "/{matricula}")
    public ResponseEntity<ElectronicoDTO> getByIdDTO(@PathVariable String matricula) {
        Optional<ElectronicoDTO> electronicoDTO = iElectronicoDTOService.getElectronicoById(matricula);
        if (electronicoDTO.isPresent()) {
            return ResponseEntity.ok(electronicoDTO.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<ElectronicoDTO> createElectronicoDTO(
            @Valid @RequestBody ElectronicoDTO electronico) throws ParseException, URISyntaxException, CategoriaNoExisteExepcion {
        ElectronicoDTO electronicoDTO = iElectronicoDTOService.createElectronico(electronico);
        URI location = new URI("/api/v2/electronicos/" + electronicoDTO.getMatricula());
        return ResponseEntity.created(location).body(electronicoDTO);
    }

    @PutMapping(path = "/{matricula}")
    public ResponseEntity<ElectronicoDTO> updateElectronicoDTO(@PathVariable String matricula, @RequestBody ElectronicoDTO electronico) throws ParseException, CategoriaNoExisteExepcion {
        electronico.setMatricula(matricula);
        return ResponseEntity.ok(iElectronicoDTOService.updateElectronico(electronico));
    }

    @PatchMapping(path = "/{matricula}")
    public ResponseEntity<ElectronicoDTO> updatePartialElectronicoDTO(@PathVariable String matricula, @RequestBody ElectronicoDTO electronico) throws ParseException, CategoriaNoExisteExepcion {
        Optional<ElectronicoDTO> electronicoDB = iElectronicoDTOService.getElectronicoById(matricula);
        if (electronicoDB.isPresent()) {
            ElectronicoDTO modificable = electronicoDB.get();
            if (electronico.getNombre() != null) modificable.setNombre(electronico.getNombre());
            if (electronico.getCodigo() != null) modificable.setCodigo(electronico.getCodigo());
            if (electronico.getFfac() != null) modificable.setFfac(electronico.getFfac());
            if (electronico.getPrecio() != 0.0) modificable.setPrecio(electronico.getPrecio());
            if (electronico.getCategoria() != null) modificable.setCategoria(electronico.getCategoria());
            return ResponseEntity.ok(iElectronicoDTOService.updateElectronico(modificable));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{matricula}")
    public ResponseEntity<?> deleteElectronico(@PathVariable String matricula) {
        if (iElectronicoDTOService.deleteElectronico(matricula)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/categoria/{cat}")
    public ResponseEntity<List<ElectronicoDTO>> getByCategoria(@PathVariable String cat) {
        return ResponseEntity.ok(iElectronicoDTOService.findElectronicosByCategoria(cat));
    }

    // /api/v2/electronico/paginado?page=0&size=2&dir=asc&sort=nombre
    @GetMapping(path = "/paginado")
    public ResponseEntity<List<ElectronicoDTO>> getPaginadoAlumnoDTO(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "asc") String dir,
            @RequestParam(defaultValue = "matricula") String sort
    ) {
        return ResponseEntity.ok(iElectronicoDTOService.getElectronicosPageable(page, size, dir, sort));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> errorFormatoDeCliente(
            HttpMessageNotReadableException ex){
        HashMap<String, String> detalles = new HashMap<>();
        detalles.put("mensaje","el formato de los datos es incorrecto");
        detalles.put("detalle", ex.getMessage());
        detalles.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(detalles);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratamientoValidacion(
            MethodArgumentNotValidException ex){

        HashMap<String, Object> detalles = new HashMap<>();
        detalles.put("mensaje", "Eror de validacion de campos. Revisa la entrada" );
        detalles.put("statusCode", ex.getStatusCode().toString());
        detalles.put("timestap", LocalDateTime.now().toString());
        HashMap<String, String> detalleCampos = new HashMap<>();

        int i = 1;
        for (FieldError campoError : ex.getBindingResult().getFieldErrors()){
            detalleCampos.put(campoError.getField()+ i++ ,campoError.getDefaultMessage());
        }
        detalles.put("errores",detalleCampos);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(detalles);
    }

    @GetMapping(path = "/ping/{veces}")
    public ResponseEntity<String> ping(@PathVariable int veces){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < veces; i++) {
            str.append("\n TTL " + i );
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.TEXT_PLAIN)
                .body(str.toString());
    }

}
