package mx.unam.dgtic.system.clienteweb.services;

import mx.unam.dgtic.system.dto.CompradorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompradorFrontService {

    public static final String API_URL = "http://127.0.0.1:8080/api/v2/compradores";

    @Autowired
    private RestTemplate restTemplate;

    public CompradorDTO getCompradorById(String id) {
        String url = API_URL + "/" + id;
        return restTemplate.getForObject(url, CompradorDTO.class);
    }
}
