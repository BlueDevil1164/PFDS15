package mx.unam.dgtic.system.servicio.categoria;

import mx.unam.dgtic.system.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface ICategoriaService {

    public List<Categoria> getCategoriasList();

    public Optional<Categoria> getCategoriaById(Integer id);

    Categoria updateCategoria(Categoria categoria);

    Categoria createCategoria(Categoria categoria);

    public boolean deleteCategoria(Integer id);



}


