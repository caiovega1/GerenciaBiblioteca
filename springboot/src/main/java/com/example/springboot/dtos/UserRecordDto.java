package com.example.springboot.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record UserRecordDto(@NotBlank String nome,
                            @Email String email,
                            @PastOrPresent LocalDate data_cadastro,
                            @NotNull String telefone) {

}