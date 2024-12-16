package mx.unam.dgtic.auth.service.impl;

import lombok.extern.slf4j.Slf4j;
import mx.unam.dgtic.auth.dto.UsuarioInfoRoleDTO;
import mx.unam.dgtic.auth.exception.UsuarioInfoRoleNotFoundException;
import mx.unam.dgtic.auth.model.UsuarioInfoRole;
import mx.unam.dgtic.auth.repository.UsuarioInfoRoleRepository;
import mx.unam.dgtic.auth.service.UsuarioInfoRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UsuarioInfoRoleServiceImpl implements UsuarioInfoRoleService {
    private final UsuarioInfoRoleRepository usuarioInfoRoleRepository;

    @Autowired
    public UsuarioInfoRoleServiceImpl(UsuarioInfoRoleRepository compradorInfoRoleRepository) {
        this.usuarioInfoRoleRepository = compradorInfoRoleRepository;
    }

    @Override
    public List<UsuarioInfoRoleDTO> findAll() {
        log.info("Service - UsuarioInfoRoleServiceImpl.findAll");
        List<UsuarioInfoRole> theList = usuarioInfoRoleRepository.findAllByOrderByUserIdAsc();
        return theList.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }


    @Override
    public List<UsuarioInfoRoleDTO> findAllOrderByUserRoleName() {
        log.info("Service - UsuarioInfoRoleServiceImpl.findAll");
        List<UsuarioInfoRole> theList = usuarioInfoRoleRepository.findAllByOrderByUserRoleNameAsc();
        return theList.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public UsuarioInfoRoleDTO findById(Long id) throws UsuarioInfoRoleNotFoundException {
        log.info("Service - UsuarioInfoRoleServiceImpl.findById {}", id);
        UsuarioInfoRole object = usuarioInfoRoleRepository.findById(id).orElseThrow(() ->
                new UsuarioInfoRoleNotFoundException(id));
        return convertEntityToDTO(object);
    }

    @Override
    public UsuarioInfoRoleDTO save(UsuarioInfoRoleDTO role) {
        log.info("Service - UsuarioInfoRoleServiceImpl.save object {} ", role);
        UsuarioInfoRole finalStatus = convertDTOtoEntity(role);
        finalStatus = usuarioInfoRoleRepository.save(finalStatus);
        return convertEntityToDTO(finalStatus);
    }

    public UsuarioInfoRoleDTO convertEntityToDTO(UsuarioInfoRole usuarioInfo) {
        UsuarioInfoRoleDTO dto = new UsuarioInfoRoleDTO();
        dto.setUserId(usuarioInfo.getUserId());
        dto.setUserRoleName(usuarioInfo.getUserRoleName());
        dto.setUserIdStatus(usuarioInfo.getUserIdStatus());
        dto.setUserCreatedBy(usuarioInfo.getUserCreatedBy());
        dto.setUserCreatedDate(usuarioInfo.getUserCreatedDate());
        dto.setUserModifiedBy(usuarioInfo.getUserModifiedBy());
        dto.setUserModifiedDate(usuarioInfo.getUserModifiedDate());
        return dto;
    }

    public UsuarioInfoRole convertDTOtoEntity(UsuarioInfoRoleDTO usuarioInfo) {
        UsuarioInfoRole entity = new UsuarioInfoRole();
        entity.setUserId(usuarioInfo.getUserId());
        entity.setUserRoleName(usuarioInfo.getUserRoleName());
        entity.setUserIdStatus(usuarioInfo.getUserIdStatus());
        entity.setUserCreatedBy(usuarioInfo.getUserCreatedBy());
        entity.setUserCreatedDate(usuarioInfo.getUserCreatedDate());
        entity.setUserModifiedBy(usuarioInfo.getUserModifiedBy());
        entity.setUserModifiedDate(usuarioInfo.getUserModifiedDate());
        return entity;
    }
}
