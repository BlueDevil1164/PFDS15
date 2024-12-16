package mx.unam.dgtic.security.service;

import lombok.extern.slf4j.Slf4j;
import mx.unam.dgtic.auth.model.UsuarioInfo;
import mx.unam.dgtic.auth.repository.UsuarioInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UsuarioDetailsServiceImpl implements UserDetailsService {
    private final UsuarioInfoRepository usuarioInfoRepository;

    @Autowired
    public UsuarioDetailsServiceImpl(UsuarioInfoRepository usuarioInfoRepository) {
        this.usuarioInfoRepository = usuarioInfoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Security - UserDetailsServiceImpl.loadUserByUsername {}", username);
       UsuarioInfo userInfo = (UsuarioInfo) Optional.ofNullable(usuarioInfoRepository.findByUsuEmail(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found in database"));
        String userName = userInfo.getUsuEmail();
        String password = userInfo.getUsuPasswd();
        List<GrantedAuthority> authorities = userInfo.getUsuInfoRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getUserRoleName())).collect(Collectors.toList());
        return new User(userName, password, authorities);
    }
}
