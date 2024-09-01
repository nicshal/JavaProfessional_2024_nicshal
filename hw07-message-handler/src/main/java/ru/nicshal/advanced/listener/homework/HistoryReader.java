package ru.nicshal.advanced.listener.homework;

import java.util.Optional;
import ru.nicshal.advanced.model.Message;

public interface HistoryReader {

    Optional<Message> findMessageById(long id);

}
