package mx.unam.dgtic.system.controller.proveedor;

import mx.unam.dgtic.system.model.Proveedor;
import mx.unam.dgtic.system.servicio.proveedor.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private IProveedorService proveedorService;

    // Obtener todos los proveedores
    @GetMapping(path = "/")
    public ResponseEntity<List<Proveedor>> getAll() {
        List<Proveedor> proveedores = proveedorService.getProveedoresList();
        return ResponseEntity.ok(proveedores);
    }

    // Obtener un proveedor por ID
    @GetMapping(path = "/{id}")
    public ResponseEntity<Proveedor> getById(@PathVariable("id") int id) {
        Optional<Proveedor> proveedor = proveedorService.getProveedorById(id);
        if (proveedor.isPresent()) {
            return ResponseEntity.ok(proveedor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un proveedor por ID
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteProveedor(@PathVariable("id") int id) {
        if (proveedorService.deleteProveedor(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo proveedor
    @PostMapping(path = "/")
    public ResponseEntity<Proveedor> createProveedor(@RequestBody Proveedor proveedor) throws URISyntaxException {
        Proveedor nuevoProveedor = proveedorService.createProveedor(proveedor);
        URI location = new URI("/api/proveedores/" + nuevoProveedor.getId());
        return ResponseEntity.created(location).body(nuevoProveedor);
    }

    // Actualizar completamente un proveedor
    @PutMapping(path = "/{id}")
    public ResponseEntity<Proveedor> updateFullProveedor(@RequestBody Proveedor proveedor, @PathVariable("id") int id) throws URISyntaxException {
        Optional<Proveedor> proveedorDB = proveedorService.getProveedorById(id);
        if (proveedorDB.isPresent()) {
            return ResponseEntity.ok(proveedorService.updateProveedor(proveedor));
        } else {
            Proveedor nuevoProveedor = proveedorService.createProveedor(proveedor);
            URI location = new URI("/api/proveedores/" + nuevoProveedor.getId());
            return ResponseEntity.created(location).body(nuevoProveedor);
        }
    }

    // Actualizar parcialmente un proveedor
    @PatchMapping(path = "/{id}")
    public ResponseEntity<Proveedor> updatePartialProveedor(@RequestBody Proveedor proveedor, @PathVariable("id") int id) throws URISyntaxException {
        Optional<Proveedor> proveedorDB = proveedorService.getProveedorById(id);
        if (proveedorDB.isPresent()) {
            Proveedor proveedorToUpdate = proveedorDB.get();
            if (proveedor.getProveedor() != null) {
                proveedorToUpdate.setProveedor(proveedor.getProveedor());
            }
            return ResponseEntity.ok(proveedorService.updateProveedor(proveedorToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
