package ru.nicshal.advanced.processor.homework;

import ru.nicshal.advanced.model.Message;
import ru.nicshal.advanced.processor.Processor;

public class ProcessorSwapFields11And12 implements Processor {

    @Override
    public Message process(Message message) {
        var field11 = message.getField11();
        return message.toBuilder().field11(message.getField12()).field12(field11).build();
    }

}
