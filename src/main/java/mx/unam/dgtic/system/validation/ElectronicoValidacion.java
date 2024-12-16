package mx.unam.dgtic.system.validation;


import mx.unam.dgtic.system.model.Electronico;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ElectronicoValidacion implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Electronico electronico=(Electronico) target;
        if(electronico.getNombre()==null
                || electronico.getNombre().regionMatches(0," ",0,1)
                || electronico.getNombre().isBlank()){
            errors.rejectValue("nombre","NotEmpty.electronico.nombre");
        }
    }
}


