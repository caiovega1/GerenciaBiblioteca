# GerenciaBiblioteca
 O projeto foi construido com java/spring, para inicialização do projeto, não deve ter muitas complicações o banco usado foi o Postgress.

Configurações do Resources:
spring.datasource.url= jdbc:postgresql://localhost:5432/library-api
spring.datasource.username=postgres
spring.datasource.password=banco123
spring.jpa.hibernate.ddl-auto= update

Exemplo post livro 
POST http://localhost:8080/Book
Content-Type: application/json

{
  "titulo": "Harry Potter",
  "autor": "j.K",
  "isbn": 123456789",
  "data_publicacao": "1998-01-01",
  "categoria": "FANTASIA"
}
Exemplo Get
GET http://localhost:8080/Book/UUID

Exemplo Put
PUT http://localhost:8080/Book/UUID
Content-Type: application/json

{
  "titulo": "diary of a wimpy kid: Rodrick Rules",
  "autor": "Jeff Kinney",
  "isbn": "9780143303831",
  "data_publicacao": "2009-01-01",
  "categoria": "BIOGRAFIA"
}

Exemplo Post User
POST http://localhost:8080/User
Content-Type: application/json

{
  "nome": "Caio",
  "email": "caiovega1@hotmail.com",
  "data_cadastro": "2025-10-19",
  "telefone": "44-988578630"
}

Exemplo Post Emprestimo
POST http://localhost:8080/Loan
Content-Type: application/json

{
  "usuario_id": "cb192d01-5738-4d87-a8a3-47ac7566e0e3",
  "livroId":  "b1c89443-9adc-4867-8428-ea93efcd31f0",
  "data_emprestimo": "2025-10-19",
  "data_devolucao": "2025-10-29",
  "status": "TRUE"
}

Exemplo Put Emprestimo
PUT http://localhost:8080/Loan/UUID
Content-Type: application/json

{
  "data_devolucao": "2025-10-30",
  "status": false
}



