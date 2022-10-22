package ru.nartov.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class InputFileDTO {

    @NotNull
    @JsonProperty("file_path")
    private String filePath;
    private String operation;
}
