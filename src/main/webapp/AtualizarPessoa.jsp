
<%@page import="com.models.JavaBeans"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% JavaBeans pessoa = (JavaBeans)request.getAttribute("pessoa"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Atualizar Pessoa</title>
</head>
<body>
	<a href="lista">Voltar</a>	
	<h1><label>Atualizar Pessoa</label></h1>
	
	<form method="GET" action="update">
		<input type="hidden" name="id" value="<%= pessoa.getId() %>">
		Nome: <input type="text" name="nome" value="<%= pessoa.getNome() %>">
		Sobrenome: <input type="text" name="sobrenome" value="<%= pessoa.getSobrenome() %>">
		Telefone: <input type="text" name="telefone" value="<%= pessoa.getTelefone() %>">
		E-mail: <input type="text" name="email" value="<%= pessoa.getEmail() %>">
		LinkedIn: <input type="text" name="linkedIn" value="<%= pessoa.getLinkedIn() %>">
		<input type="submit" value="Atualizar"/>
	</form>
</body>
</html>