package mx.unam.dgtic.auth.service;



import mx.unam.dgtic.auth.dto.UsuarioInfoRoleDTO;
import mx.unam.dgtic.auth.exception.UsuarioInfoRoleNotFoundException;
import mx.unam.dgtic.auth.model.UsuarioInfoRole;

import java.util.List;

public interface UsuarioInfoRoleService {
    List<UsuarioInfoRoleDTO> findAll();

    List<UsuarioInfoRoleDTO> findAllOrderByUserRoleName();

    UsuarioInfoRoleDTO findById(Long id) throws UsuarioInfoRoleNotFoundException;
    UsuarioInfoRoleDTO save(UsuarioInfoRoleDTO role);
    UsuarioInfoRoleDTO convertEntityToDTO(UsuarioInfoRole compradorInfo);
    UsuarioInfoRole convertDTOtoEntity(UsuarioInfoRoleDTO compradorInfo);
}
