package mx.unam.dgtic.auth.clienteweb.services;

import mx.unam.dgtic.auth.dto.VendedorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class VendedorWebClientService {

    @Autowired
    private WebClient webClient;

    public List<VendedorDTO> getAll() {
        Mono<List<VendedorDTO>> vendedoresMono = webClient.get()
                .uri("/")
                .retrieve()
                .bodyToFlux(VendedorDTO.class)
                .collectList();
        return vendedoresMono.block();
    }

    public VendedorDTO getVendedorById(int id) {
        Mono<VendedorDTO> vendedorDtoMono = webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(VendedorDTO.class);
        return vendedorDtoMono.block();
    }

    public VendedorDTO actualizaVendedor(VendedorDTO vendedorDTO) {
        return webClient.put()
                .uri("/{id}", vendedorDTO.getId())
                .bodyValue(vendedorDTO)
                .retrieve()
                .bodyToMono(VendedorDTO.class)
                .block();
    }
}
