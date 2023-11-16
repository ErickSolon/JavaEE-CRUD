<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Pessoa</title>
</head>
<body>
	<a href="lista">Voltar</a>	
	<h1><label>Cadastrar Pessoa</label></h1>
	
	<form method="GET" action="cadastro">
		Nome: <input type="text" name="nome">
		Sobrenome: <input type="text" name="sobrenome">
		Telefone: <input type="text" name="telefone">
		Email: <input type="text" name="email">
		LinkedIn: <input type="text" name="linkedIn">
		<input type="submit" value="Cadastrar"/>
	</form>
</body>
</html>