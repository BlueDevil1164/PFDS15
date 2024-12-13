package mx.unam.dgtic.auth.clienteweb.controller;

import mx.unam.dgtic.auth.clienteweb.services.VendedorFrontService;
import mx.unam.dgtic.auth.clienteweb.services.VendedorWebClientService;
import mx.unam.dgtic.auth.dto.VendedorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class VendedorFrontController {

    @Autowired
    private VendedorFrontService service;

    @Autowired
    private VendedorWebClientService vendedorWebClientService;

    @GetMapping(path = "/front/vendedores/{id}")
    public String getVendedor(
            @PathVariable int id,
            Model model
    ) {
        VendedorDTO vendedorDTO = service.getVendedorById(id);
        model.addAttribute("vendedor", vendedorDTO);
        return "vendedordetalle";
    }

    @GetMapping(path = "/front/vendedores/")
    public String getAllVendedores(Model model) {
        model.addAttribute("vendedores", vendedorWebClientService.getAll());
        return "vendedores";
    }

    @GetMapping(path = "/front/vendedores/{matricula}/editar")
    public String getFormEditar(@PathVariable int id, Model model) {
        VendedorDTO vendedorDTO = vendedorWebClientService.getVendedorById(id);
        model.addAttribute("vendedor", vendedorDTO);
        return "formEditarVendedor";
    }

    @PutMapping(path = "/front/vendedores/editar")
    public String actualizarVendedor(@RequestBody VendedorDTO vendedorDTO, Model model) {
        VendedorDTO vendedorActualizado = vendedorWebClientService.actualizaVendedor(vendedorDTO);
        model.addAttribute("vendedor", vendedorActualizado);
        return "formEditarVendedor";
    }
}
