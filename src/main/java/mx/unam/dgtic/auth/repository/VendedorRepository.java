package mx.unam.dgtic.auth.repository;

import mx.unam.dgtic.auth.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

    // Consultas por Nombre
    List<Vendedor> findByNombre(String nombre);

    List<Vendedor> findByNombreNot(String nombre);

    // Contar
    long countByNombre(String nombre);

    long countByNombreNot(String nombre);

    // Consultas por Edad
    List<Vendedor> getByEdad(int edad);

    List<Vendedor> findByEdadLessThan(int edad);

    List<Vendedor> findByEdadLessThanEqual(int edad);

    List<Vendedor> findByEdadGreaterThan(int edad);

    List<Vendedor> findByEdadGreaterThanEqual(int edad);

    // Consultas por Género
    List<Vendedor> findByGenero(String genero);

    List<Vendedor> findByGeneroNot(String genero);

    // Usar NULL
    List<Vendedor> streamByGeneroIsNull();

    List<Vendedor> streamByGeneroIsNotNull();

    long countByGeneroIsNull();

    long countByGeneroIsNotNull();

    // Combinar campos con AND / OR
    List<Vendedor> queryByNombreAndEdad(String nombre, int edad);

    List<Vendedor> queryByNombreOrGenero(String nombre, String genero);

    long countByNombreAndEdad(String nombre, int edad);

    long countByNombreOrGenero(String nombre, String genero);

    boolean existsByNombreAndGenero(String nombre, String genero);

    // Patrones
    List<Vendedor> findByNombreStartingWith(String prefijo);

    List<Vendedor> findByNombreContaining(String contiene);

    List<Vendedor> findByNombreEndingWith(String sufijo);

    // Consultas Personalizadas (opcional)
    @Query("SELECT v FROM Vendedor v WHERE v.nombre LIKE %:patron%")
    List<Vendedor> buscarVendedoresPorPatron(@Param("patron") String patron);
}
