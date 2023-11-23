package ru.myPackage.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.myPackage.DAO.ModelsDAO;
import ru.myPackage.models.Builder;

@Component
public class BuilderValidator implements Validator {

    private final ModelsDAO modelsDAO;

    @Autowired
    public BuilderValidator(ModelsDAO modelsDAO) {
        this.modelsDAO = modelsDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Builder.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Builder builder = (Builder) target;

//        if (modelsDAO.show(builder.getIdOfEmployee()) != null) {
//            errors.rejectValue("idOfEmployee", "", "This ID is already taken");
//        }
        if (modelsDAO.showByEmail(builder.getEmail()) != null) {
            errors.rejectValue("email", "", "This email is already taken");
        }
    }
}
