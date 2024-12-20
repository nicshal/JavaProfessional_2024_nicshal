package ru.nicshal.queues.services.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nicshal.queues.api.SensorDataProcessor;
import ru.nicshal.queues.api.model.SensorData;

public class SensorDataProcessorRoom implements SensorDataProcessor {

    private static final Logger log = LoggerFactory.getLogger(SensorDataProcessorRoom.class);

    private final String roomName;

    public SensorDataProcessorRoom(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public void process(SensorData data) {
        if (data.getValue() == null || data.getValue().isNaN()) {
            return;
        }
        log.info("Обработка данных по заданной комнате ({}): {}", roomName, data);
    }

}
