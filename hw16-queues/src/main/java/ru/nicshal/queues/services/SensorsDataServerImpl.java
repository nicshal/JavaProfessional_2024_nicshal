package ru.nicshal.queues.services;

import ru.nicshal.queues.api.SensorsDataChannel;
import ru.nicshal.queues.api.SensorsDataServer;
import ru.nicshal.queues.api.model.SensorData;

public class SensorsDataServerImpl implements SensorsDataServer {

    private final SensorsDataChannel sensorsDataChannel;

    public SensorsDataServerImpl(SensorsDataChannel sensorsDataChannel) {
        this.sensorsDataChannel = sensorsDataChannel;
    }

    @Override
    public void onReceive(SensorData sensorData) {
        sensorsDataChannel.push(sensorData);
    }

}
