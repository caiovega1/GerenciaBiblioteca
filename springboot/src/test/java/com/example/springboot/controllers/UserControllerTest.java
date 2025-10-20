package com.example.springboot.controllers;

import com.example.springboot.models.Book;
import com.example.springboot.models.CategoriaEnum;
import com.example.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetRecomendacao_Successo() {

        UUID userId = UUID.randomUUID();
        CategoriaEnum categoria = CategoriaEnum.FANTASIA;
        List<Book> recomendacao = Arrays.asList(
                new Book("Book 1", "Author 1","34567",LocalDate.now(),CategoriaEnum.FANTASIA),
                new Book("Book 2", "Author 2","56789",LocalDate.now(),CategoriaEnum.FANTASIA),
                new Book("Book 3", "Author 3","12345",LocalDate.now(),CategoriaEnum.BIOGRAFIA)
        );

        when(userRepository.getCategoria(userId)).thenReturn(categoria);
        when(userRepository.getRecomendacao(categoria.name(), userId)).thenReturn(recomendacao);


        ResponseEntity<Object> response = userController.getRecomendacao(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recomendacao, response.getBody());
        verify(userRepository, times(1)).getCategoria(userId);
        verify(userRepository, times(1)).getRecomendacao(categoria.name(), userId);
    }

}