package ru.nicshal.advanced.homework.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nicshal.advanced.homework.runner.data.TestResult;
import ru.nicshal.advanced.homework.runner.data.TestStatus;

import java.util.List;

public class TestReport {

    private static final Logger logger = LoggerFactory.getLogger(TestReport.class);

    public static void printTestReport(List<TestResult> runResults) {
        long failedTestCount = runResults
                .stream()
                .filter(test -> TestStatus.ERROR.equals(test.getStatus()))
                .count();

        if (failedTestCount == 0) {
            logger.info("SUCCESS");
        } else {
            logger.info("FAILED");
            logger.info("Ошибочные тесты:");
            runResults
                    .stream()
                    .filter(test -> TestStatus.ERROR.equals(test.getStatus()))
                    .forEach(item -> {
                        logger.info("Тест {}.{}. Ошибка: {}", item.getClassName(), item.getMethodName(), item.getErrorMessage());
                    });
        }
        logger.info("Всего тестов:      {}", runResults.size());
        logger.info("Успешных тестов:   {}", runResults.size() - failedTestCount);
        logger.info("Неуспешных тестов: {}", failedTestCount);
    }

}
