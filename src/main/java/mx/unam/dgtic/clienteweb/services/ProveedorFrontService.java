package mx.unam.dgtic.clienteweb.services;

import mx.unam.dgtic.dto.ProveedorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProveedorFrontService {

    // URL base de la API para proveedores
    private static final String API_URL = "http://127.0.0.1:8080/api/v2/proveedores";

    @Autowired
    private RestTemplate restTemplate;

    // Método para obtener un proveedor por su ID
    public ProveedorDTO getProveedorById(int id) {
        String url = API_URL + "/" + id;
        return restTemplate.getForObject(url, ProveedorDTO.class);
    }

}
