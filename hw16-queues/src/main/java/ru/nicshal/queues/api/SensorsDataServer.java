package ru.nicshal.queues.api;

import ru.nicshal.queues.api.model.SensorData;

public interface SensorsDataServer {

    void onReceive(SensorData sensorData);

}
