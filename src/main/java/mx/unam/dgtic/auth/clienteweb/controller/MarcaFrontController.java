package mx.unam.dgtic.auth.clienteweb.controller;

import mx.unam.dgtic.auth.clienteweb.services.MarcaFrontService;
import mx.unam.dgtic.auth.clienteweb.services.MarcaWebClientService;
import mx.unam.dgtic.auth.dto.MarcaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MarcaFrontController {

    @Autowired
    private MarcaFrontService marcaFrontService;

    @Autowired
    private MarcaWebClientService marcaWebClientService;

    // Mostrar los detalles de una marca por su ID
    @GetMapping(path = "/front/marcas/{id}")
    public String getMarca(
            @PathVariable int id,
            Model model
    ) {
        MarcaDTO marcaDTO = marcaFrontService.getMarcaById(id);
        model.addAttribute("marca", marcaDTO);
        return "marcadetalle";
    }

    // Mostrar todas las marcas
    @GetMapping(path = "/front/marcas/")
    public String getAllMarcas(Model model) {
        model.addAttribute("marcas", marcaWebClientService.getAll());
        return "marcas";
    }

    // Formulario para editar una marca específica
    @GetMapping(path = "/front/marcas/{id}/editar")
    public String getFormEditar(@PathVariable int id, Model model) {
        MarcaDTO marcaDTO = marcaWebClientService.getMarcaById(id);
        model.addAttribute("marca", marcaDTO);
        return "formEditarMarca";
    }

    // Actualizar una marca existente
    @PutMapping(path = "/front/marcas/editar")
    public String actualizarMarca(@RequestBody MarcaDTO marcaDTO, Model model) {
        MarcaDTO marcaActualizada = marcaWebClientService.actualizaMarca(marcaDTO);
        model.addAttribute("marca", marcaActualizada);
        return "formEditarMarca";
    }
}
