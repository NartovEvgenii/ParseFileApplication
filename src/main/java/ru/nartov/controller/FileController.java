package ru.nartov.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nartov.component.FileArrayOperation;
import ru.nartov.dto.InputFileDTO;
import ru.nartov.service.FileReaderService;
import ru.nartov.service.ArrayParsingService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping(
        path = "/file",
        produces = "application/json"
)
public class FileController {

    @Autowired
    private FileReaderService fileReaderService;

    @Autowired
    private ArrayParsingService arrayParsingService;

    @Autowired
    private Map<String, FileArrayOperation> operationMap;

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/")
    public JsonNode execOperationFromFilePath(@RequestBody InputFileDTO inputFileDTO) throws IOException {
        FileArrayOperation fileArrayOperation = operationMap.get(inputFileDTO.getOperation());
        if(fileArrayOperation == null){
            throw new UnsupportedOperationException(inputFileDTO.getOperation() + "does not exist");
        }

        List<Integer> list = fileReaderService.getIntegerListFromFile(inputFileDTO.getFilePath());
        return objectMapper.createObjectNode()
                .put(fileArrayOperation.getJsonFieldName(),fileArrayOperation.executeOperation(list).toString());
    }

    @PostMapping("/get_max_value")
    public JsonNode getMaxElementFromFilePath(@RequestBody InputFileDTO inputFileDTO) throws IOException {
        Integer[] arr = fileReaderService.getIntegerArrayFromFile(inputFileDTO.getFilePath());
        Integer maxElem = arrayParsingService.getMAXElementFromArray(arr);
        return objectMapper.createObjectNode().put("max_value", maxElem);
    }

    @PostMapping("/get_min_value")
    public JsonNode getMinElementFromFilePath(@RequestBody InputFileDTO inputFileDTO) throws IOException {
        Integer[] arr = fileReaderService.getIntegerArrayFromFile(inputFileDTO.getFilePath());
        Integer minElem = arrayParsingService.getMINElementFromArray(arr);
        return objectMapper.createObjectNode().put("min_value", minElem);
    }

    @PostMapping("/get_median")
    public JsonNode getMedianFromFilePath(@RequestBody InputFileDTO inputFileDTO) throws IOException {
        Integer[] arr = fileReaderService.getIntegerArrayFromFile(inputFileDTO.getFilePath());
        Integer median = arrayParsingService.getMedianFromArray(arr);
        return objectMapper.createObjectNode().put("median", median);
    }

    @PostMapping("/get_average")
    public JsonNode getAverageFromFilePath(@RequestBody InputFileDTO inputFileDTO) throws IOException {
        Integer[] arr = fileReaderService.getIntegerArrayFromFile(inputFileDTO.getFilePath());
        Integer average = arrayParsingService.getAverageFromArray(arr);
        return objectMapper.createObjectNode().put("average", average);
    }

    @PostMapping("/get_increase_seq")
    public JsonNode getIncreaseSeqFromFilePath(@RequestBody InputFileDTO inputFileDTO) throws IOException {
        Integer[] arr = fileReaderService.getIntegerArrayFromFile(inputFileDTO.getFilePath());
        List<List<Integer>> increaseSeq = arrayParsingService.getIncreaseSeqFromArray(arr);
        return objectMapper.createObjectNode().put("increase_seq", String.valueOf(increaseSeq));
    }

    @PostMapping("/get_decrease_seq")
    public JsonNode getDecreaseSeqFromFilePath(@RequestBody InputFileDTO inputFileDTO) throws IOException {
        Integer[] arr = fileReaderService.getIntegerArrayFromFile(inputFileDTO.getFilePath());
        List<List<Integer>> decreaseSeq = arrayParsingService.getDecreaseSeqFromArray(arr);
        return objectMapper.createObjectNode().put("decrease_seq", String.valueOf(decreaseSeq));
    }
}
