package mx.unam.dgtic.system.clienteweb.services;

import mx.unam.dgtic.system.dto.MarcaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class MarcaFrontService {

    private static final String API_URL = "http://127.0.0.1:8080/api/v2/marcas";

    @Autowired
    private RestTemplate restTemplate;

    // Obtener una marca por ID
    public MarcaDTO getMarcaById(int id) {
        String url = API_URL + "/" + id;
        return restTemplate.getForObject(url, MarcaDTO.class);
    }

    // Obtener todas las marcas
    public List<MarcaDTO> getAllMarcas() {
        String url = API_URL + "/";
        return Arrays.asList(restTemplate.getForObject(url, MarcaDTO[].class));
    }

    // Crear una nueva marca
    public MarcaDTO createMarca(MarcaDTO marcaDTO) {
        return restTemplate.postForObject(API_URL + "/", marcaDTO, MarcaDTO.class);
    }

    // Actualizar una marca existente
    public MarcaDTO updateMarca(int id, MarcaDTO marcaDTO) {
        String url = API_URL + "/" + id;
        restTemplate.put(url, marcaDTO);
        return marcaDTO;
    }

    // Eliminar una marca por ID
    public void deleteMarca(int id) {
        String url = API_URL + "/" + id;
        restTemplate.delete(url);
    }
}
