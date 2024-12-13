package mx.unam.dgtic.validation;

import mx.unam.dgtic.auth.model.Comprador;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CompradorValidacion implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Comprador comprador=(Comprador) target;
        if(comprador.getNombre()==null
                || comprador.getNombre().regionMatches(0," ",0,1)
                || comprador.getNombre().isBlank()){
            errors.rejectValue("nombre","NotEmpty.vendedor.nombre");
        }
    }
}
