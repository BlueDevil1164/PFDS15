package mx.unam.dgtic.auth.clienteweb.controller;

import mx.unam.dgtic.auth.dto.CategoriaDTO;
import mx.unam.dgtic.auth.dto.ProveedorDTO;
import mx.unam.dgtic.auth.clienteweb.services.ProveedorFrontService;
import mx.unam.dgtic.auth.clienteweb.services.ProveedorWebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProveedorFrontController {

    @Autowired
    private ProveedorFrontService proveedorFrontService;

    @Autowired
    private ProveedorWebClientService proveedorWebClientService;

    // Mostrar los detalles de un proveedor por su ID
    @GetMapping(path = "/front/proveedores/{id}")
    public String getProveedor(
            @PathVariable int id,
            Model model
    ) {
        ProveedorDTO proveedorDTO = proveedorFrontService.getProveedorById(id);
        model.addAttribute("proveedor", proveedorDTO);
        return "proveedordetalle";
    }

    // Mostrar todos los proveedores
    @GetMapping(path = "/front/proveedores/")
    public String getAllProveedores(Model model) {
        model.addAttribute("proveedores", proveedorWebClientService.getAll());
        return "proveedores";
    }

    // Formulario para editar un proveedor específico
    @GetMapping(path = "/front/proveedores/{id}/editar")
    public String getFormEditar(@PathVariable int id, Model model) {
        ProveedorDTO proveedorDTO = proveedorWebClientService.getProveedorById(id);
        model.addAttribute("proveedor", proveedorDTO);
        return "formEditarProveedor";
    }

    // Actualizar un proveedor existente
    @PutMapping(path = "/front/proveedores/editar")
    public String actualizarProveedor(@RequestBody ProveedorDTO proveedorDTO, Model model) {
        CategoriaDTO proveedorActualizado = proveedorWebClientService.actualizaProveedor(proveedorDTO);
        model.addAttribute("proveedor", proveedorActualizado);
        return "formEditarProveedor";
    }
}
