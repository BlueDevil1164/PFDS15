package mx.unam.dgtic.system.clienteweb.services;

import mx.unam.dgtic.system.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CategoriaWebClientService {

    @Autowired
    private WebClient webClient;

    // Obtener todas las categorías
    public List<CategoriaDTO> getAll() {
        Mono<List<CategoriaDTO>> categoriasMono = webClient.get()
                .uri("/")
                .retrieve()
                .bodyToFlux(CategoriaDTO.class)
                .collectList();
        return categoriasMono.block();
    }

    // Obtener una categoría por ID
    public CategoriaDTO getCategoriaById(int id) {
        Mono<CategoriaDTO> categoriaMono = webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(CategoriaDTO.class);
        return categoriaMono.block();
    }

    // Actualizar una categoría existente
    public CategoriaDTO updateCategoria(CategoriaDTO categoriaDTO) {
        return webClient.put()
                .uri("/{id}", categoriaDTO.getIdCategoria())
                .bodyValue(categoriaDTO)
                .retrieve()
                .bodyToMono(CategoriaDTO.class)
                .block();
    }

    // Crear una nueva categoría
    public CategoriaDTO createCategoria(CategoriaDTO categoriaDTO) {
        return webClient.post()
                .uri("/")
                .bodyValue(categoriaDTO)
                .retrieve()
                .bodyToMono(CategoriaDTO.class)
                .block();
    }

    // Eliminar una categoría por ID
    public void deleteCategoria(int id) {
        webClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public CategoriaDTO actualizaCategoria(CategoriaDTO categoriaDTO){
        return webClient.put().uri("/{matricula}", categoriaDTO.getIdCategoria())
                .bodyValue(categoriaDTO)
                .retrieve()
                .bodyToMono(CategoriaDTO.class)
                .block();
    }
}
