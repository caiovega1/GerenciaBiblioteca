package com.example.springboot.controllers;

import com.example.springboot.dtos.BookRecordDto;
import com.example.springboot.models.Book;
import com.example.springboot.repositories.BookRepository;
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
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/Book")
    public ResponseEntity<Book> saveBook(@RequestBody @Valid BookRecordDto BookRecordDto){
        var Book = new Book();
        BeanUtils.copyProperties(BookRecordDto, Book);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(Book));
    }

    @GetMapping("/Books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.status (HttpStatus.OK).body(bookRepository.findAll());
    }

    @GetMapping("/Book/{id}")
    public ResponseEntity<Object> getOneBook(@PathVariable(value="id") UUID id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()) {
            return ResponseEntity.status (HttpStatus.NOT_FOUND).body("Livro não encontrado.");
        }
        return ResponseEntity.status (HttpStatus.OK).body(book.get());
    }

    @PutMapping("/Book/{id}")
    public ResponseEntity<Object> updateBook (@PathVariable (value="id") UUID id,
                                                 @RequestBody @Valid BookRecordDto bookRecordDto) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado.");
        }
        var book0 = book.get();
        BeanUtils.copyProperties(bookRecordDto, book0);
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.save(book0));
    }

    @DeleteMapping("/Book/{id}")
    public ResponseEntity<Object> deleteBook (@PathVariable (value="id") UUID id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado.");
        }
        bookRepository.delete(book.get());
        return ResponseEntity.status(HttpStatus.OK).body("Livro deletado com sucesso.");
    }
}
