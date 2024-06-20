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


@SupportedAnnotationTypes("org.example.GenerateBuilder")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
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
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        messager.printMessage(Diagnostic.Kind.NOTE, "Starting annotation processing...");

        Map<ClassName, List<ElementInfo>> result = new HashMap<>();
        for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(Generate.class)) {
            messager.printMessage(Diagnostic.Kind.NOTE, "Processing @Generate on " + annotatedElement.toString());
            if (annotatedElement.getKind() != ElementKind.INTERFACE) {
                error("Only interfaces can be annotated with @Generate", annotatedElement);
                return true;
            }
            TypeElement typeElement = (TypeElement) annotatedElement;
            ClassName className = ClassName.get(typeElement);
            result.putIfAbsent(className, new ArrayList<>());
        }

        for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(GenerateElement.class)) {
            if (annotatedElement.getKind() != ElementKind.CLASS) {
                error("Only classes can be annotated with @GenerateElement", annotatedElement);
                return true;
            }
            GenerateElement autoElement = annotatedElement.getAnnotation(GenerateElement.class);
            TypeElement typeElement = (TypeElement) annotatedElement;
            ClassName className = ClassName.get(typeElement);
            List<? extends TypeMirror> list = typeElement.getInterfaces();
            for (TypeMirror typeMirror : list) {
                ClassName typeName = getName(typeMirror);
                if (result.containsKey(typeName)) {
                    result.get(typeName).add(new ElementInfo(className));
                    break;
                }
            }
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
    private void note(String message) {
        messager.printMessage(Diagnostic.Kind.NOTE, message);
    }

}
