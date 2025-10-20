package com.example.springboot.controllers;

import com.example.springboot.dtos.UserRecordDto;
import com.example.springboot.models.Book;
import com.example.springboot.models.CategoriaEnum;
import com.example.springboot.models.User;
import com.example.springboot.repositories.UserRepository;
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
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/User")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserRecordDto UserRecordDto){
        var User = new User();
        BeanUtils.copyProperties(UserRecordDto, User);

        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(User));
    }

    @GetMapping("/Users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.status (HttpStatus.OK).body(userRepository.findAll());
    }

    @GetMapping("/User/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value="id") UUID id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            return ResponseEntity.status (HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        }
        return ResponseEntity.status (HttpStatus.OK).body(user.get());
    }

    @PutMapping("/User/{id}")
    public ResponseEntity<Object> updateUser (@PathVariable (value="id") UUID id,
                                              @RequestBody @Valid UserRecordDto userRecordDto) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        }
        var user0 = user.get();
        BeanUtils.copyProperties(userRecordDto, user0);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user0));
    }

    @DeleteMapping("/User/{id}")
    public ResponseEntity<Object> deleteUser (@PathVariable (value="id") UUID id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        }
        userRepository.delete(user.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso.");
    }

    @GetMapping("/UserRecommendation/{id}")
    public ResponseEntity<Object> getRecomendacao(@PathVariable(value="id") UUID id) {
        CategoriaEnum categoria = userRepository.getCategoria(id);
        List<Book> recomendacao = userRepository.getRecomendacao(categoria.name(), id);

        return ResponseEntity.status (HttpStatus.OK).body(recomendacao);
    }
}
