package mx.unam.dgtic.auth.repository;

import mx.unam.dgtic.auth.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {
    List<Marca> findByNombre(String nombre);

    List<Marca> findByRate(int rate);

    List<Marca> findByElectronicoNombre(String nombre);

    List<Marca> findByElectronicoCodigo(String codigo);

}
