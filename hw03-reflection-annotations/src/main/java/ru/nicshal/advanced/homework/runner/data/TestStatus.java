package ru.nicshal.advanced.homework.runner.data;

public enum TestStatus {

    SUCCESS("PASSED"), ERROR("FAILED");

    private final String state;

    TestStatus(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return String.format("[ %s ]", state);
    }

}
