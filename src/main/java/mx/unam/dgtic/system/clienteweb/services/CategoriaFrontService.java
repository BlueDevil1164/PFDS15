package mx.unam.dgtic.system.clienteweb.services;

import mx.unam.dgtic.system.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CategoriaFrontService {

    // URL base de la API de categorías
    public static final String API_URL_CATEGORIAS = "http://127.0.0.1:8080/api/v2/categorias";

    @Autowired
    private RestTemplate restTemplate;

    // Método para obtener una categoría por nombre
    public CategoriaDTO getCategoriaByCategoria(String categoria) {
        String url = API_URL_CATEGORIAS + "/" + categoria;
        return restTemplate.getForObject(url, CategoriaDTO.class);
    }

}
