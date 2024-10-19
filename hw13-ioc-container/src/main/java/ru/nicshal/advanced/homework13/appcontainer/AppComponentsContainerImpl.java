package ru.nicshal.advanced.homework13.appcontainer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ru.nicshal.advanced.homework13.appcontainer.api.*;
import ru.nicshal.advanced.homework13.appcontainer.exceptions.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        return (C) findComponent(componentClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <C> C getAppComponent(String componentName) {
        if (appComponentsByName.containsKey(componentName)) {
            return (C) appComponentsByName.get(componentName);
        } else {
            throw new ComponentNotFoundException("Component not found by name: " + componentName);
        }
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        Object instance = getClassInstance(configClass);
        for (Method method : getClassMethods(configClass)) {
            String nameBean = getBeanName(method);
            if (appComponentsByName.containsKey(nameBean)) {
                throw new TooManyBeanException("Too many bean for name" + nameBean);
            }
            Object[] args = getMethodArgs(method);
            try {
                Object component = method.invoke(instance, args);
                appComponentsByName.put(nameBean, component);
                appComponents.add(component);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new InstanceMethodInvokeException(e);
            }
        }
    }

    private String getBeanName(Method method) {
        AppComponent appComponent = method.getDeclaredAnnotation(AppComponent.class);
        return appComponent.name();
    }

    private Object[] getMethodArgs(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length == 0) {
            return new Object[0];
        }
        return Stream.of(parameterTypes)
                .map(this::findComponent)
                .toArray();
    }

    private Object findComponent(Class<?> clazz) {
        List<Object> objectList = appComponents.stream()
                .filter(component -> clazz.isAssignableFrom(component.getClass()))
                .toList();
        if (objectList.isEmpty()) {
            throw new ComponentNotFoundException("Component not found by class: " + clazz.getName());
        }
        if (objectList.size() > 1) {
            throw new TooManyBeanException("Too many bean for class" + clazz.getName());
        }
        return objectList.get(0);
    }

    private List<Method> getClassMethods(Class<?> clazz) {
        Predicate<Method> isAppComponent = method -> method.isAnnotationPresent(AppComponent.class);
        Comparator<Method> orderComparator = Comparator.comparingInt(
                method -> method.getAnnotation(AppComponent.class).order());
        return Stream.of(clazz.getDeclaredMethods())
                .filter(isAppComponent)
                .sorted(orderComparator)
                .collect(Collectors.toList());
    }

    private Object getClassInstance(Class<?> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException
                 | IllegalAccessException
                 | InvocationTargetException
                 | NoSuchMethodException e) {
            throw new InstanceCreatingException(e);
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

}
