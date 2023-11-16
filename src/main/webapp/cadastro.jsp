<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastrar Pessoa</title>
</head>
<body>
    <a href="lista?page=1&size=10">Voltar</a>    
    <h1><label>Cadastrar Pessoa</label></h1>
    
    <form action="cadastro">
        Nome: <input type="text" name="nome" required>
        Sobrenome: <input type="text" name="sobrenome" required>
        Telefone: <input type="text" name="telefone" required>
        Email: <input type="text" name="email" required>
        LinkedIn: <input type="text" name="linkedIn">
        <input type="submit" value="Cadastrar"/>
    </form>
</body>
</html>
