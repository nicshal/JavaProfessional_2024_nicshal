package ru.nicshal.advanced;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nicshal.advanced.dataprocessor.FileSerializer;
import ru.nicshal.advanced.dataprocessor.ProcessorAggregator;
import ru.nicshal.advanced.dataprocessor.ResourcesFileLoader;

import java.net.URL;

public class DemoJson {
    private static final Logger logger = LoggerFactory.getLogger(DemoJson.class);

    public static void main(String[] args) {

        var inputDataFileName = "inputData.json";
        URL resource = DemoJson.class.getClassLoader().getResource(inputDataFileName);
        var outputDataFileName = resource.getPath().replace("inputData.json", "outputData.json");
        logger.info("loaded from the file:{}", outputDataFileName);

        var loader = new ResourcesFileLoader(inputDataFileName);
        var processor = new ProcessorAggregator();
        var serializer = new FileSerializer(outputDataFileName);

        var loadedMeasurements = loader.load();
        var aggregatedMeasurements = processor.process(loadedMeasurements);

        logger.info("loaded from the file:{}", loadedMeasurements);
        logger.info("processed data:{}", aggregatedMeasurements);

        serializer.serialize(aggregatedMeasurements);
    }

}
