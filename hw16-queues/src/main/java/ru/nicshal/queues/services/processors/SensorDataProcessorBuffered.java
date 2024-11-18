package ru.nicshal.queues.services.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nicshal.queues.api.SensorDataProcessor;
import ru.nicshal.queues.api.model.SensorData;
import ru.nicshal.queues.lib.SensorDataBufferedWriter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

@SuppressWarnings({"java:S1068", "java:S125"})
public class SensorDataProcessorBuffered implements SensorDataProcessor {

    private static final Logger log = LoggerFactory.getLogger(SensorDataProcessorBuffered.class);

    private final int bufferSize;
    private final SensorDataBufferedWriter writer;
    private final PriorityBlockingQueue<SensorData> dataBuffer;

    public SensorDataProcessorBuffered(int bufferSize, SensorDataBufferedWriter writer) {
        this.bufferSize = bufferSize;
        this.writer = writer;
        dataBuffer = new PriorityBlockingQueue<>(bufferSize, Comparator.comparing(SensorData::getMeasurementTime));
    }

    @Override
    public void process(SensorData data) {
        if (dataBuffer.size() >= bufferSize) {
            flush();
        }
        dataBuffer.add(data);
    }

    public void flush() {
        try {
            ArrayList<SensorData> data = new ArrayList<>();
            dataBuffer.drainTo(data);
            if (!data.isEmpty()) {
                writer.writeBufferedData(data);
            }
        } catch (Exception e) {
            log.error("Ошибка в процессе записи буфера", e);
        }
    }

    @Override
    public void onProcessingEnd() {
        flush();
    }

}
