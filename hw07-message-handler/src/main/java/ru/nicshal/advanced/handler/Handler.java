package ru.nicshal.advanced.handler;

import ru.nicshal.advanced.listener.Listener;
import ru.nicshal.advanced.model.Message;

public interface Handler {

    Message handle(Message msg);

    void addListener(Listener listener);

    void removeListener(Listener listener);

}
