package ru.nicshal.advanced.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import ru.nicshal.advanced.listener.Listener;
import ru.nicshal.advanced.model.Message;
import ru.nicshal.advanced.processor.Processor;

public class ComplexProcessor implements Handler {

    private final List<Listener> listeners = new ArrayList<>();
    private final List<Processor> processors;
    private final Consumer<Exception> errorHandler;

    public ComplexProcessor(List<Processor> processors, Consumer<Exception> errorHandler) {
        this.processors = processors;
        this.errorHandler = errorHandler;
    }

    @Override
    public Message handle(Message msg) {
        Message newMsg = msg;
        for (Processor pros : processors) {
            try {
                newMsg = pros.process(newMsg);
            } catch (Exception ex) {
                errorHandler.accept(ex);
            }
        }
        notify(msg, newMsg);
        return newMsg;
    }

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    private void notify(Message msg, Message newMsg) {
        listeners.forEach(listener -> {
            try {
                listener.onUpdated(msg, newMsg);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

}
