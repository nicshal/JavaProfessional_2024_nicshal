package ru.nicshal.advanced.dataprocessor;

import java.util.List;
import ru.nicshal.advanced.model.Measurement;

public interface Loader {

    List<Measurement> load();

}
