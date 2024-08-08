package ru.nicshal.advanced.homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nicshal.advanced.homework.annotations.Log;

public class TestLogging implements TestLoggingInterface {

    private static final Logger logger = LoggerFactory.getLogger(TestLogging.class);

    @Log
    @Override
    public void calculation(int param) {
        logger.info("calculation, param:{}", param);
    }

    @Override
    public void calculation(int param1, int param2) {
        logger.info("calculation, param:{}, {}", param1, param2);
    }

    @Log
    @Override
    public void calculation(int param1, int param2, String param3) {
        logger.info("calculation, param:{}, {}, {}", param1, param2, param3);
    }

    @Override
    public String toString() {
        return "TestLogging{}";
    }

}
