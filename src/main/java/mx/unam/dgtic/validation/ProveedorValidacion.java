package mx.unam.dgtic.validation;

import mx.unam.dgtic.auth.model.Proveedor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProveedorValidacion implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Proveedor proveedor=(Proveedor) target;
        if(proveedor.getProveedor()==null
                || proveedor.getProveedor().regionMatches(0," ",0,1)
                || proveedor.getProveedor().isBlank()){
            errors.rejectValue("nombre","NotEmpty.proveedor.nombre");
        }
    }
}
