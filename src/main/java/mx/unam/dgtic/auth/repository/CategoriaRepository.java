package mx.unam.dgtic.auth.repository;

import mx.unam.dgtic.auth.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Categoria findByCategoria(String categoria);

    List<Categoria> findByCategoriaContaining(String categoria);

    @Query(value = "SELECT DISTINCT e.* FROM Categorias e " +
            "JOIN Electronicos a ON e.id_categoria = a.id_categoria " +
            "WHERE a.nombre LIKE %:nombre%", nativeQuery = true)
    List<Categoria> findCategoriasByElectronicoNombreLike(@Param("nombre") String nombre);

    @Query("SELECT c FROM Categoria c WHERE NOT EXISTS (SELECT e FROM Electronico e WHERE e.categoria = c)")
    List<Categoria> findCategoriasSinElectronicos();

    @Query("SELECT c FROM Categoria c WHERE SIZE(c.electronicos) > :minimo")
    List<Categoria> findCategoriasConMinimoElectronicos(@Param("minimo") int minimo);



}
