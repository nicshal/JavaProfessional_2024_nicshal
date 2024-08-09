package ru.nicshal.advanced.homework.runner;

import ru.nicshal.advanced.homework.runner.data.TestStatus;
import ru.nicshal.advanced.homework.runner.data.TestResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MethodsInvoker {

    private final Class<?> clazz;
    private Object instance;

    private MethodsInvoker(Class<?> clazz) {
        this.clazz = clazz;
    }

    public static MethodsInvoker of(Class<?> clazz) {
        return new MethodsInvoker(clazz);
    }

    public Object instance(Object... args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (instance == null) {
            instance = clazz.getConstructor().newInstance(args);
        }
        return instance;
    }

    public List<TestResult> invokeSupportMethods(Class<? extends Annotation> annotation) {
        Method[] methods = clazz.getDeclaredMethods();
        return Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(annotation))
                .map(method -> {
                    try {
                        method.invoke(instance());
                        return new TestResult(clazz.getName(), method.getName(), TestStatus.SUCCESS, "");
                    } catch (InvocationTargetException ex) {
                        return new TestResult(clazz.getName(), method.getName(), TestStatus.ERROR,
                                String.format("Вызов метода %s завершился с ошибкой: %s", method.getName(), ex.getTargetException().getMessage()));
                    } catch (Exception ex) {
                        return new TestResult(clazz.getName(), method.getName(), TestStatus.ERROR,
                                String.format("Ошибка при вызове метода %s: [%s]", method.getName(), ex.getMessage()));
                    }
                }).collect(Collectors.toList());
    }

    public TestResult invokeTestMethod(Method method) {
        try {
            method.invoke(instance());
            return new TestResult(clazz.getName(), method.getName(), TestStatus.SUCCESS, "");
        } catch (InvocationTargetException ex) {
            return new TestResult(clazz.getName(), method.getName(), TestStatus.ERROR,
                    String.format("Тест %s не пройден. Ошибка: %s", method.getName(), ex.getTargetException().getMessage()));
        } catch (Exception ex) {
            return new TestResult(clazz.getName(), method.getName(), TestStatus.ERROR,
                    String.format("Ошибка при вызове тестового метода %s: [%s]", method.getName(), ex.getMessage()));
        }
    }

}
