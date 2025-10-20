package com.example.springboot.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LoanRecordAlterDto(@NotNull LocalDate data_devolucao,
                                 @NotNull Boolean status) {

}
