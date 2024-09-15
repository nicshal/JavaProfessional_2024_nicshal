package ru.nicshal.advanced.dataprocessor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ru.nicshal.advanced.model.Measurement;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        return data.stream()
                .collect(Collectors.groupingBy(Measurement::name, Collectors.summingDouble(Measurement::value)));
    }

}
