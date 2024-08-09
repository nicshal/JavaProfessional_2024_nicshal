package ru.nicshal.advanced.homework.runner.data;

public class TestResult {

    private final String className;
    private final String methodName;
    private final TestStatus status;
    private final String errorMessage;

    public TestResult(String className, String methodName, TestStatus status, String errorMessage) {
        this.className = className;
        this.methodName = methodName;
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public TestStatus getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "TestResult{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", status=" + status +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

}
