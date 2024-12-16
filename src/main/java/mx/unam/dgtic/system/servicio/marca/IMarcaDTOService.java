package mx.unam.dgtic.system.servicio.marca;

import mx.unam.dgtic.system.dto.MarcaDTO;
import mx.unam.dgtic.system.exception.MarcaNoExisteExepcion;

import java.util.List;
import java.util.Optional;

public interface IMarcaDTOService {

    public List<MarcaDTO> getMarcasList();

    public List<MarcaDTO> getMarcasPageable(int page, int size, String dirSort, String sort);

    public Optional<MarcaDTO> getMarcaById(Integer id);

    MarcaDTO updateMarca(MarcaDTO marcaDTO) throws MarcaNoExisteExepcion;

    MarcaDTO createMarca(MarcaDTO marcaDTO) throws MarcaNoExisteExepcion;

    public boolean deleteMarca(Integer id);

}
