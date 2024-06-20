package org.example;
import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})

public @interface GenerateElement {
    String value();
}
