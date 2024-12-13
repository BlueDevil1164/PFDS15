package mx.unam.dgtic.validation;

import mx.unam.dgtic.auth.model.Vendedor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class VendedorValidacion implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Vendedor vendedor=(Vendedor) target;
        if(vendedor.getNombre()==null
                || vendedor.getNombre().regionMatches(0," ",0,1)
                || vendedor.getNombre().isBlank()){
            errors.rejectValue("nombre","NotEmpty.vendedor.nombre");
        }
    }
}
