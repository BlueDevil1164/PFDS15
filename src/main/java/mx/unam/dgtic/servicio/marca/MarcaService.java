package mx.unam.dgtic.servicio.marca;

import mx.unam.dgtic.model.Marca;
import mx.unam.dgtic.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService implements IMarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public List<Marca> getMarcasList() {
        return (List<Marca>) marcaRepository.findAll();
    }

    @Override
    public Optional<Marca> getMarcaById(Integer id) {
        return marcaRepository.findById(id);
    }

    @Override
    public Marca updateMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    @Override
    public Marca createMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    @Override
    public boolean deleteMarca(Integer id) {
        Optional<Marca> marca = marcaRepository.findById(id);
        if (marca.isPresent()) {
            marcaRepository.delete(marca.get());
            return true;
        } else {
            return false;
        }
    }

}

