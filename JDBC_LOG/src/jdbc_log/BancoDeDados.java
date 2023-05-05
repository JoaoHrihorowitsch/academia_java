package jdbc_log;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;

public class BancoDeDados implements InterfaceBancoDeDados {
	private static Connection c;
	public static Log lg;
	
	
		public static void main(String[] args) throws IOException {
			
			lg = new Log("Log.txt");
			lg.logger.setLevel(Level.FINE);
			
			try {
				lg.logger.info("\n Você Está Conectado ao DB");

			} catch (Exception e) {
				lg.logger.severe( "\n Ocorreu um erro ao conectar ao DB");
	            e.printStackTrace();

			}
			
			
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
	        	  lg.logger.info("\n A opção Consultar foi selecionada.");
	              bd.consultar(consulta);
	              break;
	        	  
	          case 2:
	        	  lg.logger.info("\n A opção Inserir foi selecionada.");
	        	  String nome;
	        	  String email;
	        	  System.out.println("Informe um nome : ");
	        	  sc.nextLine();
	        	  nome = sc.nextLine();
	        	  System.out.println("Informe o email : ");
	        	  email = sc.nextLine();
	        	  
	        	  bd.inserirAlterarExcluir("INSERT INTO pessoa(nome, email) VALUES (" + "'" + nome + "'" + "," + "'" + email + "'" + ")");
	        	  lg.logger.info("\n O usário foi incluido");
	        	  System.out.println("Usuario incluido com sucesso");
	        	  break;
	        	  
	          case 3:
	        	  lg.logger.info("\n A opção Alterar foi selecionada.");
	        	  bd.consultar(consulta);
	        	  System.out.println("");
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
	        	  lg.logger.info("\n O usário foi alterado");
	         	  System.out.println("Usuario alterado com sucesso");
	        	  break;
	          case 4:
	        	  lg.logger.info("\n A opção Deletar foi selecionada.");
	        	  bd.consultar(consulta);
                  System.out.println("Selecione o id para excluir");
                  int idExcluir = sc.nextInt();
                  
                  
                  bd.inserirAlterarExcluir("DELETE FROM pessoa WHERE id = " + idExcluir);
                  lg.logger.info("\n O usário foi deletado");
                  System.out.println("Usuario excluido com sucesso");
                  break;
                  
	          case 5:
	        	  lg.logger.info("\n A opção Desconectar foi selecionada.");
	        	  bd.desconectar();
	        	  lg.logger.info("\n O usário foi Desconectado");
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
