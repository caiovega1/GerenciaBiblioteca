package com.example.springboot.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Tabela_Livros")
@NoArgsConstructor

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String titulo;
    private String autor;
    private String isbn;
    private LocalDate data_publicacao;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany (mappedBy = "livroId", fetch = FetchType.LAZY)
    private Set<Loan> loan = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;

    public Book(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getData_publicacao() {
        return data_publicacao;
    }

    public void setData_publicacao(LocalDate data_publicacao) {
        this.data_publicacao = data_publicacao;
    }
    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }

    public Set<Loan> getLoan() {
        return loan;
    }

    public void setLoan(Set<Loan> loan) {
        this.loan = loan;
    }

    public Book(String titulo, String autor, String isbn, LocalDate data_publicacao, CategoriaEnum categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.data_publicacao = data_publicacao;
        this.categoria = categoria;
    }
}
