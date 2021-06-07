import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Questao1 {
	public static void main(String[] args) {

		// Conjunto de Estados Finitos

		String conjuntoEstados = JOptionPane.showInputDialog("Entre com um conjunto de estados finitos:");

		String arrayConjuntoEstados[] = new String[100];

		arrayConjuntoEstados = conjuntoEstados.split(",");

		// Estado Inicial

		String estadoInicial = JOptionPane.showInputDialog("Entre com o estado inicial:");

		// Alfabeto

		String alfabeto = JOptionPane.showInputDialog("Entre com o alfabeto:");

		String arrayAlfabeto[] = new String[100];

		arrayAlfabeto = alfabeto.split(",");

		// Conjunto de Estados Finais

		String conjuntoEstadosFinais = JOptionPane
				.showInputDialog("Entre com um conjunto de estados finais formada por 1 estado final:");

		String arrayConjuntoEstadosFinais[] = new String[100];

		arrayConjuntoEstadosFinais = conjuntoEstadosFinais.split(",");

		// Conjunto de Estados Finais

		ArrayList<Transicao> listaTransicoes = new ArrayList<>();

		while (true) {

			String transicoesEstados = JOptionPane
					.showInputDialog("Entre com as transições de estados e para seguir basta clicar em cancelar");

			if (transicoesEstados == null || transicoesEstados.equals("")) {
				break;
			} else {
				String array[] = new String[100];

				array = transicoesEstados.split(",");

				for (int i = 0; i < array.length; i++) {
					Transicao transicao = new Transicao();
					transicao.setEstadoAtual(array[0]);
					transicao.setCaractere(array[1]);
					transicao.setEstadoSeguinte(array[2]);
					listaTransicoes.add(transicao);
				}

			}
		}

		String palavra = JOptionPane.showInputDialog("Entre com a String: ");

		boolean situacao = VerificarAFNouAFD(estadoInicial, arrayConjuntoEstadosFinais, palavra, listaTransicoes);

		if (situacao == true) {
			JOptionPane.showMessageDialog(null, montaExibicao(palavra));
		} else {
			JOptionPane.showMessageDialog(null, montaExibicaoErro(palavra));
		}

	}

	public static boolean VerificarAFNouAFD(String estadoInicial, String[] arrayConjuntoEstadosFinais, String palavra,
			List<Transicao> listaTransicoes) {

		boolean situacao = false;

		String Proximo = null, EstadoFinal = null;
		char TerceiraString = 1;

		for (int i = 0; i < palavra.length();) {

			System.out.println("StringPrimeiraString " + i + ": " + palavra.charAt(i));

			char primeiraString = palavra.charAt(i);

			Proximo = proximoEstado(estadoInicial, primeiraString, listaTransicoes);

			System.out.println("TESTE 3 VALOR PROXIMO: " + Proximo);

			break;
		}

		for (int i = 1; i < palavra.length(); i++) {

			System.out.println("StringSegundaString " + i + ": " + palavra.charAt(i));

			char segundaString = palavra.charAt(i);

			Proximo = proximoEstado(Proximo, segundaString, listaTransicoes);

			System.out.println("TESTE 4 ESTADO FINAL: " + Proximo);

			if (Proximo.equals(arrayConjuntoEstadosFinais[0])) {
				situacao = true;
				break;
			} else {
				// NADA
			}

		}

		return situacao;

	}

	public static String proximoEstado(String estadoInicial, char primeiraString, List<Transicao> listaTransicoes) {

		String proximoEstado = null;

		System.out.println("TESTE 1 estadoInicial: " + estadoInicial + " primeiraString " + primeiraString);

		for (int i = 0; i < listaTransicoes.size(); i++) {
			String estadoAtual = listaTransicoes.get(i).getEstadoAtual();
			String caractere = listaTransicoes.get(i).getCaractere();
			String estadoSeguinte = listaTransicoes.get(i).getEstadoSeguinte();

			// System.out.println("TESTE 2 para id: " + i + " " + estadoAtual + " " +
			// caractere + " " + estadoSeguinte);

			if (estadoInicial.equals(estadoAtual) && caractere.equals(Character.toString(primeiraString))) {
				proximoEstado = estadoSeguinte;
			} else {
				// proximoEstado = estadoInicial;
			}
		}

		return proximoEstado;
	}

	public static String montaExibicao(String string) {
		String exibicao = "";
		exibicao += " ===================================================\n";
		exibicao += "A string " + string + " é aceito pelo AFD";
		exibicao += "\n ===================================================";
		return exibicao;
	}

	public static String montaExibicaoErro(String string) {
		String exibicao = "";
		exibicao += " ===================================================\n";
		exibicao += "A string " + string + " não é aceito pelo AFD";
		exibicao += "\n ===================================================";
		return exibicao;
	}

}
