package mx.unam.dgtic.system.servicio.comprador;

import mx.unam.dgtic.system.exception.CompradorNoExisteExepcion;
import mx.unam.dgtic.system.model.Comprador;
import mx.unam.dgtic.system.repository.CompradorRepository;
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
public class CompradorService implements ICompradorService {

    private static final Logger logger = LoggerFactory.getLogger(CompradorService.class);

    @Autowired
    private CompradorRepository compradorRepository;

    @Override
    public List<Comprador> getCompradoresList() {
        logger.info("Obteniendo la lista de todos los compradores");
        return compradorRepository.findAll();
    }

    @Override
    public Optional<Comprador> getCompradorById(int id) {
        logger.info("Buscando comprador con ID: {}", id);
        return compradorRepository.findById(id);
    }

    @Override
    public Comprador updateComprador(Comprador comprador) throws CompradorNoExisteExepcion {
        logger.info("Actualizando comprador con ID: {}", comprador.getId());
        if (!compradorRepository.existsById(comprador.getId())) {
            throw new CompradorNoExisteExepcion("El comprador con ID " + comprador.getId() + " no existe.");
        }
        return compradorRepository.save(comprador);
    }

    @Override
    public Comprador createComprador(Comprador comprador) {
        logger.info("Creando nuevo comprador: {}", comprador);
        if (comprador.getNombre() == null || comprador.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del comprador no puede estar vacío");
        }
        return compradorRepository.save(comprador);
    }

    @Override
    public boolean deleteComprador(int id) throws CompradorNoExisteExepcion {
        logger.info("Eliminando comprador con ID: {}", id);
        Comprador comprador = compradorRepository.findById(id)
                .orElseThrow(() -> new CompradorNoExisteExepcion("El comprador no existe con ID: " + id));
        compradorRepository.delete(comprador);
        return true;
    }


/*
    @Override
    public List<Comprador> findCompradoresByGenero(String genero) {
        return List.of();
    }

 */

    public Page<Comprador> getCompradoresPageable(int page, int size, String sortDir, String sortField) {
        logger.info("Obteniendo compradores con paginación y ordenación");
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sortField);
        return compradorRepository.findAll(pageRequest);
    }
}
