package mx.unam.dgtic.controller.categoria;


import mx.unam.dgtic.model.Categoria;

import mx.unam.dgtic.servicio.categoria.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private ICategoriaService categoriaService;

    // Obtener todas las categorias
    @GetMapping(path = "/")
    public ResponseEntity<List<Categoria>> getAll() {
        List<Categoria> categorias = categoriaService.getCategoriasList();
        return ResponseEntity.ok(categorias);
    }

    // Obtener una categoria por ID
    @GetMapping(path = "/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable("id") int id) {
        Optional<Categoria> categoria = categoriaService.getCategoriaById(id);
        if (categoria.isPresent()) {
            return ResponseEntity.ok(categoria.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una categoria por ID
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteCategoria(@PathVariable("id") int id) {
        if (categoriaService.deleteCategoria(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una nueva categoria
    @PostMapping(path = "/")
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) throws URISyntaxException {
        Categoria nuevaCategoria = categoriaService.createCategoria(categoria);
        URI location = new URI("/api/categorias/" + nuevaCategoria.getIdCategoria());
        return ResponseEntity.created(location).body(nuevaCategoria);
    }

    // Actualizar completamente una categoria
    @PutMapping(path = "/{id}")
    public ResponseEntity<Categoria> updateFullCategoria(@RequestBody Categoria categoria, @PathVariable("id") int id) throws URISyntaxException {
        Optional<Categoria> categoriaDB = categoriaService.getCategoriaById(id);
        if (categoriaDB.isPresent()) {
            return ResponseEntity.ok(categoriaService.updateCategoria(categoria));
        } else {
            Categoria nuevaCategoria = categoriaService.createCategoria(categoria);
            URI location = new URI("/api/categorias/" + nuevaCategoria.getIdCategoria());
            return ResponseEntity.created(location).body(nuevaCategoria);
        }
    }

    // Actualizar parcialmente una categoria
    @PatchMapping(path = "/{id}")
    public ResponseEntity<Categoria> updatePartialCategoria(@RequestBody Categoria categoria, @PathVariable("id") int id) throws URISyntaxException {
        Optional<Categoria> categoriaDB = categoriaService.getCategoriaById(id);
        if (categoriaDB.isPresent()) {
            Categoria categoriaToUpdate = categoriaDB.get();
            if (categoria.getCategoria() != null) {
                categoriaToUpdate.setCategoria(categoria.getCategoria());
            }
            if (categoria.getAbreviatura() != null) {
                categoriaToUpdate.setAbreviatura(categoria.getAbreviatura());
            }
            return ResponseEntity.ok(categoriaService.updateCategoria(categoriaToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
