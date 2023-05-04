import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;

public class Principal {

	public static void main(String[] args) throws IOException {
		
		Log meuLogger = new Log("Log.txt");
		meuLogger.logger.setLevel(Level.FINE);
		try {
			meuLogger.logger.info("\n Calculadora foi iniciado");

		} catch (Exception e) {
			meuLogger.logger.severe( "\n A calculadora deu erro");
            e.printStackTrace();

		}
		
		try (Scanner leitura = new Scanner(System.in)) {
			Calculadora c = new Calculadora();
			float a = 0, b = 0, resultado = 0;
			String operador;
			System.out.println("Calculadora");
			do {
				System.out.println("Insira o operador +, -, *, /");
				operador = leitura.nextLine();
				meuLogger.logger.info("\n A operação escolhida foi " + operador);
			} while (!(operador.contains("+") || operador.contains("-") || operador.contains("*")
					|| operador.contains("/")));

			System.out.println("Insira o 1o valor");
			a = leitura.nextFloat();
			System.out.println("Insira o 2o valor");
			b = leitura.nextFloat();

			resultado = c.calcular(a, b, operador.charAt(0));
			System.out.println("O resultado de " + a + " " + operador + " " + b + " �:" + resultado);
			meuLogger.logger.info("\n A calculadora concluiu a operação e está sendo encerrada.");
		}

	}

}
