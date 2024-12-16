package mx.unam.dgtic.system.servicio.electronico;

import mx.unam.dgtic.system.dto.ElectronicoDTO;
import mx.unam.dgtic.system.exception.CategoriaNoExisteExepcion;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface IElectronicoDTOService {

    public List<ElectronicoDTO> getElectronicosList();

    public List<ElectronicoDTO> getElectronicosPageable(int page, int size, String dirSort, String sort);

    public Optional<ElectronicoDTO> getElectronicoById(String matricula);

    ElectronicoDTO updateElectronico(ElectronicoDTO electronico) throws ParseException, CategoriaNoExisteExepcion;

    ElectronicoDTO createElectronico(ElectronicoDTO electronico) throws ParseException, CategoriaNoExisteExepcion;

    public boolean deleteElectronico(String matricula);

    public List<ElectronicoDTO> findElectronicosByCategoria(String categoria);

}
