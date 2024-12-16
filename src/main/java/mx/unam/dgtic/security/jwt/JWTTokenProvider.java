package mx.unam.dgtic.security.jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import mx.unam.dgtic.auth.dto.UsuarioInfoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JWTTokenProvider {

    private String secret;
    private int jwtExpirationInMs;
    private SecretKey key;

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        this.secret = secret;
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    @Value("${jwt.expirationDateInMs}")
    public void setJwtExpirationInMs(int jwtExpirationInMs) {
        this.jwtExpirationInMs = jwtExpirationInMs;
    }

    /**
     * Generate a JWT token for the authenticated user.
     */
    public String generateJwtToken(Authentication authentication, UsuarioInfoDTO user) {
        Claims claims = Jwts.claims()
                .setSubject("UNAM")
                .setIssuer(user.getUsuEmail())
                .setAudience("JAVA");

        claims.put("principal", authentication.getPrincipal());
        claims.put("auth", authentication.getAuthorities().stream()
                .map(s -> new SimpleGrantedAuthority(s.getAuthority()))
                .collect(Collectors.toList()));
        claims.put("issid", user.getUsuId());
        claims.put("issname", user.getUsuFirstName() + " " + user.getUsuLastName());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Extract claims from the token.
     */
    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    /**
     * Extract specific claims.
     */
    public String getFullName(String token) {
        return (String) getClaims(token).get("issname");
    }

    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }

    public String getIssuer(String token) {
        return getClaims(token).getIssuer();
    }

    public String getAudience(String token) {
        return getClaims(token).getAudience();
    }

    public Date getTokenExpiryFromJWT(String token) {
        return getClaims(token).getExpiration();
    }

    public Date getTokenIatFromJWT(String token) {
        return getClaims(token).getIssuedAt();
    }

    /**
     * Validate a JWT token.
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (JwtException exception) {
            log.error("Invalid JWT token -> Message: {}", exception.getMessage());
        }
        return false;
    }

    /**
     * Get the configured expiration duration for the token.
     */
    public long getExpiryDuration() {
        return jwtExpirationInMs;
    }
}
