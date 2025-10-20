package com.example.springboot.controllers;

import com.example.springboot.dtos.LoanRecordAlterDto;
import com.example.springboot.dtos.LoanRecordDto;
import com.example.springboot.models.Loan;
import com.example.springboot.services.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/Loan")
    public ResponseEntity<Loan> saveLoan(@RequestBody @Valid LoanRecordDto loanRecordDto) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.save(loanRecordDto));
    }

    @GetMapping("/Loans")
    public ResponseEntity<List<Loan>> getAllLoans(){
        return ResponseEntity.status (HttpStatus.OK).body(loanService.findAll());
    }

    @GetMapping("/Loan/{id}")
    public ResponseEntity<Object> getOneLoan(@PathVariable(value="id") UUID id) {
        Optional<Loan> loan = loanService.findById(id);
        if(loan.isEmpty()) {
            return ResponseEntity.status (HttpStatus.NOT_FOUND).body("Emprestimo não encontrado.");
        }
        return ResponseEntity.status (HttpStatus.OK).body(loan.get());
    }

    @PutMapping("/Loan/{id}")
    public ResponseEntity<Object> updateLoan (@PathVariable (value="id") UUID id,
                                              @RequestBody @Valid LoanRecordAlterDto loanRecordDto) throws Exception {
        Optional<Loan> loan = loanService.findById(id);
        if (loan.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Emprestimo não encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(loanService.alter(loanRecordDto, loan.get(), id));
    }

    @DeleteMapping("/Loan/{id}")
    public ResponseEntity<Object> deleteLoan (@PathVariable (value="id") UUID id) {
        Optional<Loan> loan = loanService.findById(id);
        if(loan.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Emprestimo não encontrado.");
        }
        loanService.delete(loan.get());
        return ResponseEntity.status(HttpStatus.OK).body("Emprestimo deletado com sucesso.");
    }
}
