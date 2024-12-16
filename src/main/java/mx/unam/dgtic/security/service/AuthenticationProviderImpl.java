package mx.unam.dgtic.security.service;


import lombok.AllArgsConstructor;
import mx.unam.dgtic.auth.model.UsuarioInfo;
import mx.unam.dgtic.auth.repository.UsuarioInfoRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AuthenticationProviderImpl implements AuthenticationProvider {
    private final UsuarioInfoRepository compradorInfoRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        UsuarioInfo compradorAdmin = Optional.ofNullable(compradorInfoRepository.findByUsuEmail(username))
                .orElseThrow(() -> new BadCredentialsException("User not found in database"));
        if (passwordEncoder.matches(pwd, compradorAdmin.getUsuPasswd())) {
            List<GrantedAuthority> authorities = compradorAdmin.getUsuInfoRoles().stream().map(role ->
                    new SimpleGrantedAuthority(role.getUserRoleName())).collect(Collectors.toList());
            return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
        } else {
            throw new BadCredentialsException("Invalid password!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
