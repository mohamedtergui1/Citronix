package ma.tr.citronix.validation.mounth;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class MonthRangeValidator implements ConstraintValidator<ValidMonthRange, LocalDate> {
    private int min;
    private int max;

    public void initialize(ValidMonthRange constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (localDate == null) {
            return true;
        }
        int month = localDate.getMonthValue();
        if (month < min || month > max) {
            if(constraintValidatorContext.getDefaultConstraintMessageTemplate().isEmpty()) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate(
                        "The month must be between " + min + " and " + max + "."
                ).addConstraintViolation();
            }

            return false;
        }
        return true;
    }
}
