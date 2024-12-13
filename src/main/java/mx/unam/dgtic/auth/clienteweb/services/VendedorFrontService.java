package mx.unam.dgtic.auth.clienteweb.services;

import mx.unam.dgtic.auth.dto.VendedorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VendedorFrontService {

    public static final String API_URL ="http://127.0.0.1:8080/api/v2/vendedores";
    @Autowired
    private RestTemplate restTemplate;

    public VendedorDTO getVendedorById(int id){
        String url = API_URL+"/"+ id;
        return restTemplate.getForObject(url, VendedorDTO.class);

    }
}
