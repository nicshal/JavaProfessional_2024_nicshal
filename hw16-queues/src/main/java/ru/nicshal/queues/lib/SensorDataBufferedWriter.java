package ru.nicshal.queues.lib;

import java.util.List;

import ru.nicshal.queues.api.model.SensorData;

public interface SensorDataBufferedWriter {

    void writeBufferedData(List<SensorData> bufferedData);

}
