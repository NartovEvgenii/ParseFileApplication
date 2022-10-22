package ru.nartov.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nartov.service.ArrayParsingService;

import java.util.Collection;
import java.util.stream.IntStream;

@Component("get_max_value")
public class MaxValueOperation implements FileArrayOperation {
    @Autowired
    private ArrayParsingService arrayParsingService;

    @Override
    public <T> Object executeOperation(Collection<T> collection) {
        Integer[] array = collection.stream()
                .flatMapToInt(s -> IntStream.of(Integer.parseInt(s.toString())))
                .boxed()
                .toArray(Integer[]::new);
        return arrayParsingService.getMAXElementFromArray(array);
    }

    @Override
    public String getJsonFieldName() {
        return "max_value";
    }
}
