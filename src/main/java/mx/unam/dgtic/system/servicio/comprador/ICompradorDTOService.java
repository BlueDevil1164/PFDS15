package mx.unam.dgtic.system.servicio.comprador;

import mx.unam.dgtic.system.dto.CompradorDTO;
import mx.unam.dgtic.system.exception.CompradorNoExisteExepcion;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface ICompradorDTOService {

    public List<CompradorDTO> getCompradoresList();

    public List<CompradorDTO> getCompradoresPageable(int page, int size, String dirSort, String sort);

    public Optional<CompradorDTO> getCompradorById(int id);

    CompradorDTO updateComprador(CompradorDTO electronico) throws ParseException, CompradorNoExisteExepcion;

    CompradorDTO createComprador(CompradorDTO electronico) throws ParseException, CompradorNoExisteExepcion;

    public boolean deleteComprador(int id);

    //public List<CompradorDTO> findCompradoresByGenero(int id);

}
