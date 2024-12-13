package mx.unam.dgtic.auth.clienteweb.services;

import mx.unam.dgtic.auth.dto.CompradorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CompradorWebClientService {

    @Autowired
    private WebClient webClient;

    public List<CompradorDTO> getAll(){
        Mono<List<CompradorDTO>> compradoresMono = webClient.get()
                .uri("/")
                .retrieve()
                .bodyToFlux(CompradorDTO.class)
                .collectList();
        List<CompradorDTO> compradores = compradoresMono.block();
        return compradores;
    }

    public CompradorDTO getCompradorById(int id){
        Mono<CompradorDTO> compradorDtoMono = webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(CompradorDTO.class);
        CompradorDTO compradorDTO = compradorDtoMono.block();
        return compradorDTO;
    }

    public CompradorDTO actualizaComprador(CompradorDTO compradorDTO) {
        return webClient.put()
                .uri("/{id}", compradorDTO.getId())
                .bodyValue(compradorDTO)
                .retrieve()
                .bodyToMono(CompradorDTO.class)
                .block();
    }


}
