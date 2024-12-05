package mx.unam.dgtic.servicio.marca;

import mx.unam.dgtic.model.Marca;

import java.util.List;
import java.util.Optional;

public interface IMarcaService {

    public List<Marca> getMarcasList();

    public Optional<Marca> getMarcaById(Integer id);

    Marca updateMarca(Marca marca);

    Marca createMarca(Marca marca);

    public boolean deleteMarca(Integer id);

}


