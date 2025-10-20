package com.example.springboot.dtos;

import com.example.springboot.models.CategoriaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookRecordDto(@NotBlank String titulo,
                            @NotBlank String autor,
                            @NotBlank String isbn,
                            @NotNull LocalDate data_publicacao,
                            @NotNull CategoriaEnum categoria) {

}
