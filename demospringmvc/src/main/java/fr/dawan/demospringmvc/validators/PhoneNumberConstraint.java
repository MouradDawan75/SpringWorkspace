package fr.dawan.demospringmvc.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberConstraint {

    String message() default "Invalide phone number";

    /*
    Params nécessaires à Spring
    Spring les utilise en arrière plan pour traiter l'annotation
     */
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
}
