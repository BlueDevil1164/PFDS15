package mx.unam.dgtic.servicio.proveedor;

import mx.unam.dgtic.model.Proveedor;
import mx.unam.dgtic.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService implements IProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> getProveedoresList() {
        return (List<Proveedor>) proveedorRepository.findAll();
    }

    @Override
    public Optional<Proveedor> getProveedorById(Integer id) {
        return proveedorRepository.findById(id);
    }

    @Override
    public Proveedor updateProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor createProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public boolean deleteProveedor(Integer id) {
        Optional<Proveedor> proveedor = proveedorRepository.findById(id);
        if (proveedor.isPresent()) {
            proveedorRepository.delete(proveedor.get());
            return true;
        } else {
            return false;
        }
    }

}

