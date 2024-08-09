package ru.nicshal.advanced.homework.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nicshal.advanced.homework.annotations.After;
import ru.nicshal.advanced.homework.annotations.Before;
import ru.nicshal.advanced.homework.annotations.Test;
import ru.nicshal.advanced.homework.tests.data.Calculator;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorTest.class);
    private Calculator calculator;

    @Before
    public void setUp() {
        logger.info("@Before - создаем новый объект");
        calculator = new Calculator(77, 11);
    }

    @Test
    public void addSuccessTest() {
        assertThat(calculator.add())
                .isEqualTo(88);
    }

    @Test
    public void addFailurTest() {
        assertThat(calculator.add())
                .isEqualTo(155);
    }

    @Test
    public void subSuccessTest() {
        assertThat(calculator.sub())
                .isEqualTo(66);
    }

    @Test
    public void mulSuccessTest() {
        assertThat(calculator.mul())
                .isEqualTo(847);
    }

    @Test
    public void divSuccessTest() {
        assertThat(calculator.div())
                .isEqualTo(7);
    }

    @After
    public void clean() {
        logger.info("@After");
    }

}
