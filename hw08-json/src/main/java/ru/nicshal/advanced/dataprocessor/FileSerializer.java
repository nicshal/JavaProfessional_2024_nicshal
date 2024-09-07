package ru.nicshal.advanced.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileSerializer implements Serializer {

    private String fileName;
    private final ObjectMapper mapper;

    public FileSerializer(String fileName) {
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
    public void serialize(Map<String, Double> data) {
        // формирует результирующий json и сохраняет его в файл
        var file = new File(fileName);
        try {
             mapper.writeValue(file, data
                     .entrySet()
                     .stream()
                     .sorted(Map.Entry.<String, Double>comparingByValue())
                     .collect(LinkedHashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), Map::putAll));
        } catch (IOException ex) {
            throw new FileProcessException("Ошибка при выгрузке данных в файл");
        }
    }

    @Override
    public String toString() {
        return "FileSerializer{" +
                "fileName='" + fileName + '\'' +
                '}';
    }

}
