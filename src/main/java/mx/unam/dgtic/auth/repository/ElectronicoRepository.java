package mx.unam.dgtic.auth.repository;

import mx.unam.dgtic.auth.model.Categoria;
import mx.unam.dgtic.auth.model.Electronico;
import mx.unam.dgtic.auth.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ElectronicoRepository extends JpaRepository<Electronico, String> {
    List<Electronico> findByNombre(String nombre);

    List<Electronico> findByNombreNot(String nombre);

    //Contar
    long countByNombre(String nombre);

    long countByNombreNot(String nombre);

    List<Electronico> getByCodigo(String codigo);

    List<Electronico> getByPrecio(double precio);

    List<Electronico> getByFfac(Date ffac);

    //Usar NULL
    List<Electronico> streamByCodigoIsNull();

    List<Electronico> streamByCodigoIsNotNull();

    long countByCodigoIsNull();

    long countByCodigoIsNotNull();

    //Combinar campos con AND / OR
    List<Electronico> queryByNombreAndCodigo(String nombre, String codigo);

    List<Electronico> queryByNombreOrCodigo(String nombre, String codigo);

    List<Electronico> queryByNombreOrCodigoNull(String nombre);

    List<Electronico> queryByNombreOrCodigoAndPrecio(String nombre, String codigo, double precio);

    long countByNombreAndCodigo(String nombre, String codigo);

    long countByNombreOrCodigo(String nombre, String codigo);

    long countByNombreOrCodigoNull(String nombre);

    long countByNombreOrCodigoAndPrecio(String nombre, String codigo, double precio);

    boolean existsByNombreAndCodigo(String nombre, String codigo);

    //Mayor que, Menor que
    List<Electronico> findByFfacBefore(Date fecha);

    List<Electronico> findByFfacAfter(Date fecha);

    List<Electronico> findByPrecioLessThan(double precio);

    List<Electronico> findByPrecioLessThanEqual(double precio);

    List<Electronico> findByPrecioGreaterThan(double precio);

    List<Electronico> findByPrecioGreaterThanEqual(double precio);

    //Patrones
    List<Electronico> findByCodigoStartingWith(String prefijo);

    List<Electronico> findByCodigoContaining(String contiene);

    List<Electronico> findByCodigoEndingWith(String prefijo);

    List<Electronico> findByNombreStartingWith(String prefijo);

    List<Electronico> findByNombreContaining(String contiene);

    List<Electronico> findByNombreEndingWith(String prefijo);

    //Consuta derivada para listar alumnos por estado
    List<Electronico> findByCategoria(Categoria categoria);

    long countByCategoria(Categoria categoria);

    List<Electronico> findByCategoriaCategoria(String categoria);

    // Buscar por abreviatura de la categoría
    List<Electronico> findByCategoriaAbreviatura(String abreviatura);

    // Consulta personalizada (opcional)
    @Query("SELECT g FROM Electronico a JOIN a.proveedores g WHERE a.matricula = :matricula")
    List<Proveedor> findProveedoresByMatricula(@Param("matricula") String matricula);

    List<Electronico> buscarTodosConMarcas();


}
