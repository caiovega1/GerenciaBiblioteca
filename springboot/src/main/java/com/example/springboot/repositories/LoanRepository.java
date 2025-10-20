package com.example.springboot.repositories;


import com.example.springboot.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoanRepository extends JpaRepository<Loan, UUID> {

    boolean existsLoanByLivroId_IdAndStatusTrue(UUID livroIdId);

}