package mx.unam.dgtic.auth.clienteweb.controller;

import mx.unam.dgtic.auth.clienteweb.services.CompradorFrontService;
import mx.unam.dgtic.auth.clienteweb.services.CompradorWebClientService;
import mx.unam.dgtic.auth.dto.CompradorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CompradorFrontController {

    @Autowired
    private CompradorFrontService service;

    @Autowired
    private CompradorWebClientService compradorWebClientService;

    @GetMapping(path = "/front/compradores/{id}")
    public String getComprador(
            @PathVariable String id,
            Model model
    ) {
        CompradorDTO compradorDTO = service.getCompradorById(id);
        model.addAttribute("comprador", compradorDTO);
        return "compradordetalle";
    }

    @GetMapping(path = "/front/compradores/")
    public String getAllCompradores(Model model) {
        model.addAttribute("compradores", compradorWebClientService.getAll());
        return "compradores";
    }

    @GetMapping(path = "/front/compradores/{id}/editar")
    public String getFormEditar(@PathVariable int id, Model model) {
        CompradorDTO compradorDTO = compradorWebClientService.getCompradorById(id);
        model.addAttribute("comprador", compradorDTO);
        return "formEditarComprador";
    }

    @PutMapping(path = "/front/compradores/editar")
    public String actualizarComprador(@RequestBody CompradorDTO compradorDTO, Model model) {
        CompradorDTO compradorActualizado = compradorWebClientService.actualizaComprador(compradorDTO);
        model.addAttribute("comprador", compradorActualizado);
        return "formEditarComprador";
    }
}
