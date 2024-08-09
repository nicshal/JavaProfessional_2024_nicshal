package ru.nicshal.advanced.homework;

import ru.nicshal.advanced.homework.loader.ClassPackage;
import ru.nicshal.advanced.homework.loader.TestClassLoader;
import ru.nicshal.advanced.homework.runner.TestRunner;

public class Demo {

    public static void main(String[] args) {
        new TestRunner(new TestClassLoader(new ClassPackage("ru.nicshal.advanced.homework.tests")))
                .run();
    }

}
