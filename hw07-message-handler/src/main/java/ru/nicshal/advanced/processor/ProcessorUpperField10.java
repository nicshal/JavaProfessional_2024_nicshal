package ru.nicshal.advanced.processor;

import ru.nicshal.advanced.model.Message;

public class ProcessorUpperField10 implements Processor {

    @Override
    public Message process(Message message) {
        return message.toBuilder().field4(message.getField10().toUpperCase()).build();
    }

}
