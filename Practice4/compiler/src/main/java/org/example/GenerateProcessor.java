package org.example;

import com.squareup.javapoet.ClassName;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.*;

public class GenerateProcessor extends AbstractProcessor {
    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Map<ClassName, List<ElementInfo>> result = new HashMap<>();
        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(Generate.class)) {
            if (annotatedElement.getKind() != ElementKind.INTERFACE) {
                error("Only interface can be annotated with AutoFactory", annotatedElement);
                return true;
            }
            TypeElement typeElement = (TypeElement) annotatedElement;
            ClassName className = ClassName.get(typeElement);
            if (!result.containsKey(className)) {
                result.put(className, new ArrayList<>());
            }
        }
        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(Generate.class)) {
            if (annotatedElement.getKind() != ElementKind.CLASS) {
                error("Only class can be annotated with CarInterface", annotatedElement);
                return true;
            }
            GenerateElement carElement = annotatedElement.getAnnotation(GenerateElement.class);
            TypeElement typeElement = (TypeElement) annotatedElement;
            ClassName className = ClassName.get(typeElement);
            List<? extends TypeMirror> list = typeElement.getInterfaces();

        }
        try {
            new FactoryBuilder(filer, result).generate();
        } catch (IOException e) {
            error(e.getMessage());
        }
        return true;
    }

    private ClassName getName(TypeMirror typeMirror) {
        String rawString = typeMirror.toString();
        int dotPosition = rawString.lastIndexOf(".");
        String packageName = rawString.substring(0, dotPosition);
        String className = rawString.substring(dotPosition + 1);
        return ClassName.get(packageName, className);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(Generate.class.getCanonicalName());
        annotations.add(GenerateElement.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private void error(String message, Element element) {
        messager.printMessage(Diagnostic.Kind.ERROR, message, element);
    }

    private void error(String message) {
        messager.printMessage(Diagnostic.Kind.ERROR, message);
    }
}
