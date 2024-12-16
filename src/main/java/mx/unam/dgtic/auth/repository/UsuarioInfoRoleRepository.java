package mx.unam.dgtic.auth.repository;


import mx.unam.dgtic.auth.model.UsuarioInfoRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioInfoRoleRepository extends JpaRepository<UsuarioInfoRole, Long> {
    UsuarioInfoRole findByUserRoleName(String role);
    List<UsuarioInfoRole> findAllByOrderByUserIdAsc();
    List<UsuarioInfoRole> findAllByOrderByUserRoleNameAsc();
}
