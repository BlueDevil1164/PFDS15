package mx.unam.dgtic.servicio.comprador;

import mx.unam.dgtic.auth.exception.CompradorNoExisteExepcion;
import mx.unam.dgtic.auth.model.Comprador;

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