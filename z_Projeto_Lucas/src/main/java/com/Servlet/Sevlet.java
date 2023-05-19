package com.Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Produtos.Produtos;
import com.Um.Conexao;

@WebServlet(urlPatterns = {"/Sevlet", "/main", "/cadastrar", "/select", "/update","/select2", "/excluir"})
public class Sevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Produtos produto = new Produtos();
	Conexao con = new Conexao();
	

    public Sevlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/main")) {
			listaDeProdutos(request, response);
		}else if (action.equals("/cadastrar")) {
			novoProduto(request, response);
		}else if (action.equals("/select")) {
			mostrarProdutos(request, response);
		}else if (action.equals("/update")) {
			editarProduto(request, response);	
		}else if (action.equals("/select2")) {
			mostrarProdutos2(request, response);
		}else if (action.equals("/excluir")) {
			excluirProduto(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void listaDeProdutos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//criando um objeto que ira receber os dados Produtos.
		//response.sendRedirect("produto.jsp");
		ArrayList<Produtos> lista = con.listarProdutos();
		//for (int i = 0; i < lista.size(); i++) {
			//System.out.println(lista.get(i).getId());
			//System.out.println(lista.get(i).getCodigo());
			//System.out.println(lista.get(i).getNome());
			//System.out.println(lista.get(i).getCategoria());
			//System.out.println(lista.get(i).getValor());
			//System.out.println(lista.get(i).getQuantidade());
		//}
		request.setAttribute("listaDeProdutos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("produto.jsp");
		rd.forward(request, response);
		}
	
	protected void novoProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		produto.setCodigo(Integer.parseInt(request.getParameter("codigo")));
		produto.setNome(request.getParameter("nome"));
		produto.setCategoria((request.getParameter("categoria")));
		produto.setValor(Integer.parseInt(request.getParameter("valor")));
		produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
		//invocar o metodo inserirProduto passando o objeto produto
		con.inserirProduto(produto);
		//redirecionar para o documento produto.jsp
		response.sendRedirect("main");
	}
	
	protected void mostrarProdutos2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//recebimento do id que sera editado.
		int id = Integer.parseInt(request.getParameter("id"));
		//setar a variável Produto
		produto.setId(id);
		//executar o metodo selecionarProduto
		con.selecionarProduto(produto);
		//setar os atributos do formulario com o conteudo Produtos
		request.setAttribute("id" , produto.getId());
		request.setAttribute("codigo" , produto.getCodigo());
		request.setAttribute("nome" , produto.getNome());
		request.setAttribute("categoria" , produto.getCategoria());
		request.setAttribute("valor" , produto.getValor());
		request.setAttribute("quantidade" , produto.getQuantidade());
		//encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("excluir.jsp");
		rd.forward(request, response);
	}
	protected void mostrarProdutos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//recebimento do id que sera editado.
		int id = Integer.parseInt(request.getParameter("id"));
		//setar a variável Produto
		produto.setId(id);
		//executar o metodo selecionarProduto
		con.selecionarProduto(produto);
		//setar os atributos do formulario com o conteudo Produtos
		request.setAttribute("id" , produto.getId());
		request.setAttribute("codigo" , produto.getCodigo());
		request.setAttribute("nome" , produto.getNome());
		request.setAttribute("categoria" , produto.getCategoria());
		request.setAttribute("valor" , produto.getValor());
		request.setAttribute("quantidade" , produto.getQuantidade());
		//encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	
	protected void editarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//setar as variaveis Produto
		produto.setId(Integer.parseInt(request.getParameter("id")));
		produto.setCodigo(Integer.parseInt(request.getParameter("codigo")));
		produto.setNome(request.getParameter("nome"));
		produto.setCategoria((request.getParameter("categoria")));
		produto.setValor(Integer.parseInt(request.getParameter("valor")));
		produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
		//executar o metodo alterarProduto
		con.editarProduto(produto);
		//redirecionar para lista atualizando alteração
		response.sendRedirect("main");
		
	}
	
	protected void excluirProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		produto.setId(Integer.parseInt(request.getParameter("id")));
		//executar o metodo excluirProduto
		con.excluirProduto(produto);
		//redirecionar para lista atualizando alteração
		response.sendRedirect("main");
		
	}
	
	


}
