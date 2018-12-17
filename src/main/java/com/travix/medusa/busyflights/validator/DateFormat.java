package com.travix.medusa.busyflights.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateFormatValidator.class)
@Documented
public @interface DateFormat {

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String message() default "{com.travix.medusa.busyflights.validator.DateFormat.message}";
}
