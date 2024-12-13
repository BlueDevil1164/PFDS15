package mx.unam.dgtic.auth.controller.electronico;

import mx.unam.dgtic.auth.model.Electronico;
import mx.unam.dgtic.servicio.electronico.IElectronicoService;
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
@RequestMapping(path = "/api/electronicos",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ElectronicoController {

    @Autowired
    private IElectronicoService electronicoService;


    // Método para mostrar el detalle de un electrónico en una vista Thymeleaf
    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable("id") String id, Model model) {
        Optional<Electronico> electronico = electronicoService.getElectronicoById(id);
        if (electronico.isPresent()) {
            model.addAttribute("electronico", electronico.get());
            return "detalle";  // Esto corresponde al archivo HTML que compartiste (detalle.html)
        } else {
            return "error";  // Redirige a una página de error si no se encuentra el electrónico
        }
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<Electronico>> getAll() {
        return ResponseEntity.ok(electronicoService.getElectronicosList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Electronico> getById(@PathVariable("id") String id) {
        Optional<Electronico> electronico = electronicoService.getElectronicoById(id);
        if (electronico.isPresent()) {
            return ResponseEntity.ok(electronico.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteElectronico(@PathVariable("id") String id) {
        if (electronicoService.deleteElectronico(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<Electronico> createElectronico(@RequestBody Electronico electronico) throws URISyntaxException {
        Electronico electronicoNuevo = electronicoService.createElectronico(electronico);
        URI location = new URI("/api/electronicos/" + electronicoNuevo.getMatricula());
        return ResponseEntity.created(location).body(electronicoNuevo);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Electronico> updateFullElectronico(@RequestBody Electronico electronico, @PathVariable("id") String id) throws URISyntaxException {
        Optional<Electronico> electronicoDB = electronicoService.getElectronicoById(id);
        if (electronicoDB.isPresent()) {
            return ResponseEntity.ok(electronicoService.updateElectronico(electronico));
        } else {
            Electronico electronicoNuevo = electronicoService.createElectronico(electronico);
            URI location = new URI("/api/electronicos/" + electronicoNuevo.getMatricula());
            return ResponseEntity.created(location).body(electronicoNuevo);
        }
    }

    @PatchMapping(path = "/{id}")
        public ResponseEntity<Electronico> updatePartialElectronico(@RequestBody Electronico electronico, @PathVariable("id") String id) throws URISyntaxException {
        Optional<Electronico> electronicoDB = electronicoService.getElectronicoById(id);
        if (electronicoDB.isPresent()) {
            Electronico electronicoToUpdate = electronicoDB.get();
            if (electronico.getNombre() != null) {
                electronicoToUpdate.setNombre(electronico.getNombre());
            }
            if (electronico.getCodigo() != null) {
                electronicoToUpdate.setCodigo(electronico.getCodigo());
            }
            if (electronico.getFfac() != null) {
                electronicoToUpdate.setFfac(electronico.getFfac());
            }
            if (electronico.getPrecio() != 0.0) {
                electronicoToUpdate.setPrecio(electronico.getPrecio());
            }
            return ResponseEntity.ok(electronicoService.updateElectronico(electronicoToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/categoria/{cat}")
    public ResponseEntity<List<Electronico>> getByCategoria(@PathVariable String cat) {
        return ResponseEntity.ok(electronicoService.findElectronicosByCategoria(cat));
    }



}

