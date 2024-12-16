package mx.unam.dgtic.system.controller.comprador;

import mx.unam.dgtic.system.exception.CompradorNoExisteExepcion;
import mx.unam.dgtic.system.model.Comprador;
import mx.unam.dgtic.system.servicio.comprador.ICompradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v2/compradores",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CompradorDTOController {

    @Autowired
    private ICompradorService compradorService;

    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable("id") int id, Model model) {
        Optional<Comprador> comprador = compradorService.getCompradorById(id);
        if (comprador.isPresent()) {
            model.addAttribute("comprador", comprador.get());
            return "detalle"; // Vista de Thymeleaf
        } else {
            return "error";
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Comprador>> getAll() {
        return ResponseEntity.ok(compradorService.getCompradoresList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comprador> getById(@PathVariable("id") int id) {
        Optional<Comprador> comprador = compradorService.getCompradorById(id);
        return comprador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComprador(@PathVariable("id") int id) throws CompradorNoExisteExepcion {
        if (compradorService.deleteComprador(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Comprador> createComprador(@RequestBody Comprador comprador) throws URISyntaxException {
        Comprador nuevoComprador = compradorService.createComprador(comprador);
        URI location = new URI("/api/compradores/" + nuevoComprador.getId());
        return ResponseEntity.created(location).body(nuevoComprador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comprador> updateFullComprador(@RequestBody Comprador comprador, @PathVariable("id") int id) throws URISyntaxException, CompradorNoExisteExepcion {
        Optional<Comprador> compradorDB = compradorService.getCompradorById(id);
        if (compradorDB.isPresent()) {
            return ResponseEntity.ok(compradorService.updateComprador(comprador));
        } else {
            Comprador nuevoComprador = compradorService.createComprador(comprador);
            URI location = new URI("/api/compradores/" + nuevoComprador.getId());
            return ResponseEntity.created(location).body(nuevoComprador);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Comprador> updatePartialComprador(@RequestBody Comprador comprador, @PathVariable("id") int id) throws CompradorNoExisteExepcion {
        Optional<Comprador> compradorDB = compradorService.getCompradorById(id);
        if (compradorDB.isPresent()) {
            Comprador compradorToUpdate = compradorDB.get();
            if (comprador.getNombre() != null) compradorToUpdate.setNombre(comprador.getNombre());
            if (comprador.getApellidos() != null) compradorToUpdate.setApellidos(comprador.getApellidos());
            if (comprador.getGenero() != null) compradorToUpdate.setGenero(comprador.getGenero());
            if (comprador.getEdad() != 0) compradorToUpdate.setEdad(comprador.getEdad());
            return ResponseEntity.ok(compradorService.updateComprador(compradorToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
