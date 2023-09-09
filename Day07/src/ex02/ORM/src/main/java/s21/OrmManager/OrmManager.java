package s21.OrmManager;

import com.google.auto.service.AutoService;

import s21.helpers.*;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes("edu.school21.Annotation.Annotations.HtmlForm")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(OrmInput.class)
public class OrmManager extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(OrmForm.class);
        for (Element element : annotatedElements) {
            List<? extends Element> elements = element.getEnclosedElements();
            List<Annotation> fields = new ArrayList<>(elements.size());
            for(Element element1 : elements) {
                if(element1.getAnnotation(OrmInput.class) != null) {
                    Annotation field = element1.getAnnotation(OrmInput.class);
                    fields.add(field);
                }
            }
            createHTML(element.getAnnotation(OrmForm.class), fields);
        }
        return true;
    }

    public void createHTML(OrmForm form, List<Annotation> fields) {
        try {
            FileObject fileObject = processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT,
                    "", form.fileName());
            try (PrintWriter printWriter = new PrintWriter(fileObject.openWriter())) {
                printWriter.println(String.format("<form action = \"%s\" method = \"%s\">", form.action(), form.method()));
                for (Annotation field : fields) {
                    OrmInput input = (OrmInput)field;
                    printWriter.println(String.format("<input type = \"%s\" name = \"%s\" placeholder = \"%s\">",
                            input.type(), input.name(), input.placeholder()));
                }
                printWriter.println("<input type = \"submit\" value = \"Send\">");
                printWriter.println("</form>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
