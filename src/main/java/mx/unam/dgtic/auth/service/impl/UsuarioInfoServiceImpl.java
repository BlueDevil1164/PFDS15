package mx.unam.dgtic.auth.service.impl;

import lombok.extern.slf4j.Slf4j;

import mx.unam.dgtic.auth.dto.UsuarioInfoDTO;
import mx.unam.dgtic.auth.dto.UsuarioInfoRoleDTO;
import mx.unam.dgtic.auth.exception.UsuarioInfoNotFoundException;
import mx.unam.dgtic.auth.model.UsuarioInfo;
import mx.unam.dgtic.auth.model.UsuarioInfoRole;
import mx.unam.dgtic.auth.repository.UsuarioInfoRepository;
import mx.unam.dgtic.auth.service.UsuarioInfoRoleService;
import mx.unam.dgtic.auth.service.UsuarioInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UsuarioInfoServiceImpl implements UsuarioInfoService {
    private final UsuarioInfoRepository compradorInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioInfoRoleService userInfoRoleService; //inyectamos el servicio!!! (DIP)

    @Autowired
    public UsuarioInfoServiceImpl(UsuarioInfoRepository userInfoRepository,
                               PasswordEncoder passwordEncoder,
                               UsuarioInfoRoleService userInfoRoleService) {
        this.compradorInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
        this.userInfoRoleService = userInfoRoleService;
    }

    @Override
    public List<UsuarioInfoDTO> findAll() {
        log.info("Service - UserInfoServiceImpl.findAll");
        List<UsuarioInfo> theList = compradorInfoRepository.findAllByOrderByUsuIdAsc();
        return theList.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public UsuarioInfoDTO findById(Long id) throws UsuarioInfoNotFoundException {
        log.info("Service - UserAdmin.findById {}", id);
        UsuarioInfo object = compradorInfoRepository.findById(id).orElseThrow(() ->
                new UsuarioInfoNotFoundException(id));
        return convertEntityToDTO(object);
    }

    @Override
    public UsuarioInfoDTO save(UsuarioInfoDTO usuAdmin) throws UsuarioInfoNotFoundException {
        log.info("Service - UsuarioAdmin.save");
        log.info("Service - UsuarioAdmin.save object {} ", usuAdmin);
        if (existsByUseEmail(usuAdmin.getUsuEmail()))
            throw new UsuarioInfoNotFoundException(usuAdmin.getUsuEmail());
        usuAdmin.setUsuPasswd(passwordEncoder.encode(usuAdmin.getUsuPasswd()));
        UsuarioInfo finalStatus = convertDTOtoEntity(usuAdmin);
        finalStatus = compradorInfoRepository.save(finalStatus);
        return convertEntityToDTO(finalStatus);
    }

    @Override
    public UsuarioInfoDTO findByUseEmail(String email) throws UsuarioInfoNotFoundException {
        UsuarioInfo object = compradorInfoRepository.findByUsuEmail(email);
        if(object == null)
            throw new UsuarioInfoNotFoundException(email);
        return convertEntityToDTO(object);
    }

    private boolean existsByUseEmail(String email){
        return compradorInfoRepository.existsByUsuEmail(email);
    }

    private UsuarioInfoDTO convertEntityToDTO(UsuarioInfo usuarioInfo) {
        UsuarioInfoDTO dto = new UsuarioInfoDTO();
        dto.setUsuId(usuarioInfo.getUsuId());
        dto.setUsuFirstName(usuarioInfo.getUsuFirstName());
        dto.setUsuLastName(usuarioInfo.getUsuLastName());
        dto.setUsuEmail(usuarioInfo.getUsuEmail());
        dto.setUsuPasswd(usuarioInfo.getUsuPasswd());
        dto.setUsuIdStatus(usuarioInfo.getUsuIdStatus());
        dto.setUsuCreatedBy(usuarioInfo.getUsuCreatedBy());
        dto.setUsuCreatedDate(usuarioInfo.getUsuCreatedDate());
        dto.setUsuModifiedBy(usuarioInfo.getUsuModifiedBy());
        dto.setUsuModifiedDate(usuarioInfo.getUsuModifiedDate());
        Set<UsuarioInfoRoleDTO> usuarioInfoRoles = new HashSet<>();
        for (UsuarioInfoRole role : usuarioInfo.getUsuInfoRoles()) {
            usuarioInfoRoles.add(userInfoRoleService.convertEntityToDTO(role));
        }
        dto.setUsuInfoRoles(usuarioInfoRoles);
        /*dto.setUseInfoRoles(userInfo.getUseInfoRoles()
                .stream()
                .map(userInfoRoleService::convertEntityToDTO)
                .collect(Collectors.toSet()));*/
        return dto;
    }

    private UsuarioInfo convertDTOtoEntity(UsuarioInfoDTO usuarioInfo) {
        UsuarioInfo entity = new UsuarioInfo();
        entity.setUsuId(usuarioInfo.getUsuId());
        entity.setUsuFirstName(usuarioInfo.getUsuFirstName());
        entity.setUsuLastName(usuarioInfo.getUsuLastName());
        entity.setUsuEmail(usuarioInfo.getUsuEmail());
        entity.setUsuPasswd(usuarioInfo.getUsuPasswd());
        entity.setUsuIdStatus(usuarioInfo.getUsuIdStatus());
        entity.setUsuCreatedBy(usuarioInfo.getUsuCreatedBy());
        entity.setUsuCreatedDate(usuarioInfo.getUsuCreatedDate());
        entity.setUsuModifiedBy(usuarioInfo.getUsuModifiedBy());
        entity.setUsuModifiedDate(usuarioInfo.getUsuModifiedDate());
        Set<UsuarioInfoRole> usuarioInfoRoles = new HashSet<>();
        for (UsuarioInfoRoleDTO role : usuarioInfo.getUsuInfoRoles()) {
            usuarioInfoRoles.add(userInfoRoleService.convertDTOtoEntity(role));
        }
        entity.setUsuInfoRoles(usuarioInfoRoles);
        /*entity.setUseInfoRoles(userInfo.getUseInfoRoles()
                .stream()
                .map(userInfoRoleService::convertDTOtoEntity)
                .collect(Collectors.toSet()));*/
        return entity;
    }
}
