package mx.unam.dgtic.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UsuarioInfoNotFoundException extends Exception {
    public UsuarioInfoNotFoundException(Long id) {
        super("User Info with id " + id + " is NOT found");
    }

    public UsuarioInfoNotFoundException(String email) {
        super("User Email already exists! " + email);
    }
}
