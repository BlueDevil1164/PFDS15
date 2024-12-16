package mx.unam.dgtic.system.validation;

import mx.unam.dgtic.system.model.Categoria;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoriaValidacion implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Categoria categoria=(Categoria) target;
        if(categoria.getCategoria()==null
                || categoria.getCategoria().regionMatches(0," ",0,1)
                || categoria.getCategoria().isBlank()){
            errors.rejectValue("categoria","NotEmpty.categoria.categoria");
        }
    }
}
