package ru.nicshal.advanced.homework.loader;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class TestClassLoader implements SimpleClassLoader {

    private static final File[] EMPTY_ARRAY = {};
    private static final String CLASS_EXTENSION = ".class";
    private static final String EMPTY_STRING = "";
    private final ClassPackage classPackage;

    public TestClassLoader(ClassPackage classPackage) {
        this.classPackage = classPackage;
    }

    @Override
    public Class<?>[] loadClasses() {
        File[] files = loadFiles();
        return Arrays.stream(files)
                .map(file -> {
                    try {
                        String className = classPackage.getClassPackage() + '.' +
                                file.getName().replace(CLASS_EXTENSION, EMPTY_STRING);
                        return file.getName().endsWith(CLASS_EXTENSION) ? Class.forName(className) : null;
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(Objects::nonNull)
                .toArray(Class<?>[]::new);
    }

    private File[] loadFiles() {
        File testDirectory = classPackage.toFile();
        return testDirectory.isDirectory()
                ? testDirectory.listFiles(File::isFile)
                : EMPTY_ARRAY;
    }

}
