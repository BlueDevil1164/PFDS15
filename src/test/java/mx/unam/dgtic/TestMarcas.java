package mx.unam.dgtic;

import mx.unam.dgtic.auth.repository.MarcaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestMarcas {
    @Autowired
    private MarcaRepository marcaRepository;

    @Test
    public void findAllCals(){
        System.out.println("Test de Calificaciones");
        marcaRepository.findAll().forEach(System.out::println);


    }
}
