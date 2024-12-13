package mx.unam.dgtic.servicio.vendedor;

import mx.unam.dgtic.auth.exception.VendedorNoExisteExepcion;
import mx.unam.dgtic.auth.model.Vendedor;

import java.util.List;
import java.util.Optional;
public interface IVendedorService {

    public List<Vendedor> getVendedoresList();

    public Optional<Vendedor> getVendedorById(int id);

    Vendedor updateVendedor(Vendedor comprador) throws VendedorNoExisteExepcion;

    Vendedor createVendedor(Vendedor comprador);

    public boolean deleteVendedor(int id) throws VendedorNoExisteExepcion;

    //public List<Comprador> findCompradoresByGenero(String genero);
}