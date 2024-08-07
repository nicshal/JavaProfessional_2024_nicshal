package ru.nicshal.advanced.homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nicshal.advanced.homework.annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LoggingInvocationHandler<T> implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInvocationHandler.class);

    private final T loggingInterface;
    private final Map<Method, Class<?>[]> methodMap;

    public LoggingInvocationHandler(T loggingInterface) {
        this.loggingInterface = loggingInterface;
        this.methodMap = generateMethodMap(loggingInterface, (method) -> method.isAnnotationPresent(Log.class));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (methodMap
                .entrySet()
                .stream()
                .anyMatch(item -> item.getKey().getName().equals(method.getName())
                        && Arrays.equals(item.getValue(), method.getParameterTypes()))) {
            logger.info("executed method: {}, param {}", method.getName(), args);
        }
        return method.invoke(loggingInterface, args);
    }

    @Override
    public String toString() {
        return "LoggingInvocationHandler{" +
                "loggingInterface=" + loggingInterface +
                ", methodMap=" + methodMap +
                '}';
    }

    private Map<Method, Class<?>[]> generateMethodMap(Object target, Predicate<Method> predicate) {
        return Arrays.stream(target.getClass().getMethods())
                .filter(predicate)
                .map(method -> Map.entry(method, method.getParameterTypes()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
