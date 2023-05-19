<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.*, com.Produtos.*, com.Servlet.*" %>

<%
	ArrayList<Produtos> lista = (ArrayList<Produtos>) request.getAttribute("listaDeProdutos");
//for (int i = 0; i <lista.size(); i++){
	//out.println(lista.get(i).getId());
	//out.println(lista.get(i).getCodigo());
	//out.println(lista.get(i).getNome());
	//out.println(lista.get(i).getCategoria());
	//out.println(lista.get(i).getValor());
	//out.println(lista.get(i).getQuantidade());
//}
%>


<!DOCTYPE html>

<html lang="pt-BR">
	<head>
		<link rel="stylesheet" href="style.css">	
		<meta charset="ISO-8859-1">
		<title>Produtos</title>
		
		<style>
		
			
	
}
		</style>
		

	</head>
	<body>
		<h1>Lista de Produtos</h1>
		
		<a id="botao" href="cadastrar.html" class="botao1">Novo Produto</a>
		<table id="tabela">
			<thead>
				<tr>
					<th>Id</th>
					<th>Código</th>
					<th>Nome</th>
					<th>Categoria</th>
					<th>Valor</th>
					<th>Quantidade</th>
					<th>Opções</th>						
				</tr>
			</thead>
			<tbody>
				<% for (int i = 0; i <lista.size(); i++) { %>
					<tr>
						<td><%=lista.get(i).getId()%></td>
						<td><%=lista.get(i).getCodigo()%></td>
						<td><%=lista.get(i).getNome()%></td>
						<td><%=lista.get(i).getCategoria()%></td>
						<td><%=lista.get(i).getValor()%></td>
						<td><%=lista.get(i).getQuantidade()%></td>
						<td><a id="botao" href="select?id=<%= lista.get(i).getId() %>" class="botao">Editar</a>
						<a id="botao" href="select2?id=<%= lista.get(i).getId() %>" class="botao3">Excluir</a></td>
					</tr>
					<%}%>
			</tbody>
		</table>
	
	
	
	</body>
</html>