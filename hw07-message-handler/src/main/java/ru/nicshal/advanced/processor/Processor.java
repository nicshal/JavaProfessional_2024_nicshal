package ru.nicshal.advanced.processor;

import ru.nicshal.advanced.model.Message;

@SuppressWarnings("java:S1135")
public interface Processor {

    Message process(Message message);

}
