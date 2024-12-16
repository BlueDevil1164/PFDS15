package mx.unam.dgtic.system.servicio.categoria;

import mx.unam.dgtic.system.dto.CategoriaDTO;
import mx.unam.dgtic.system.exception.CategoriaNoExisteExepcion;

import java.util.List;
import java.util.Optional;

public interface ICategoriaDTOService {

    public List<CategoriaDTO> getCategoriasList();

    public List<CategoriaDTO> getCategoriasPageable(int page, int size, String dirSort, String sort);

    public Optional<CategoriaDTO> getCategoriaById(Integer id);

    CategoriaDTO updateCategoria(CategoriaDTO categoriaDTO) throws CategoriaNoExisteExepcion;

    CategoriaDTO createCategoria(CategoriaDTO categoriaDTO) throws CategoriaNoExisteExepcion;

    public boolean deleteCategoria(Integer id);

}
