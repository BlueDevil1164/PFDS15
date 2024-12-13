package mx.unam.dgtic.validation;

import mx.unam.dgtic.auth.model.Marca;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MarcoValidacion implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Marca marca=(Marca) target;
        if(marca.getNombre()==null
                || marca.getNombre().regionMatches(0," ",0,1)
                || marca.getNombre().isBlank()){
            errors.rejectValue("marca","NotEmpty.marca.marca");
        }
    }
}
