package mx.unam.dgtic.security.model;

import mx.unam.dgtic.auth.model.UsuarioInfo;
import mx.unam.dgtic.auth.model.UsuarioInfoRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class UsuarioDetailsImpl implements UserDetails {
    private Long id;
    private String name;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;
    private UsuarioInfo userInfo;

    public UsuarioDetailsImpl(UsuarioInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UsuarioDetailsImpl(Long id, String name, String email, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.authorities = authorities;
    }

    public static UsuarioDetailsImpl build(UsuarioInfo usuario) {
        List<GrantedAuthority> authorities = usuario.getUsuInfoRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getUserRoleName())
        ).collect(Collectors.toList());
        return new UsuarioDetailsImpl(
                usuario.getUsuId(),
                usuario.getFullName(),
                usuario.getUsuEmail(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (null == userInfo.getUsuInfoRoles()) {
            return Collections.emptySet();
        }
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        for (UsuarioInfoRole role : userInfo.getUsuInfoRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getUserRoleName()));
        }
        return grantedAuthorities;
    }

    /**
     * getUsername
     * @return username
     */
    @Override
    public String getUsername() {
        return userInfo.getUsuEmail();
    }

    /**
     * getPassword (OTP)
     * @return password
     */
    @Override
    public String getPassword() {
        return userInfo.getUsuPasswd();
    }

    /**
     * getName
     * @return name
     */
    public String getName() {
        return userInfo.getUsuFirstName();
    }

    /**
     * getEmail
     * @return email
     */
    public String getEmail() {
        return userInfo.getUsuEmail();
    }

    /**
     * isEnabled
     * @return if user is enabled
     */
    @Override
    public boolean isEnabled() {
        return userInfo.getUsuIdStatus() == 1;
    }

    /**
     * isAccountNonLocked
     * @return if user is locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * isAccountNonExpired
     * @return if account is not expired
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * isCredentialsNonExpired
     * @return if credential is not expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
