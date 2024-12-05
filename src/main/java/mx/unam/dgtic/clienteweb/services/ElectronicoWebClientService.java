package mx.unam.dgtic.clienteweb.services;

import mx.unam.dgtic.dto.ElectronicoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ElectronicoWebClientService {

    @Autowired
    private WebClient webClient;


    public List<ElectronicoDTO> getAll(){
        Mono<List<ElectronicoDTO>> electronicosMono = webClient.get()
                .uri("/")
                .retrieve()
                .bodyToFlux(ElectronicoDTO.class)
                .collectList();
        List<ElectronicoDTO> electronicos = electronicosMono.block();
        return electronicos;
    }

    public ElectronicoDTO getElectronicoByMatricula(String matricula){
        Mono<ElectronicoDTO> electronicoDtoMono = webClient.get()
                .uri("/{matricula}", matricula)
                .retrieve()
                .bodyToMono(ElectronicoDTO.class);
        ElectronicoDTO electronicoDTO = electronicoDtoMono.block();
        return electronicoDTO;
    }

    public ElectronicoDTO actualizaElectronico(ElectronicoDTO alumnoDTO){
        return webClient.put().uri("/{matricula}", alumnoDTO.getMatricula())
                .bodyValue(alumnoDTO)
                .retrieve()
                .bodyToMono(ElectronicoDTO.class)
                .block();
    }
}
