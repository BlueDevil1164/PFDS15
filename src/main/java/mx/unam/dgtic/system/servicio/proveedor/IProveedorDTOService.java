package mx.unam.dgtic.system.servicio.proveedor;

import mx.unam.dgtic.system.dto.ProveedorDTO;
import mx.unam.dgtic.system.exception.ProveedorNoExisteExepcion;

import java.util.List;
import java.util.Optional;

public interface IProveedorDTOService {

    List<ProveedorDTO> getProveedoresList();

    List<ProveedorDTO> getProveedoresPageable(int page, int size, String dirSort, String sort);

    Optional<ProveedorDTO> getProveedorById(Integer id);

    ProveedorDTO updateProveedor(ProveedorDTO proveedorDTO) throws ProveedorNoExisteExepcion;

    ProveedorDTO createProveedor(ProveedorDTO proveedorDTO) throws ProveedorNoExisteExepcion;

    boolean deleteProveedor(Integer id);
}
