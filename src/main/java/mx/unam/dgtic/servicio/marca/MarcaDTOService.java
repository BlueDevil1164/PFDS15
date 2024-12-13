package mx.unam.dgtic.servicio.marca;

import mx.unam.dgtic.auth.dto.MarcaDTO;
import mx.unam.dgtic.auth.exception.MarcaNoExisteExepcion;
import mx.unam.dgtic.auth.model.Marca;
import mx.unam.dgtic.auth.repository.MarcaRepository;
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
public class MarcaDTOService implements IMarcaDTOService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MarcaDTO> getMarcasList() {
        List<Marca> marcas = marcaRepository.findAll();
        return marcas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<MarcaDTO> getMarcasPageable(int page, int size, String dirSort, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(dirSort), sort);
        Page<Marca> pageResult = marcaRepository.findAll(pageRequest);
        //return pageResult.stream().toList();
        return pageResult.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<MarcaDTO> getMarcaById(Integer id) {
        Optional<Marca> marca = marcaRepository.findById(id);
        if (marca.isPresent()) {
            MarcaDTO marcaDTO = convertToDTO(marca.get());
            return Optional.of(marcaDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public MarcaDTO updateMarca(MarcaDTO marcaDTO) throws MarcaNoExisteExepcion {
        return convertToDTO(marcaRepository.save(this.convertToEntity(marcaDTO)));
    }

    @Override
    public MarcaDTO createMarca(MarcaDTO marcaDTO) throws MarcaNoExisteExepcion {
        return convertToDTO(marcaRepository.save(this.convertToEntity(marcaDTO)));
    }

    @Override
    public boolean deleteMarca(Integer id) {
        Optional<Marca> marca = marcaRepository.findById(id);
        if (marca.isPresent()) {
            marcaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private MarcaDTO convertToDTO(Marca marca) {
        return modelMapper.map(marca, MarcaDTO.class);
    }

    private Marca convertToEntity(MarcaDTO marcaDTO) throws MarcaNoExisteExepcion {
        Marca marca = modelMapper.map(marcaDTO, Marca.class);

        // Si deseas realizar validaciones específicas, como verificar la existencia de la marca,
        // puedes lanzar la excepción personalizada aquí.
        if (marcaDTO.getNombre() == null || marcaDTO.getNombre().isEmpty()) {
            throw new MarcaNoExisteExepcion("La marca no existe o es inválida.");
        }

        return marca;
    }
}
