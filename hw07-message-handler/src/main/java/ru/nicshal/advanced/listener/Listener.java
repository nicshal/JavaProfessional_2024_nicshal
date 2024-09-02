package ru.nicshal.advanced.listener;

import ru.nicshal.advanced.model.Message;

public interface Listener {

    void onUpdated(Message msg, Message newMsg);

}
