package mx.unam.dgtic.servicio.comprador;

import mx.unam.dgtic.auth.dto.CompradorDTO;
import mx.unam.dgtic.auth.model.Comprador;
import mx.unam.dgtic.auth.repository.CompradorRepository;
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
public class CompradorDTOService implements ICompradorDTOService {

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CompradorDTO> getCompradoresList() {
        List<Comprador> compradores = compradorRepository.findAll();
        return compradores.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CompradorDTO> getCompradoresPageable(int page, int size, String dirSort, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(dirSort), sort);
        Page<Comprador> pageResult = compradorRepository.findAll(pageRequest);
        return pageResult.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<CompradorDTO> getCompradorById(int id) {
        Optional<Comprador> comprador = compradorRepository.findById(id);
        if (comprador.isPresent()) {
            CompradorDTO compradorDTO = convertToDTO(comprador.get());
            return Optional.of(compradorDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public CompradorDTO updateComprador(CompradorDTO compradorDTO) throws ParseException {
        return convertToDTO(compradorRepository.save(this.convertToEntity(compradorDTO)));
    }

    @Override
    public CompradorDTO createComprador(CompradorDTO compradorDTO) throws ParseException {
        return convertToDTO(compradorRepository.save(this.convertToEntity(compradorDTO)));
    }


    @Override
    public boolean deleteComprador(int id) {
        Optional<Comprador> comprador = compradorRepository.findById(id);
        if (comprador.isPresent()) {
            compradorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private CompradorDTO convertToDTO(Comprador comprador) {
        CompradorDTO compradorDTO = modelMapper.map(comprador, CompradorDTO.class);
        System.out.println("Comprador2CompradorDTO: " + compradorDTO.toString());
        return compradorDTO;
    }

    private Comprador convertToEntity(CompradorDTO compradorDTO) throws ParseException {
        Comprador comprador = modelMapper.map(compradorDTO, Comprador.class);
        System.out.println("CompradorDTO2Comprador: " + comprador.toString());
        return comprador;
    }
}
