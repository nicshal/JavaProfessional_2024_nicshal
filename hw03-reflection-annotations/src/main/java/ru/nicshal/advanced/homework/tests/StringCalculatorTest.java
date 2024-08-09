package ru.nicshal.advanced.homework.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nicshal.advanced.homework.annotations.After;
import ru.nicshal.advanced.homework.annotations.Before;
import ru.nicshal.advanced.homework.annotations.Test;
import ru.nicshal.advanced.homework.tests.data.StringCalculator;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorTest {

    private static final Logger logger = LoggerFactory.getLogger(StringCalculatorTest.class);
    private StringCalculator calculator;

    @Before
    public void setUp() {
        logger.info("@Before - создаем новый объект");
        calculator = new StringCalculator("Новый год", "год");
    }

    @Test
    public void addSuccessTest() {
        assertThat(calculator.add())
                .isEqualTo("Новый год->год");
    }

    @Test
    public void addFailurTest() {
        assertThat(calculator.add())
                .isEqualTo("Неудача");
    }

    @Test
    public void subSuccessTest() {
        assertThat(calculator.sub())
                .isEqualTo("Новый ");
    }

    @Test
    public void mulSuccessTest() {
        assertThat(calculator.mul())
                .isEqualTo("Новый годНовый годНовый год");
    }

    @After
    public void clean() {
        logger.info("@After");
    }

}
