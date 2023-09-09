package s21.Form;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
public @interface HtmlForm {
    String fileName();
    String action();
    String method();
}