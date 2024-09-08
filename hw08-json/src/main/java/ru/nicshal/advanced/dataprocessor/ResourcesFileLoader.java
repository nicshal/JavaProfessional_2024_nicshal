package ru.nicshal.advanced.dataprocessor;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import ru.nicshal.advanced.model.Measurement;

public class ResourcesFileLoader implements Loader {

    private String fileName;
    private final ObjectMapper mapper;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
        this.mapper = JsonMapper.builder().build();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        URL resource = getClass().getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        } else {
            try {
                var file = new File(resource.toURI());
                return mapper.readValue(file, new TypeReference<>() {
                });
            } catch (IOException | URISyntaxException ex) {
                throw new FileProcessException("Ошибка при загрузке данных из файла");
            }
        }
    }

    @Override
    public String toString() {
        return "ResourcesFileLoader{" +
                "fileName='" + fileName + '\'' +
                '}';
    }

}
