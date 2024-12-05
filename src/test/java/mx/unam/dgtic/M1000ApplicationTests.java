package mx.unam.dgtic;

import mx.unam.dgtic.model.Electronico;
import mx.unam.dgtic.repository.ElectronicoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
