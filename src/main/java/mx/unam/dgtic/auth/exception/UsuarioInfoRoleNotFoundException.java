package mx.unam.dgtic.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UsuarioInfoRoleNotFoundException extends Exception {
    public UsuarioInfoRoleNotFoundException(Long id) {
        super("User Info Role with id " + id + " is NOT found");
    }
}
