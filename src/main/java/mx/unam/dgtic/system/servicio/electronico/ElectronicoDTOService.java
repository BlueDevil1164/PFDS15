package mx.unam.dgtic.system.servicio.electronico;

import mx.unam.dgtic.system.dto.ElectronicoDTO;
import mx.unam.dgtic.system.exception.CategoriaNoExisteExepcion;
import mx.unam.dgtic.system.model.Categoria;
import mx.unam.dgtic.system.model.Electronico;
import mx.unam.dgtic.system.repository.CategoriaRepository;
import mx.unam.dgtic.system.repository.ElectronicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ElectronicoDTOService implements IElectronicoDTOService {

    @Autowired
    private ElectronicoRepository electronicoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ElectronicoDTO> getElectronicosList() {
        List<Electronico> electronicos = electronicoRepository.findAll();
        return electronicos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ElectronicoDTO> getElectronicosPageable(int page, int size, String dirSort, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(dirSort), sort);
        Page<Electronico> pageResult = electronicoRepository.findAll(pageRequest);
        //return pageResult.stream().toList();
        return pageResult.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<ElectronicoDTO> getElectronicoById(String matricula) {
        Optional<Electronico> electronico = electronicoRepository.findById(matricula);
        if (electronico.isPresent()) {
            ElectronicoDTO electronicoDTO = convertToDTO(electronico.get());
            return Optional.of(electronicoDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public ElectronicoDTO updateElectronico(ElectronicoDTO electronico) throws ParseException, CategoriaNoExisteExepcion {
        return convertToDTO(electronicoRepository.save(this.convertToEntity(electronico)));
    }

    @Override
    public ElectronicoDTO createElectronico(ElectronicoDTO electronico) throws ParseException, CategoriaNoExisteExepcion {
        return convertToDTO(electronicoRepository.save(this.convertToEntity(electronico)));
    }

    @Override
    public boolean deleteElectronico(String matricula) {
        Optional<Electronico> electronico = electronicoRepository.findById(matricula);
        if (electronico.isPresent()) {
            electronicoRepository.deleteById(matricula);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ElectronicoDTO> findElectronicosByCategoria(String categoria) {
        List<Electronico> electronicos = electronicoRepository.findByCategoriaCategoria(categoria);
        return electronicos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ElectronicoDTO convertToDTO(Electronico electronico) {
        ElectronicoDTO electronicoDTO = modelMapper.map(electronico, ElectronicoDTO.class);
        System.out.println("Electronico2ElectronicoDTO: " + electronicoDTO.toString());
        if (electronico.getCategoria() != null) {
            electronicoDTO.setCategoria(electronico.getCategoria().getCategoria());
        }
        if (electronico.getFfac() != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            electronicoDTO.setFfac(dateFormat.format(electronico.getFfac()));
        }
        return electronicoDTO;
    }

    private Electronico convertToEntity(ElectronicoDTO electronicoDTO) throws ParseException, CategoriaNoExisteExepcion {
        Electronico electronico = modelMapper.map(electronicoDTO, Electronico.class);
        System.out.println("Electronico2ElectronicoDTO: " + electronico.toString());
        if (electronicoDTO.getCategoria() != null && !electronicoDTO.getCategoria().isEmpty()) {
            Categoria categoria = categoriaRepository.findByCategoria(electronicoDTO.getCategoria());
            electronico.setCategoria(categoria);
            if (categoria==null){
                //Lanzar una exception
                throw new CategoriaNoExisteExepcion("La categoria no existe");
            }else {
                electronico.setCategoria(categoria);
            }
        }
        if (electronicoDTO.getFfac() != null && !electronicoDTO.getFfac().isBlank()) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            electronico.setFfac(dateFormat.parse(electronicoDTO.getFfac()));
        } else {
            electronico.setFfac(new SimpleDateFormat("yyyy-MM-dd").parse("1900-01-01"));
        }
        return electronico;
    }

}
