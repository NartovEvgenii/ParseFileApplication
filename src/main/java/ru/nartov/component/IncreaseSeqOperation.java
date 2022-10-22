package ru.nartov.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nartov.service.ArrayParsingService;

import java.util.Collection;
import java.util.stream.IntStream;

@Component("get_increase_seq")
public class IncreaseSeqOperation implements FileArrayOperation {
    @Autowired
    private ArrayParsingService arrayParsingService;

    @Override
    public <T> Object executeOperation(Collection<T> collection) {
        Integer[] array = collection.stream()
                .flatMapToInt(s -> IntStream.of(Integer.parseInt(s.toString())))
                .boxed()
                .toArray(Integer[]::new);
        return arrayParsingService.getIncreaseSeqFromArray(array);
    }

    @Override
    public String getJsonFieldName() {
        return "increase_seq";
    }
}

