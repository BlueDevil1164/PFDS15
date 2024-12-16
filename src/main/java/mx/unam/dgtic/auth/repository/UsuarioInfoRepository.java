package mx.unam.dgtic.auth.repository;

//import edu.unam.springsecurity.auth.model.UserInfo;
import mx.unam.dgtic.auth.model.UsuarioInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioInfoRepository extends JpaRepository<UsuarioInfo, Long> {
    List<UsuarioInfo> findAllByOrderByUsuIdAsc();
    UsuarioInfo findByUsuEmail(String email);
    boolean existsByUsuEmail(String email);
}
