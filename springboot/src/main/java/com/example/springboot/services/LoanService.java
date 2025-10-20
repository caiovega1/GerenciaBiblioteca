package com.example.springboot.services;

import com.example.springboot.dtos.LoanRecordAlterDto;
import com.example.springboot.dtos.LoanRecordDto;
import com.example.springboot.models.Book;
import com.example.springboot.models.Loan;
import com.example.springboot.models.User;
import com.example.springboot.repositories.LoanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;

    public Loan save( LoanRecordDto emprestimo) throws Exception {
        var loan = new Loan();
        BeanUtils.copyProperties(emprestimo, loan);

        UUID livroId = emprestimo.livroId();
        UUID user_id = emprestimo.usuario_id();

        loan.setLivroId(new Book(livroId));
        loan.setUsuario_id(new User(user_id));

        if (loanRepository.existsLoanByLivroId_IdAndStatusTrue(livroId)) {
            throw new Exception(STR."O livro com ID \{livroId} já possui um empréstimo ativo.");
        }
        loanRepository.save(loan);
        return null;
    }
    @Transactional
    public Loan alter(LoanRecordAlterDto emprestimo, Loan loan, UUID Id) throws Exception {
        BeanUtils.copyProperties(emprestimo, loan);

        boolean status = emprestimo.status();
        LocalDate data_devolucao = emprestimo.data_devolucao();

        loan.setData_devolucao(data_devolucao);
        loan.setStatus(status);
        loan.setId(Id);

        loanRepository.save(loan);
        return null;
    }

    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    public Optional<Loan> findById(UUID id) {
        return loanRepository.findById(id);
    }

    @Transactional
    public void delete(Loan loan) {
        loanRepository.delete(loan);
    }
}
