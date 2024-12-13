package mx.unam.dgtic.servicio.categoria;

import mx.unam.dgtic.auth.dto.CategoriaDTO;
import mx.unam.dgtic.auth.exception.CategoriaNoExisteExepcion;
import mx.unam.dgtic.auth.model.Categoria;
import mx.unam.dgtic.auth.repository.CategoriaRepository;
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
public class CategoriaDTOService implements ICategoriaDTOService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoriaDTO> getCategoriasList() {
        //List<Categoria> categorias = categoriaRepository.findAll();
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    @Override
    public List<CategoriaDTO> getCategoriasPageable(int page, int size, String dirSort, String sort) {
        // Configurar el objeto PageRequest para la paginación
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(dirSort), sort);

        // Cambiar a Page<Categoria> si es una consulta sobre Categorias
        Page<Categoria> pageResult = categoriaRepository.findAll(pageRequest);

        // Convertir cada Categoria a CategoriaDTO
        return pageResult.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<CategoriaDTO> getCategoriaById(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            CategoriaDTO categoriaDTO = convertToDTO(categoria.get());
            return Optional.of(categoriaDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public CategoriaDTO updateCategoria(CategoriaDTO categoriaDTO) throws CategoriaNoExisteExepcion {
        Categoria categoria = this.convertToEntity(categoriaDTO);
        return convertToDTO(categoriaRepository.save(categoria));
    }

    @Override
    public CategoriaDTO createCategoria(CategoriaDTO categoriaDTO) throws CategoriaNoExisteExepcion {
        Categoria categoria = this.convertToEntity(categoriaDTO);
        return convertToDTO(categoriaRepository.save(categoria));
    }

    @Override
    public boolean deleteCategoria(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            categoriaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Convertir a DTO
    private CategoriaDTO convertToDTO(Categoria categoria) {
        return modelMapper.map(categoria, CategoriaDTO.class);
    }

    private Categoria convertToEntity(CategoriaDTO categoriaDTO) throws CategoriaNoExisteExepcion {
        // Crear la entidad `Categoria` y asignar valores del DTO
        Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);
        System.out.println("CategoriaDTO a Categoria: " + categoria.toString());

        // Validar y asignar la categoría
        if (categoriaDTO.getCategoria() != null && !categoriaDTO.getCategoria().isEmpty()) {
            Categoria categoriaExistente = categoriaRepository.findByCategoria(categoriaDTO.getCategoria());
            if (categoriaExistente == null) {
                // Lanza excepción si la categoría no existe en la base de datos
                throw new CategoriaNoExisteExepcion("La categoría especificada no existe: " + categoriaDTO.getCategoria());
            } else {
                // Asignar los datos existentes de la categoría
                categoria.setIdCategoria(categoriaExistente.getIdCategoria());
                categoria.setCategoria(categoriaExistente.getCategoria());
                categoria.setAbreviatura(categoriaExistente.getAbreviatura());
            }
        } else {
            throw new CategoriaNoExisteExepcion("El nombre de la categoría no puede estar vacío.");
        }

        // Manejar el campo `abreviatura` si existe en el DTO
        if (categoriaDTO.getAbreviatura() != null && !categoriaDTO.getAbreviatura().isEmpty()) {
            categoria.setAbreviatura(categoriaDTO.getAbreviatura());
        } else {
            categoria.setAbreviatura("N/A"); // Asignar valor predeterminado si no se proporciona abreviatura
        }

        return categoria;
    }



}