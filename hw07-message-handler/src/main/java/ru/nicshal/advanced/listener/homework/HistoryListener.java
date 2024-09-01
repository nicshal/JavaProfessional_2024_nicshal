package ru.nicshal.advanced.listener.homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import ru.nicshal.advanced.listener.Listener;
import ru.nicshal.advanced.model.Message;

public class HistoryListener implements Listener, HistoryReader {

    Map<Long, Message> messageMap = new HashMap<>();

    @Override
    public void onUpdated(Message msg, Message newMsg) {
        messageMap.put(msg.getId(), msg.clone());
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return Optional.ofNullable(messageMap.get(id));
    }

}
