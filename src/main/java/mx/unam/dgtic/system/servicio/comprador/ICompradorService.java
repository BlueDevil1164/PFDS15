package mx.unam.dgtic.system.servicio.comprador;

import mx.unam.dgtic.system.exception.CompradorNoExisteExepcion;
import mx.unam.dgtic.system.model.Comprador;

import java.util.List;
import java.util.Optional;
public interface ICompradorService {

    public List<Comprador> getCompradoresList();

    public Optional<Comprador> getCompradorById(int id);

    Comprador updateComprador(Comprador comprador) throws CompradorNoExisteExepcion;

    Comprador createComprador(Comprador comprador);

    public boolean deleteComprador(int id) throws CompradorNoExisteExepcion;

    //public List<Comprador> findCompradoresByGenero(String genero);
}