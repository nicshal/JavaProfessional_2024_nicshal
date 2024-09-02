package ru.nicshal.advanced;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nicshal.advanced.handler.ComplexProcessor;
import ru.nicshal.advanced.listener.ListenerPrinterConsole;
import ru.nicshal.advanced.model.Message;
import ru.nicshal.advanced.processor.LoggerProcessor;
import ru.nicshal.advanced.processor.ProcessorConcatFields;
import ru.nicshal.advanced.processor.ProcessorUpperField10;
import ru.nicshal.advanced.processor.homework.ProcessorSwapFields11And12;
import ru.nicshal.advanced.processor.homework.ProcessorWithTimeException;

import java.time.LocalDateTime;
import java.util.List;

public class HomeWork {

    private static final Logger logger = LoggerFactory.getLogger(HomeWork.class);

    public static void main(String[] args) {
        var processors = List.of(new ProcessorConcatFields(),
                new LoggerProcessor(new ProcessorUpperField10()),
                new ProcessorSwapFields11And12(),
                new ProcessorWithTimeException(LocalDateTime::now));

        var complexProcessor = new ComplexProcessor(processors, ex -> {});
        var listenerPrinter = new ListenerPrinterConsole();
        complexProcessor.addListener(listenerPrinter);

        var message = new Message.Builder(1L)
                .field1("field1")
                .field2("field2")
                .field3("field3")
                .field6("field6")
                .field10("field10")
                .field11("field11")
                .field12("field12")
                .build();

        var result = complexProcessor.handle(message);
        logger.info("result:{}", result);

        complexProcessor.removeListener(listenerPrinter);
    }

}
