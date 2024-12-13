package mx.unam.dgtic.servicio.vendedor;

import mx.unam.dgtic.auth.dto.VendedorDTO;
import mx.unam.dgtic.auth.model.Vendedor;
import mx.unam.dgtic.auth.repository.VendedorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendedorDTOService implements IVendedorDTOService {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<VendedorDTO> getVendedoresList() {
        List<Vendedor> vendedores = vendedorRepository.findAll();
        return vendedores.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<VendedorDTO> getVendedoresPageable(int page, int size, String dirSort, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(dirSort), sort);
        Page<Vendedor> pageResult = vendedorRepository.findAll(pageRequest);
        return pageResult.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<VendedorDTO> getVendedorById(int id) {
        Optional<Vendedor> vendedor = vendedorRepository.findById(id);
        if (vendedor.isPresent()) {
            VendedorDTO vendedorDTO = convertToDTO(vendedor.get());
            return Optional.of(vendedorDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public VendedorDTO updateVendedor(VendedorDTO vendedorDTO) throws ParseException {
        return convertToDTO(vendedorRepository.save(this.convertToEntity(vendedorDTO)));
    }

    @Override
    public VendedorDTO createVendedor(VendedorDTO vendedorDTO) throws ParseException {
        return convertToDTO(vendedorRepository.save(this.convertToEntity(vendedorDTO)));
    }


    @Override
    public boolean deleteVendedor(int id) {
        Optional<Vendedor> vendedor = vendedorRepository.findById(id);
        if (vendedor.isPresent()) {
            vendedorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private VendedorDTO convertToDTO(Vendedor vendedor) {
        VendedorDTO vendedorDTO = modelMapper.map(vendedor, VendedorDTO.class);
        System.out.println("Vendedor2VendedorDTO: " + vendedorDTO.toString());
        return vendedorDTO;
    }

    private Vendedor convertToEntity(VendedorDTO vendedorDTO) throws ParseException {
        Vendedor vendedor = modelMapper.map(vendedorDTO, Vendedor.class);
        System.out.println("VendedorDTO2Vendedor: " + vendedor.toString());
        return vendedor;
    }
}
