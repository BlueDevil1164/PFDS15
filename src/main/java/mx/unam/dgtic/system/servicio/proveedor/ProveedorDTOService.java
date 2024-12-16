package mx.unam.dgtic.system.servicio.proveedor;

import mx.unam.dgtic.system.dto.ProveedorDTO;
import mx.unam.dgtic.system.exception.ProveedorNoExisteExepcion;
import mx.unam.dgtic.system.model.Proveedor;
import mx.unam.dgtic.system.repository.ProveedorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorDTOService implements IProveedorDTOService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProveedorDTO> getProveedoresList() {
        List<Proveedor> proveedores = proveedorRepository.findAll();
        return proveedores.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProveedorDTO> getProveedoresPageable(int page, int size, String dirSort, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(dirSort), sort);
        Page<Proveedor> pageResult = proveedorRepository.findAll(pageRequest);
        //return pageResult.stream().toList();
        return pageResult.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<ProveedorDTO> getProveedorById(Integer id) {
        Optional<Proveedor> proveedor = proveedorRepository.findById(id);
        if (proveedor.isPresent()) {
            ProveedorDTO proveedorDTO = convertToDTO(proveedor.get());
            return Optional.of(proveedorDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public ProveedorDTO updateProveedor(ProveedorDTO proveedorDTO) throws ProveedorNoExisteExepcion {
        return convertToDTO(proveedorRepository.save(this.convertToEntity(proveedorDTO)));
    }

    @Override
    public ProveedorDTO createProveedor(ProveedorDTO proveedorDTO) throws ProveedorNoExisteExepcion {
        return convertToDTO(proveedorRepository.save(this.convertToEntity(proveedorDTO)));
    }

    @Override
    public boolean deleteProveedor(Integer id) {
        Optional<Proveedor> proveedor = proveedorRepository.findById(id);
        if (proveedor.isPresent()) {
            proveedorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private ProveedorDTO convertToDTO(Proveedor proveedor) {
        return modelMapper.map(proveedor, ProveedorDTO.class);
    }

    private Proveedor convertToEntity(ProveedorDTO proveedorDTO) throws ProveedorNoExisteExepcion {
        Proveedor proveedor = modelMapper.map(proveedorDTO, Proveedor.class);

        // Si deseas realizar validaciones específicas, como verificar la existencia del proveedor,
        // puedes lanzar la excepción personalizada aquí.
        if (proveedorDTO.getProveedor() == null || proveedorDTO.getProveedor().isEmpty()) {
            throw new ProveedorNoExisteExepcion("El proveedor no existe o es inválido.");
        }

        return proveedor;
    }
}

