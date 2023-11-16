<%@page import="java.util.ArrayList"%>
<%@page import="com.models.JavaBeans"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	List<JavaBeans> lstPessoas = (ArrayList<JavaBeans>)request.getAttribute("pessoas"); 
	int pagina = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
	int size = 10;
	boolean isEmpty = lstPessoas == null || lstPessoas.isEmpty();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista De Pessoas</title>
</head>
<body>
	<ul>
		<li>
			<a href="index.html">Voltar</a>		
		</li>
		<li>
			<a href="cadastro.jsp">Adicionar</a>		
		</li>
	</ul>
	<h1><label>Lista de Pessoas</label></h1>
	
	<table>
		<thead>
			<tr>
				<th>
					Id
				</th>
				<th>
					Nome
				</th>
				<th>
					Sobrenome
				</th>
				<th>
					Telefone
				</th>
				<th>
					E-mail
				</th>
				<th>
					LinkedIn
				</th>
				<th>
					Ações
				</th>
			</tr>
		</thead>
		<tbody>
			<% if (!isEmpty) { %>
				<% for (JavaBeans pessoa : lstPessoas) { %>
					<tr key="<%= pessoa.getId() %>">
						<td><%= pessoa.getId() %></td>
						<td><%= pessoa.getNome() %></td>
						<td><%= pessoa.getSobrenome() %></td>
						<td><%= pessoa.getTelefone() %></td>
						<td><%= pessoa.getEmail() %></td>
						<td><%= pessoa.getLinkedIn() %></td>
						<td>
							<a href="delete?id=<%= pessoa.getId() %>">Deletar</a>
							<a href="edit?id=<%= pessoa.getId() %>">Atualizar</a>
						</td>
					</tr>
				<% } %>
			<% } else { %>
				<tr>
					<td colspan="7">Nenhum resultado encontrado.</td>
				</tr>
			<% } %>
		</tbody>
	</table>
	
	<form method="GET" action="lista">
		<% if (pagina > 1) { %>
			<a href="?page=<%= pagina - 1  %>&size=10"><label><-</label></a>
		<% } %>
   		
   		<%= pagina %>
   		
   		<% if (pagina < lstPessoas.size()) { %>
   			<a href="?page=<%= pagina + 1  %>&size=10"><label>-></label></a>
   		<% } %>
	</form>
</body>
</html>
