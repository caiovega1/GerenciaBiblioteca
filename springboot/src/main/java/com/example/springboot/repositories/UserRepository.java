package com.example.springboot.repositories;


import com.example.springboot.models.Book;
import com.example.springboot.models.CategoriaEnum;
import com.example.springboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

   @Query(nativeQuery = true, value = "SELECT categoria " +
                                      "FROM" +
                                      "    tabela_livros " +
                                      "WHERE id in (SELECT livro_id FROM tabela_emprestimos WHERE :id = usuario_id) " +
                                      "GROUP BY " +
                                      "    categoria " +
                                      "ORDER BY " +
                                      "    count(categoria) desc limit 1 ")
   public CategoriaEnum getCategoria( @Param("id") UUID id );

    @Query(nativeQuery = true, value = "SELECT" +
                                       " *" +
                                       " FROM" +
                                       " tabela_livros" +
                                       " WHERE categoria = :cat  AND id NOT IN (SELECT livro_id FROM tabela_emprestimos WHERE :id = usuario_id)")
    List<Book> getRecomendacao(@Param("cat") String cat, @Param("id") UUID id );

}