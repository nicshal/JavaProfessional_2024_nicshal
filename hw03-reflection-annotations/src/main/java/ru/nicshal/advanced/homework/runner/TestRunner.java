package ru.nicshal.advanced.homework.runner;

import ru.nicshal.advanced.homework.annotations.After;
import ru.nicshal.advanced.homework.annotations.Before;
import ru.nicshal.advanced.homework.annotations.Test;
import ru.nicshal.advanced.homework.loader.SimpleClassLoader;
import ru.nicshal.advanced.homework.report.TestReport;
import ru.nicshal.advanced.homework.runner.data.TestResult;
import ru.nicshal.advanced.homework.runner.data.TestStatus;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestRunner {

    private final SimpleClassLoader classLoader;

    public TestRunner(SimpleClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public void run() {
        Class<?>[] classes = classLoader.loadClasses();
        List<TestResult> runResults = Stream.of(classes)
                .map(this::invokeTestList)
                .flatMap(Collection::stream)
                .toList();

        TestReport.printTestReport(runResults);
    }

    private List<TestResult> invokeTestList(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();

        return Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(Test.class))
                .map(method -> {
                    TestResult res;
                    MethodsInvoker testClass = MethodsInvoker.of(clazz);

                    List<TestResult> resBefore = testClass.invokeSupportMethods(Before.class);
                    if (resBefore.stream().anyMatch(item -> TestStatus.ERROR.equals(item.getStatus()))) {
                        res = new TestResult(clazz.getName(), method.getName(), TestStatus.ERROR,
                                getErrorMessage(resBefore).orElse("Ошибка при вызове метода @Before"));
                    } else {
                        res = testClass.invokeTestMethod(method);
                        List<TestResult> resAfter = testClass.invokeSupportMethods(After.class);
                        if (resAfter.stream().anyMatch(item -> TestStatus.ERROR.equals(item.getStatus()))) {
                            res = new TestResult(clazz.getName(), method.getName(), TestStatus.ERROR,
                                    getErrorMessage(resAfter).orElse("Ошибка при вызове метода @After"));
                        }
                    }
                    return res;
                }).collect(Collectors.toList());
    }

    private Optional<String> getErrorMessage(List<TestResult> result) {
        return result
                .stream()
                .filter(item -> TestStatus.ERROR.equals(item.getStatus()))
                .map(item -> String.format("Метод %s.%s, Ошибка: %s", item.getClassName(), item.getMethodName(), item.getErrorMessage()))
                .findFirst();
    }

}
