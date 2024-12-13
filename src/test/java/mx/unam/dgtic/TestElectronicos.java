package mx.unam.dgtic;

import mx.unam.dgtic.auth.repository.CategoriaRepository;
import mx.unam.dgtic.auth.repository.ElectronicoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class TestElectronicos {

    @Autowired
    private ElectronicoRepository electronicoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    @Test
    public void testElectronicos() {
        System.out.println("Test de electronicos");
        electronicoRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void testElectronicosPorNombre() {
        System.out.println("Test de Electronicos por nombre");
        electronicoRepository.findByNombre("Laptop").forEach(System.out::println);
    }


}
