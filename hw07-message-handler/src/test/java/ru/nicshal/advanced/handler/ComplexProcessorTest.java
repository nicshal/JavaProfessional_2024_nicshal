package ru.nicshal.advanced.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.nicshal.advanced.listener.Listener;
import ru.nicshal.advanced.model.Message;
import ru.nicshal.advanced.processor.Processor;
import ru.nicshal.advanced.processor.homework.ProcessorSwapFields11And12;
import ru.nicshal.advanced.processor.homework.ProcessorWithTimeException;
import ru.nicshal.advanced.processor.homework.exception.ProcessorTimeException;

class ComplexProcessorTest {

    @Test
    @DisplayName("Тестируем вызовы процессоров")
    void handleProcessorsTest() {
        // given
        var message = new Message.Builder(1L).field7("field7").build();

        var processor1 = mock(Processor.class);
        when(processor1.process(message)).thenReturn(message);

        var processor2 = mock(Processor.class);
        when(processor2.process(message)).thenReturn(message);

        var processors = List.of(processor1, processor2);

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {
        });

        // when
        var result = complexProcessor.handle(message);

        // then
        verify(processor1).process(message);
        verify(processor2).process(message);
        assertThat(result).isEqualTo(message);
    }

    @Test
    @DisplayName("Тестируем обработку исключения")
    void handleExceptionTest() {
        // given
        var message = new Message.Builder(1L).field8("field8").build();

        var processor1 = mock(Processor.class);
        when(processor1.process(message)).thenThrow(new RuntimeException("Test Exception"));

        var processor2 = mock(Processor.class);
        when(processor2.process(message)).thenReturn(message);

        var processors = List.of(processor1, processor2);

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {
            throw new TestException(ex.getMessage());
        });

        // when
        assertThatExceptionOfType(TestException.class).isThrownBy(() -> complexProcessor.handle(message));

        // then
        verify(processor1, times(1)).process(message);
        verify(processor2, never()).process(message);
    }

    @Test
    @DisplayName("Тестируем уведомления")
    void notifyTest() {
        // given
        var message = new Message.Builder(1L).field9("field9").build();

        var listener = mock(Listener.class);

        var complexProcessor = new ComplexProcessor(new ArrayList<>(), (ex) -> {
        });

        complexProcessor.addListener(listener);

        // when
        complexProcessor.handle(message);
        complexProcessor.removeListener(listener);
        complexProcessor.handle(message);

        // then
        verify(listener, times(1)).onUpdated(message, null);
    }

    @Test
    @DisplayName("Тестируем перемену местами значений полей")
    void swapTest() {
        //given
        var message = new Message.Builder(1L).field11("field11").field12("field12").build();

        List<Processor> processors = List.of(new ProcessorSwapFields11And12());

        var complexProcessor = new ComplexProcessor(processors, ex -> {
        });

        //when
        var result = complexProcessor.handle(message);

        //then
        assertThat(result.getField11()).isEqualTo("field12");
        assertThat(result.getField12()).isEqualTo("field11");
    }

    @Test
    @DisplayName("Тестируем исключение на четной секунде")
    void timeExceptionTest() {
        //given
        var message = new Message.Builder(1L).field8("field1").build();
        var testTime = LocalDateTime.of(2024, 9, 1, 12, 50, 4);

        List<Processor> processors = List.of(new ProcessorWithTimeException(() -> testTime));
        var complexProcessor = new ComplexProcessor(processors, ex -> {
            throw new ProcessorTimeException(ex);
        });

        //then
        assertThatExceptionOfType(ProcessorTimeException.class).isThrownBy(() -> complexProcessor.handle(message));
    }

    private static class TestException extends RuntimeException {
        public TestException(String message) {
            super(message);
        }
    }

}
