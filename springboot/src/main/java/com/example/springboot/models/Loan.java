package com.example.springboot.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Tabela_Emprestimos")
public class Loan implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private LocalDate data_emprestimo;
    private LocalDate data_devolucao;
    private Boolean status = Boolean.TRUE;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario_id;

    @ManyToOne
    @JoinColumn(name = "livroId")
    private Book livroId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(LocalDate data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public LocalDate getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(LocalDate data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public User getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(User usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Book getLivroId() {
        return livroId;
    }

    public void setLivroId(Book livroId) {
        this.livroId = livroId;
    }
}
