package mx.unam.dgtic.repository;

import mx.unam.dgtic.model.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {


}
