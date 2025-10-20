package com.example.springboot.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.UUID;

public record LoanRecordDto(@NotNull UUID usuario_id,
                            @NotNull UUID livroId,
                            @PastOrPresent LocalDate data_emprestimo,
                            @NotNull LocalDate data_devolucao,
                            @NotNull Boolean status) {

}
