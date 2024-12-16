package mx.unam.dgtic.system.servicio.proveedor;

import mx.unam.dgtic.system.model.Proveedor;

import java.util.List;
import java.util.Optional;

public interface IProveedorService {

    public List<Proveedor> getProveedoresList();

    public Optional<Proveedor> getProveedorById(Integer id);

    Proveedor updateProveedor(Proveedor proveedor);

    Proveedor createProveedor(Proveedor proveedor);

    public boolean deleteProveedor(Integer id);

}



