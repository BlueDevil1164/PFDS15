package mx.unam.dgtic.servicio.electronico;

import mx.unam.dgtic.auth.model.Electronico;
import mx.unam.dgtic.auth.repository.ElectronicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElectronicoService implements IElectronicoService {

    @Autowired
    private ElectronicoRepository electronicoRepository;

    @Override
    public List<Electronico> getElectronicosList() {
        return electronicoRepository.findAll();
    }

    @Override
    public Optional<Electronico> getElectronicoById(String matricula) {
        return electronicoRepository.findById(matricula);
    }

    @Override
    public Electronico updateElectronico(Electronico electronico) {
        return electronicoRepository.save(electronico);
    }

    @Override
    public Electronico createElectronico(Electronico electronico) {
        return electronicoRepository.save(electronico);
    }

    @Override
    public boolean deleteElectronico(String matricula) {
        Optional<Electronico> electronico = electronicoRepository.findById(matricula);
        if (electronico.isPresent()) {
            electronicoRepository.delete(electronico.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Electronico> findElectronicosByCategoria(String categoria) {
        return electronicoRepository.findByCategoriaCategoria(categoria);
    }

}
