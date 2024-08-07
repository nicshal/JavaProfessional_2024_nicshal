package ru.nicshal.advanced.homework;

import java.lang.reflect.Proxy;

public class Ioc<T> {

    private Ioc() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T createLogging(Class<T> targetInterface, T targetObject) {
        return (T) Proxy.newProxyInstance(
                targetInterface.getClassLoader(),
                new Class<?>[]{targetInterface},
                new LoggingInvocationHandler<>(targetObject)
        );
    }

}
