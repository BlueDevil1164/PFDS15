package mx.unam.dgtic.auth.clienteweb.services;

import mx.unam.dgtic.auth.dto.CategoriaDTO;
import mx.unam.dgtic.auth.dto.ProveedorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProveedorWebClientService {

    @Autowired
    private WebClient webClient;

    // Obtener todos los proveedores
    public List<ProveedorDTO> getAll() {
        Mono<List<ProveedorDTO>> proveedoresMono = webClient.get()
                .uri("/")
                .retrieve()
                .bodyToFlux(ProveedorDTO.class)
                .collectList();
        return proveedoresMono.block();
    }

    // Obtener un proveedor por ID
    public ProveedorDTO getProveedorById(int id) {
        Mono<ProveedorDTO> proveedorMono = webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(ProveedorDTO.class);
        return proveedorMono.block();
    }

    // Actualizar un proveedor existente
    public ProveedorDTO updateProveedor(ProveedorDTO proveedorDTO) {
        return webClient.put()
                .uri("/{id}", proveedorDTO.getId())
                .bodyValue(proveedorDTO)
                .retrieve()
                .bodyToMono(ProveedorDTO.class)
                .block();
    }

    // Crear un nuevo proveedor
    public ProveedorDTO createProveedor(ProveedorDTO proveedorDTO) {
        return webClient.post()
                .uri("/")
                .bodyValue(proveedorDTO)
                .retrieve()
                .bodyToMono(ProveedorDTO.class)
                .block();
    }

    // Eliminar un proveedor por ID
    public void deleteProveedor(int id) {
        webClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public CategoriaDTO actualizaProveedor(ProveedorDTO proveedorDTO){
        return webClient.put().uri("/{matricula}", proveedorDTO.getId())
                .bodyValue(proveedorDTO)
                .retrieve()
                .bodyToMono(CategoriaDTO.class)
                .block();
    }
}
