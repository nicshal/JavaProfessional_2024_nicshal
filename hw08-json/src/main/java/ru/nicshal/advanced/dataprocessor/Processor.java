package ru.nicshal.advanced.dataprocessor;

import java.util.List;
import java.util.Map;
import ru.nicshal.advanced.model.Measurement;

public interface Processor {

    Map<String, Double> process(List<Measurement> data);

}