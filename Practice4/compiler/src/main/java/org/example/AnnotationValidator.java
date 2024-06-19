package org.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationValidator {

    public static void processAnnotations(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value == null) {
                    throw new Exception("Field '" + field.getName() + "' cannot be null.");
                }
            }
        }
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(MinAge.class)) {
                MinAge minAge = method.getAnnotation(MinAge.class);
                Method getAgeMethod = clazz.getMethod("getAge");
                int age = (int) getAgeMethod.invoke(obj);
                if (age < minAge.value()) {
                    throw new Exception("Car age (" + age + ") is less than the minimum required age of " + minAge.value());
                } else {
                    method.setAccessible(true);
                    method.invoke(obj);
                }
            }
        }
    }

}
