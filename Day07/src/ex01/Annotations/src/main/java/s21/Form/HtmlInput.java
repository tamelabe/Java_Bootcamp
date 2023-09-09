package s21.Form;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD})
public @interface HtmlInput {
    String type();
    String name();
    String placeholder();
}