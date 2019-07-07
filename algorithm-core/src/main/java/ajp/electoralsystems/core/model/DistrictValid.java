package ajp.electoralsystems.core.model;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { DistrictValidator.class })
@Documented
public @interface DistrictValid {

    String message() default "Error.InvalidDistrictData";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}