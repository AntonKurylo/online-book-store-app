package mate.academy.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

@Component
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        String fieldValue = (String) new BeanWrapperImpl(value).getPropertyValue(field);
        String fieldMatchValue = (String) new BeanWrapperImpl(value).getPropertyValue(fieldMatch);
        return fieldValue != null && fieldValue.equals(fieldMatchValue);
    }
}
