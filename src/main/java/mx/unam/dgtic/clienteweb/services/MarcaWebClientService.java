package mx.unam.dgtic.clienteweb.services;

import mx.unam.dgtic.dto.ElectronicoDTO;
import mx.unam.dgtic.dto.MarcaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MarcaWebClientService {

    @Autowired
    private WebClient webClient;

    // Obtener todas las marcas
    public List<MarcaDTO> getAll() {
        Mono<List<MarcaDTO>> marcasMono = webClient.get()
                .uri("/")
                .retrieve()
                .bodyToFlux(MarcaDTO.class)
                .collectList();
        return marcasMono.block();
    }

    // Obtener una marca por ID
    public MarcaDTO getMarcaById(int id) {
        Mono<MarcaDTO> marcaMono = webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(MarcaDTO.class);
        return marcaMono.block();
    }

    // Actualizar una marca existente
    public MarcaDTO updateMarca(MarcaDTO marcaDTO) {
        return webClient.put()
                .uri("/{id}", marcaDTO.getId())
                .bodyValue(marcaDTO)
                .retrieve()
                .bodyToMono(MarcaDTO.class)
                .block();
    }

    // Crear una nueva marca
    public MarcaDTO createMarca(MarcaDTO marcaDTO) {
        return webClient.post()
                .uri("/")
                .bodyValue(marcaDTO)
                .retrieve()
                .bodyToMono(MarcaDTO.class)
                .block();
    }

    // Eliminar una marca por ID
    public void deleteMarca(int id) {
        webClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
    public MarcaDTO actualizaMarca(MarcaDTO marcaDTO){
        return webClient.put().uri("/{matricula}", marcaDTO.getElectronicoMatricula())
                .bodyValue(marcaDTO)
                .retrieve()
                .bodyToMono(MarcaDTO.class)
                .block();
    }
}
