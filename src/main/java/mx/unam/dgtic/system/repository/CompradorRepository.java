package mx.unam.dgtic.system.repository;

import mx.unam.dgtic.system.model.Comprador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompradorRepository extends JpaRepository<Comprador, Integer> {

    // Consultas por Nombre
    List<Comprador> findByNombre(String nombre);

    List<Comprador> findByNombreNot(String nombre);

    // Contar
    long countByNombre(String nombre);

    long countByNombreNot(String nombre);

    // Consultas por Edad
    List<Comprador> getByEdad(int edad);

    List<Comprador> findByEdadLessThan(int edad);

    List<Comprador> findByEdadLessThanEqual(int edad);

    List<Comprador> findByEdadGreaterThan(int edad);

    List<Comprador> findByEdadGreaterThanEqual(int edad);

    // Consultas por Género
    List<Comprador> findByGenero(String genero);

    List<Comprador> findByGeneroNot(String genero);

    // Usar NULL
    List<Comprador> streamByGeneroIsNull();

    List<Comprador> streamByGeneroIsNotNull();

    long countByGeneroIsNull();

    long countByGeneroIsNotNull();

    // Combinar campos con AND / OR
    List<Comprador> queryByNombreAndEdad(String nombre, int edad);

    List<Comprador> queryByNombreOrGenero(String nombre, String genero);

    long countByNombreAndEdad(String nombre, int edad);

    long countByNombreOrGenero(String nombre, String genero);

    boolean existsByNombreAndGenero(String nombre, String genero);

    // Patrones
    List<Comprador> findByNombreStartingWith(String prefijo);

    List<Comprador> findByNombreContaining(String contiene);

    List<Comprador> findByNombreEndingWith(String sufijo);

    // Consultas Personalizadas (opcional)
    @Query("SELECT c FROM Comprador c WHERE c.nombre LIKE %:patron%")
    List<Comprador> buscarCompradoresPorPatron(@Param("patron") String patron);
}
