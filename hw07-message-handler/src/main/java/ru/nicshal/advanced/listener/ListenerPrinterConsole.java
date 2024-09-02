package ru.nicshal.advanced.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nicshal.advanced.model.Message;

public class ListenerPrinterConsole implements Listener {

    private static final Logger logger = LoggerFactory.getLogger(ListenerPrinterConsole.class);

    @Override
    public void onUpdated(Message msg, Message newMsg) {
        logger.info("msg:{} - newMsg:{}", msg, newMsg);
    }

}
