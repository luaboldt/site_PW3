<%@page import="model.Pessoa"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Usuários</title>
</head>
<body>
	<% 
		List<Pessoa> listaDePessoas = (List<Pessoa>) request.getAttribute("dados");
	%>
	
	<table>
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Detalhes</th>	
		</tr>
		<%
		for (int i = 0; i < listaDePessoas.size(); i++) {
		%>
		<tr>
			<td><%=listaDePessoas.get(i).getId()%></td>
			<td><%=listaDePessoas.get(i).getNome()%></td>
			<td><a href="action=procurar">ARRUMAR ESSA PARTE</a></td> 
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>