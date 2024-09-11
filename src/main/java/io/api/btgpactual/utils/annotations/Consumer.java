package io.api.btgpactual.utils.annotations;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Component
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Consumer {
    @AliasFor(annotation = Component.class)
    String value() default "";
}
