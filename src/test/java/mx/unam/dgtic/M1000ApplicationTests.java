package mx.unam.dgtic;

import mx.unam.dgtic.system.model.Electronico;
import mx.unam.dgtic.system.repository.ElectronicoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class M1000ApplicationTests {

	final String USER = "Alejandro Noyola Nazario";

	@Autowired
	ElectronicoRepository electronicoRepository;


	@Test
	void findAll(){
		ArrayList<Electronico>  als =(ArrayList<Electronico>) electronicoRepository.findAll();
		//print all
		als.forEach(System.out::println);
	}





}
