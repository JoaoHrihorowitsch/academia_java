package com.Um;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.Produtos.Produtos;

public class Conexao {
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/projeto";
	private String user = "root";
	private String password = "";
	
	
	private Connection conectar() {
		Connection c = null;
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(url, user, password);
			System.out.println("Você está conectado ao DB");
			return c;		
		} catch (Exception e) {
			System.out.println("Não foi possivel conectar" +e);
			return null;

		}		
	}
	
	/**CRUD CREATE**/
	public void inserirProduto(Produtos produto) {
		String create = "INSERT INTO produto (codigo, nome, categoria, valor, quantidade) values (?,?,?,?,?)";
		
		try {//abrir a conexao
			Connection c = conectar();
			//preparar a querry para a execução no bd
			PreparedStatement ps = c.prepareStatement(create);
			//trocar os parametros "?" pelo conteudo das variaveis Produtos
			ps.setInt(1, produto.getCodigo());
			ps.setString(2, produto.getNome());
			ps.setString(3, produto.getCategoria());
			ps.setInt(4, produto.getValor());
			ps.setInt(5, produto.getQuantidade());
			//executar a querry
			ps.executeUpdate();
			//fechar a conexao
			c.close();
			
		} catch (Exception e) {
			System.out.println(e);

		}
	}
	
	/** CRUD READ **/
	public ArrayList<Produtos> listarProdutos(){
		//criando obj para a acss a class Produtos
		ArrayList<Produtos> produtos = new ArrayList<>();
		String read = "SELECT * FROM produto ";
		try {
			Connection c = conectar ();
			PreparedStatement ps = c.prepareStatement(read);
			//obj criado para executar query (select....)
			ResultSet rs = ps.executeQuery();
			//laço abaixo sera executado enqt houver contatos
			while(rs.next()) {
				//variaveis de apoio que receber dados do banco
				int id = rs.getInt(1);
				int codigo = rs.getInt(2);
				String nome = rs.getString(3);
				String categoria = rs.getString(4);
				int valor = rs.getInt(5);
				int quantidade = rs.getInt(6);
				//populando o arraylist
				produtos.add(new Produtos(id, codigo, nome, categoria, valor, quantidade));
			}
			c.close();
			return produtos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	/**CRUD UPDATE**/
	//selecionar o produto
	public void selecionarProduto(Produtos produto) {
		String read2 = "SELECT * FROM produto WHERE id = ?";
		try {
			Connection c = conectar();
			PreparedStatement ps = c.prepareStatement(read2);
			ps.setInt(1, produto.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				//setar as variais Produto
				produto.setId(rs.getInt(1));
				produto.setCodigo(rs.getInt(2));
				produto.setNome(rs.getString(3));
				produto.setCategoria(rs.getString(4));
				produto.setValor(rs.getInt(5));
				produto.setQuantidade(rs.getInt(6));
			}
			c.close();
		} catch (Exception e) {
			System.out.println(e);
			
		}
	}
	
	//Editar Produto
	public void editarProduto(Produtos produto) {
		String update = "UPDATE produto SET codigo=?, nome=?, categoria=?, valor=?, quantidade=? WHERE id=?";
		try {
			Connection c = conectar();
			PreparedStatement ps = c.prepareStatement(update);
			ps.setInt(1, produto.getCodigo());
			ps.setString(2, produto.getNome());
			ps.setString(3, produto.getCategoria());
			ps.setInt(4, produto.getValor());
			ps.setInt(5, produto.getQuantidade());
			ps.setInt(6, produto.getId());
			ps.executeUpdate();
			
			c.close();
		} catch (Exception e) {
			System.out.println(e);
			
		}
	}
	
	public void excluirProduto(Produtos produto) {
		String excluir = "DELETE FROM produto WHERE id=?";
		try {
			Connection c = conectar();
			PreparedStatement ps = c.prepareStatement(excluir);
			ps.setInt(1, produto.getId());
			ps.executeUpdate();
			
			c.close();
		} catch (Exception e) {
			System.out.println(e);
			
		}
	}



	

}
