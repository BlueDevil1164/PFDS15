package mx.unam.dgtic.clienteweb.controller;

import mx.unam.dgtic.clienteweb.services.CategoriaFrontService;
import mx.unam.dgtic.clienteweb.services.CategoriaWebClientService;
import mx.unam.dgtic.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;

@Controller
public class CategoriaFrontController {

    @Autowired
    private CategoriaFrontService categoriaFrontService;

    @Autowired
    private CategoriaWebClientService categoriaWebClientService;


    // Mostrar los detalles de una categoría por su ID
    @GetMapping(path = "/front/categorias/{id}")
    public String getCategoria(
            @PathVariable String id,
            Model model
    ) {
        CategoriaDTO categoriaDTO = categoriaFrontService.getCategoriaByCategoria(id);
        model.addAttribute("categoria", categoriaDTO);
        return "categoriadetalle";
    }

    // Mostrar todas las categorías
    @GetMapping(path = "/front/categorias/")
    public String getAllCategorias(Model model) {
        model.addAttribute("categorias", categoriaWebClientService.getAll());
        return "categorias";
    }

    // Formulario para editar una categoría específica
    @GetMapping(path = "/front/categorias/{id}/editar")
    public String getFormEditar(@PathVariable int id, Model model) {
        CategoriaDTO categoriaDTO = categoriaWebClientService.getCategoriaById(id);
        model.addAttribute("categoria", categoriaDTO);
        return "formEditarCategoria";
    }

    // Actualizar una categoría existente
    @PutMapping(path = "/front/categorias/editar")
    public String actualizarCategoria(@RequestBody CategoriaDTO categoriaDTO, Model model) {
        CategoriaDTO categoriaActualizada = categoriaWebClientService.actualizaCategoria(categoriaDTO);
        model.addAttribute("categoria", categoriaActualizada);
        return "formEditarCategoria";
    }
}
