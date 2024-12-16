package mx.unam.dgtic.system.servicio.electronico;

import mx.unam.dgtic.system.model.Electronico;

import java.util.List;
import java.util.Optional;

public interface IElectronicoService {

    public List<Electronico> getElectronicosList();

    public Optional<Electronico> getElectronicoById(String matricula);

    Electronico updateElectronico(Electronico electronico);

    Electronico createElectronico(Electronico electronico);

    public boolean deleteElectronico(String matricula);

    public List<Electronico> findElectronicosByCategoria(String categoria);

}

