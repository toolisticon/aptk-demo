package io.toolisticon.myspiservice.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for triggering spi configuration generation.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
@Documented
public @interface MySpiService {

    /**
     * The service interface type.
     * @return the value
     */
    Class<?> value();


}
