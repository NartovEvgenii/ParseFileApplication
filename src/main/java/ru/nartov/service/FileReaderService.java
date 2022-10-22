package ru.nartov.service;

import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FileReaderService {

    public Integer[] getIntegerArrayFromFile(String filePath) throws IOException {
        return getIntegerListFromFile(filePath).toArray(new Integer[0]);
    }

    public List<Integer> getIntegerListFromFile(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        Scanner scanner = new Scanner(fileReader);
        List<Integer> result = new ArrayList<>();
        while (scanner.hasNextInt()) {
            result.add(scanner.nextInt());
        }
        fileReader.close();
        return result;
    }
}
