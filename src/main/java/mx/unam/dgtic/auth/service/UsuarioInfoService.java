package mx.unam.dgtic.auth.service;

import mx.unam.dgtic.auth.dto.UsuarioInfoDTO;
import mx.unam.dgtic.auth.exception.UsuarioInfoNotFoundException;

import java.util.List;

public interface UsuarioInfoService {
    List<UsuarioInfoDTO> findAll();
    UsuarioInfoDTO findById(Long id) throws UsuarioInfoNotFoundException;
    UsuarioInfoDTO save(UsuarioInfoDTO userAdmin) throws UsuarioInfoNotFoundException;
    UsuarioInfoDTO findByUseEmail(String email) throws UsuarioInfoNotFoundException;
}
