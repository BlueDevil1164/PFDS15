package mx.unam.dgtic.clienteweb.controller;

import mx.unam.dgtic.clienteweb.services.ElectronicoFrontService;
import mx.unam.dgtic.clienteweb.services.ElectronicoWebClientService;
import mx.unam.dgtic.dto.ElectronicoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ElectronicoFrontController {

    @Autowired
    private ElectronicoFrontService service;

    @Autowired
    private ElectronicoWebClientService electronicoWebClientService;

    @GetMapping(path = "/front/electronicos/{matricula}")
    public String getElectronico(
            @PathVariable String matricula,
            Model model
    ){
        ElectronicoDTO electronicoDTO = service.getElectronicoByMatricula(matricula);
        model.addAttribute("electronico", electronicoDTO);
        return "electronicodetalle";
    }

    @GetMapping(path = "/front/electronicos/")
    public String getAllElectronicos(Model model){
        model.addAttribute("electronicos", electronicoWebClientService.getAll());
        return "electronicos";
    }

    @GetMapping(path = "/front/electronicos/{matricula}/editar")
    public String getFormEditar(@PathVariable String matricula, Model model){
        ElectronicoDTO electronicoDTO = electronicoWebClientService.getElectronicoByMatricula(matricula);
        model.addAttribute("electronico", electronicoDTO);
        return "formEditar";
    }

    @PutMapping(path = "/front/electronicos/editar")
    public String actualizarElectronico(@RequestBody ElectronicoDTO electronicoDTO, Model model){
        ElectronicoDTO electronicoActualizado = electronicoWebClientService.actualizaElectronico(electronicoDTO);
        model.addAttribute("electronico", electronicoActualizado);
        return "formEditar";
    }

}
