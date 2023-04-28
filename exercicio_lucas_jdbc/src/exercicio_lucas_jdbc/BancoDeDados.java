package exercicio_lucas_jdbc;
import java.sql.*;
import java.util.Scanner;

public class BancoDeDados implements InterfaceBancoDeDados {
	private static Connection c;
	
		public static void main(String[] args) {
			
			String db_url ="jdbc:mysql://localhost:3306/jdbc";
			String user = "root";
			String password = "";
			
			Scanner sc = new Scanner (System.in);
			int opçao = 0;
			
			try {
			    c = DriverManager.getConnection(db_url, user, password);
				System.out.println("Conectado ao DB");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Não foi possivel conectar ao DB");
			}	
			
			
		while(opçao != 5) {	
			System.out.println("");
			System.out.println("Selecione a opção desejada:");
			System.out.println("");
	        System.out.println("1 - Consultar");
	        System.out.println("2 - Inserir");
	        System.out.println("3 - Alterar");
	        System.out.println("4 - Excluir");
	        System.out.println("5 - Desconectar");
	        
	        BancoDeDados bd = new BancoDeDados();
	        String consulta = "SELECT * FROM pessoa";
	        
	        opçao = sc.nextInt();
	        
	        switch(opçao) {
	        
	          case 1:
	              bd.consultar(consulta);
	              break;
	        	  
	          case 2:
	        	  String nome;
	        	  String email;
	        	  System.out.println("Informe um nome : ");
	        	  sc.nextLine();
	        	  nome = sc.nextLine();
	        	  System.out.println("Informe o email : ");
	        	  email = sc.nextLine();
	        	  
	        	  bd.inserirAlterarExcluir("INSERT INTO pessoa(nome, email) VALUES (" + "'" + nome + "'" + "," + "'" + email + "'" + ")");
	        	  System.out.println("Usuario incluido com sucesso");
	        	  break;
	        	  
	          case 3:
	        	  bd.consultar(consulta);
	        	  System.out.println("Selecione o id para alterar:");
	        	  int idAlterar = sc.nextInt();
	        	  String novoNome;
	        	  String novoEmail;
	        	  System.out.println("Informe o novo nome : ");
	        	  sc.nextLine();
	        	  novoNome = sc.nextLine();
	        	  System.out.println("Informe o novo email : ");
	        	  novoEmail = sc.nextLine();
	        	  
	        	  bd.inserirAlterarExcluir("UPDATE pessoa SET nome = "+ "'" + novoNome + "'" + "," + "email = " + "'" + novoEmail + "'" + " WHERE id = " + idAlterar);
	         	  System.out.println("Usuario alterado com sucesso");
	        	  break;
	          case 4:
	        	  bd.consultar(consulta);
                  System.out.println("Selecione o id para excluir");
                  int idExcluir = sc.nextInt();
                  
                  
                  bd.inserirAlterarExcluir("DELETE FROM pessoa WHERE id = " + idExcluir);
                  System.out.println("Usuario excluido com sucesso");
                  break;
                  
	          case 5:
	        	  bd.desconectar();
	        	  System.out.println("Você desconectou do DB com sucesso.");
	        	  break;
	        }
	        
		}
		
		sc.close();
				
   }

		@Override
		public void conectar(String db_url, String user, String password) {
			try {
			    c = DriverManager.getConnection(db_url, user, password);
				System.out.println("Conectado ao DB");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Não foi possivel conectar ao DB");
			}	
			
			// TODO Auto-generated method stub
			
		}

		@Override
		public void desconectar() {
			try {
				c.close();
			}catch(SQLException e) {
				 System.out.println("Não foi possível desconectar do BD.");
		            e.printStackTrace();
			}
			// TODO Auto-generated method stub
			
		}

		@Override
		public void consultar(String db_query) {
			try {
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery(db_query);
				while(rs.next()) {
					 System.out.println(rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3));
				}	
				rs.close();
				stmt.close();
				
			}catch(SQLException e) {
				 System.out.println("Não foi possível executar a operação desejada.");
		            e.printStackTrace();	
			}
			// TODO Auto-generated method stub
			
		}

		@Override
		public int inserirAlterarExcluir(String db_query) {
			int linhas = 0;
			try {
				Statement stmt = c.createStatement();
				linhas = stmt.executeUpdate(db_query);
				stmt.close();
			}catch(SQLException e) {
				 System.out.println("Não foi possível executar a operação desejada.");
		            e.printStackTrace();
			}
			System.out.println(linhas + " linha foi alterada.");
	        return linhas;
		}
		// TODO Auto-generated method stub

}
