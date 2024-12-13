package mx.unam.dgtic.auth.controller.vendedor;

import mx.unam.dgtic.auth.exception.VendedorNoExisteExepcion;
import mx.unam.dgtic.auth.model.Vendedor;
import mx.unam.dgtic.servicio.vendedor.IVendedorService;
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
@RequestMapping(path = "/api/v2/vendedores",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class VendedorDTOController {

    @Autowired
    private IVendedorService vendedorService;

    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable("id") int id, Model model) {
        Optional<Vendedor> vendedor = vendedorService.getVendedorById(id);
        if (vendedor.isPresent()) {
            model.addAttribute("vendedor", vendedor.get());
            return "detalle"; // Vista de Thymeleaf
        } else {
            return "error";
        }
    }

    @GetMapping("/")
        public ResponseEntity<List<Vendedor>> getAll() {
        return ResponseEntity.ok(vendedorService.getVendedoresList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> getById(@PathVariable("id") int id) {
        Optional<Vendedor> vendedor = vendedorService.getVendedorById(id);
        return vendedor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComprador(@PathVariable("id") int id) throws VendedorNoExisteExepcion {
        if (vendedorService.deleteVendedor(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Vendedor> createComprador(@RequestBody Vendedor vendedor) throws URISyntaxException {
        Vendedor nuevoComprador = vendedorService.createVendedor(vendedor);
        URI location = new URI("/api/compradores/" + nuevoComprador.getId());
        return ResponseEntity.created(location).body(nuevoComprador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendedor> updateFullVendedor(@RequestBody Vendedor vendedor, @PathVariable("id") int id) throws URISyntaxException, VendedorNoExisteExepcion {
        Optional<Vendedor> vendedorDB = vendedorService.getVendedorById(id);
        if (vendedorDB.isPresent()) {
            return ResponseEntity.ok(vendedorService.updateVendedor(vendedor));
        } else {
            Vendedor nuevoVendedor = vendedorService.createVendedor(vendedor);
            URI location = new URI("/api/vendedores/" + nuevoVendedor.getId());
            return ResponseEntity.created(location).body(nuevoVendedor);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Vendedor> updatePartialComprador(@RequestBody Vendedor vendedor, @PathVariable("id") int id) throws VendedorNoExisteExepcion {
        Optional<Vendedor> vendedorDB = vendedorService.getVendedorById(id);
        if (vendedorDB.isPresent()) {
            Vendedor vendedorToUpdate = vendedorDB.get();
            if (vendedor.getNombre() != null) vendedorToUpdate.setNombre(vendedor.getNombre());
            if (vendedor.getApellidos() != null) vendedorToUpdate.setApellidos(vendedor.getApellidos());
            if (vendedor.getGenero() != null) vendedorToUpdate.setGenero(vendedor.getGenero());
            if (vendedor.getEdad() != 0) vendedorToUpdate.setEdad(vendedor.getEdad());
            return ResponseEntity.ok(vendedorService.updateVendedor(vendedorToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
