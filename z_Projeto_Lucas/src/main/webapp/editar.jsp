<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
		<title>Produtos</title>
		<link rel="stylesheet" href="style.css">
		</head>
	<body>
	<h1>Editar produto</h1>
	<form name="fmrContato" action="update" class="tabela">
		<table>
			<tr>
				<td><input class="caixa" type="text" name="id" readonly value="<%out.print(request.getAttribute("id"));%>"></td>
			</tr>
			
			<tr>
				<td><input class="caixa" type="text" name="codigo" value="<%out.print(request.getAttribute("codigo"));%>" required></td>
			</tr>
			<tr>
				<td><input class="caixa" type="text" name="nome" value="<%out.print(request.getAttribute("nome"));%>" required></td>
			</tr>
			<tr>
				<td><input class="caixa" type="text" name="categoria" value="<%out.print(request.getAttribute("categoria"));%>" required></td>
			</tr>
			<tr>
				<td><input class="caixa" type="text" name="valor" value="<%out.print(request.getAttribute("valor"));%>" required></td>
			</tr>
			<tr>
				<td><input class="caixa" type="text" name="quantidade" value="<%out.print(request.getAttribute("quantidade"));%>" required></td>
			</tr>
		</table>
		
		<input  id="botao" class="botao2" type="submit" value="Salvar">
		<a  id="botao" class="botao5" href="main">Voltar</a>
	
	</form>
	
	</body>
</html>	