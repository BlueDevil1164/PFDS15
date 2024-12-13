package mx.unam.dgtic.auth.controller.marca;

import mx.unam.dgtic.auth.model.Marca;
import mx.unam.dgtic.servicio.marca.IMarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    @Autowired
    private IMarcaService marcaService;

    // Obtener todas las marcas
    @GetMapping(path = "/")
    public ResponseEntity<List<Marca>> getAll() {
        List<Marca> marcas = marcaService.getMarcasList();
        return ResponseEntity.ok(marcas);
    }

    // Obtener una marca por ID
    @GetMapping(path = "/{id}")
    public ResponseEntity<Marca> getById(@PathVariable("id") int id) {
        Optional<Marca> marca = marcaService.getMarcaById(id);
        if (marca.isPresent()) {
            return ResponseEntity.ok(marca.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una marca por ID
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteMarca(@PathVariable("id") int id) {
        if (marcaService.deleteMarca(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una nueva marca
    @PostMapping(path = "/")
    public ResponseEntity<Marca> createMarca(@RequestBody Marca marca) throws URISyntaxException {
        Marca nuevaMarca = marcaService.createMarca(marca);
        URI location = new URI("/api/marcas/" + nuevaMarca.getId());
        return ResponseEntity.created(location).body(nuevaMarca);
    }

    // Actualizar completamente una marca
    @PutMapping(path = "/{id}")
    public ResponseEntity<Marca> updateFullMarca(@RequestBody Marca marca, @PathVariable("id") int id) throws URISyntaxException {
        Optional<Marca> marcaDB = marcaService.getMarcaById(id);
        if (marcaDB.isPresent()) {
            return ResponseEntity.ok(marcaService.updateMarca(marca));
        } else {
            Marca nuevaMarca = marcaService.createMarca(marca);
            URI location = new URI("/api/marcas/" + nuevaMarca.getId());
            return ResponseEntity.created(location).body(nuevaMarca);
        }
    }

    // Actualizar parcialmente una marca
    @PatchMapping(path = "/{id}")
    public ResponseEntity<Marca> updatePartialMarca(@RequestBody Marca marca, @PathVariable("id") int id) throws URISyntaxException {
        Optional<Marca> marcaDB = marcaService.getMarcaById(id);
        if (marcaDB.isPresent()) {
            Marca marcaToUpdate = marcaDB.get();
            if (marca.getNombre() != null) {
                marcaToUpdate.setNombre(marca.getNombre());
            }
            if (marca.getRate() != 0) {
                marcaToUpdate.setRate(marca.getRate());
            }
            return ResponseEntity.ok(marcaService.updateMarca(marcaToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
