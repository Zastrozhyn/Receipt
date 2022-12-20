package by.zastr.controller.util;

import by.zastr.service.exception.EntityException;
import by.zastr.service.exception.ExceptionCode;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyFileReader {


    public List<String> readFile(String file) {
        if (file == null || file.isBlank()) {
            throw new EntityException(ExceptionCode.FILE_NOT_FOUND.getErrorCode());
        }
        List<String> list = new ArrayList<>();
        Path dataFile = Paths.get(file);
        try (Stream<String> dataStream = Files.lines(dataFile)){
            list = dataStream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new EntityException(ExceptionCode.FILE_NOT_FOUND.getErrorCode());
        }
        return list;
    }
}
