package ru.nicshal.advanced.processor.homework;

import ru.nicshal.advanced.model.Message;
import ru.nicshal.advanced.processor.Processor;
import ru.nicshal.advanced.processor.homework.exception.ProcessorTimeException;

public class ProcessorWithTimeException implements Processor {

    private final DateTimeProvider dateTimeProvider;

    public ProcessorWithTimeException(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) {
        if (dateTimeProvider.getDate().getSecond() % 2 == 0) {
            throw new ProcessorTimeException("Попали на четную секунду");
        }
        return message;
    }

}
