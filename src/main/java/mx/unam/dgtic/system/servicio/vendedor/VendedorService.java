package mx.unam.dgtic.system.servicio.vendedor;

import mx.unam.dgtic.system.exception.VendedorNoExisteExepcion;
import mx.unam.dgtic.system.model.Vendedor;
import mx.unam.dgtic.system.repository.VendedorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService implements IVendedorService {

    private static final Logger logger = LoggerFactory.getLogger(VendedorService.class);

    @Autowired
    private VendedorRepository vendedorRepository;

    @Override
    public List<Vendedor> getVendedoresList() {
        logger.info("Obteniendo la lista de todos los vendedores");
        return vendedorRepository.findAll();
    }

    @Override
    public Optional<Vendedor> getVendedorById(int id) {
        logger.info("Buscando comprador con ID: {}", id);
        return vendedorRepository.findById(id);
    }

    @Override
    public Vendedor updateVendedor(Vendedor vendedor) throws VendedorNoExisteExepcion {
        logger.info("Actualizando vendedor con ID: {}", vendedor.getId());
        if (!vendedorRepository.existsById(vendedor.getId())) {
            throw new VendedorNoExisteExepcion("El vendedor con ID " + vendedor.getId() + " no existe.");
        }
        return vendedorRepository.save(vendedor);
    }

    @Override
    public Vendedor createVendedor(Vendedor vendedor) {
        logger.info("Creando nuevo comprador: {}", vendedor);
        if (vendedor.getNombre() == null || vendedor.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del comprador no puede estar vacío");
        }
        return vendedorRepository.save(vendedor);
    }

    @Override
    public boolean deleteVendedor(int id) throws VendedorNoExisteExepcion {
        logger.info("Eliminando comprador con ID: {}", id);
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new VendedorNoExisteExepcion("El vendedor no existe con ID: " + id));
        vendedorRepository.delete(vendedor);
        return true;
    }

    public Page<Vendedor> getVendedoresPageable(int page, int size, String sortDir, String sortField) {
        logger.info("Obteniendo vendedores con paginación y ordenación");
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sortField);
        return vendedorRepository.findAll(pageRequest);
    }
}
