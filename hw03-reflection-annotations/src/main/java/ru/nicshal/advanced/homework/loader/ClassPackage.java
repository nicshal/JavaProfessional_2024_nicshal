package ru.nicshal.advanced.homework.loader;

import java.io.File;
import java.net.URL;
import java.util.Optional;

public class ClassPackage {

    private final String classPackage;

    public ClassPackage(String classPackage) {
        this.classPackage = classPackage;
    }

    public String getClassPackage() {
        return classPackage;
    }

    public File toFile() {
        return new File(getFilePath());
    }

    private String getFilePath() {
        String filePath = classPackage.replace('.', '/');
        Optional<URL> url = Optional.ofNullable(ClassPackage.class.getClassLoader().getResource(filePath));
        return url.orElseThrow().getFile();
    }

}
