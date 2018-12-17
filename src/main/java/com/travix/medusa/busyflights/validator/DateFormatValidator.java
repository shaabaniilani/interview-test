package com.travix.medusa.busyflights.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;

public class DateFormatValidator  implements ConstraintValidator<DateFormat, String> {

    private SimpleDateFormat simpleDateFormat;

    public DateFormatValidator() {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
    }

    @Override
    public void initialize(DateFormat constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() != 10) {
            return false;
        }
        try {
            simpleDateFormat.parse(value);
        }catch (Exception ex) {
            return false;
        }
        return true;
    }
}
