package ru.nicshal.advanced.dataprocessor;

import java.io.IOException;
import java.io.InputStream;
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
        try (InputStream resource = getClass().getClassLoader().getResourceAsStream(fileName)) {
            return mapper.readValue(resource, new TypeReference<>() {
            });
        } catch (IOException ex) {
            throw new FileProcessException("Ошибка при загрузке данных из файла");
        }
    }

    @Override
    public String toString() {
        return "ResourcesFileLoader{" +
                "fileName='" + fileName + '\'' +
                '}';
    }

}
