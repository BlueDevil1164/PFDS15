package mx.unam.dgtic.auth.clienteweb.services;

import mx.unam.dgtic.auth.dto.ElectronicoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ElectronicoFrontService {

    public static final String API_URL ="http://127.0.0.1:8080/api/v2/electronicos";
    @Autowired
    private RestTemplate restTemplate;

    public ElectronicoDTO getElectronicoByMatricula(String matricula){
        String url = API_URL+"/"+ matricula;
        return restTemplate.getForObject(url, ElectronicoDTO.class);

    }
}
