package com.javalive09.codebag.processor;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import com.google.auto.service.AutoService;
import com.javalive09.codebag.annotation.Constant;
import com.javalive09.codebag.annotation.Test;
import com.javalive09.codebag.annotation.Tester;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

@AutoService(Processor.class)
public class MyProcessor extends AbstractProcessor {

    private boolean existFile = false;

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if(!existFile) {
            ClassName arrayList = ClassName.get("java.util", "ArrayList");

            TypeSpec.Builder classBuilder = TypeSpec.classBuilder(Constant.CLASS_NAME)
                    .addModifiers(Modifier.FINAL, Modifier.PUBLIC);

            MethodSpec.Builder initBuilder = MethodSpec.methodBuilder(Constant.METHOD_NAME)
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(arrayList)
                    .addStatement("$T $N = new $T()", arrayList, "arrayList", arrayList);

            HashSet<String> hashSet = new HashSet<>();

            for(Element element : roundEnvironment.getElementsAnnotatedWith(Test.class)) {
                if(element.getKind() == ElementKind.METHOD) {
                    ExecutableElement executableElement = (ExecutableElement)element;
                    TypeElement typeElement = (TypeElement) executableElement.getEnclosingElement();
                    String className = typeElement.getQualifiedName().toString();
                    hashSet.add(className);
                }
            }

            for(String className: hashSet) {
                initBuilder.addStatement("arrayList" + ".add($S)", className);
            }

//            for (Element element : roundEnvironment.getElementsAnnotatedWith(Tester.class)) {
//                if(element.getKind() == ElementKind.CLASS) {
//                    TypeElement typeElement = (TypeElement) element;
//                    String className = typeElement.getQualifiedName().toString();
//                    initBuilder.addStatement("arrayList" + ".add($S)", className);
//                }
//            }
            initBuilder.addStatement("return arrayList").build();

            classBuilder.addMethod(initBuilder.build());

            JavaFile javaFile = JavaFile.builder(Constant.PACKAGE_NAME, classBuilder.build()).build();
            try {
                javaFile.writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }
            existFile = true;
        }
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<>();
        set.add(Tester.class.getCanonicalName());
        return set;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
