package ma.tr.citronix.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumValidateImpl implements ConstraintValidator<EnumValidate, String> {
    private Enum<?>[] enumValues;
    @Override
    public void initialize(EnumValidate constraintAnnotation) {
        this.enumValues = constraintAnnotation.enumClass().getEnumConstants();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        boolean isValidEnumValue = Arrays.stream(enumValues)
                .anyMatch(enumValue -> enumValue.name().equals(value));

        if (isValidEnumValue) {
            return true;
        } else {

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Invalid value '" + value + "' for " + enumValues[0].getDeclaringClass().getSimpleName() + ". Accepted values are: " +
                            Arrays.toString(enumValues)
            ).addConstraintViolation();
            return false;
        }
    }
}
