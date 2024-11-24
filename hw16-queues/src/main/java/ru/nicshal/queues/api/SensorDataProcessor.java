package ru.nicshal.queues.api;

import ru.nicshal.queues.api.model.SensorData;

public interface SensorDataProcessor {

    void process(SensorData data);

    default void onProcessingEnd() {
    }

}
