import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

public class Aps2 {
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
		String mostrar = null;

		while (true) {

			if (listaTransicoes.isEmpty()) {
				mostrar = "Lista Vazia";
				// System.out.println(mostrar);
			} else {
				String[] novaLista = new String[listaTransicoes.size()];
				for (int test = 0; test < listaTransicoes.size(); test++) {
					String estadoAtual = listaTransicoes.get(test).getEstadoAtual();
					String caractere = listaTransicoes.get(test).getCaractere();
					String estadoSeguinte = listaTransicoes.get(test).getEstadoSeguinte();
					novaLista[test] = estadoAtual + caractere + estadoSeguinte;
				}
				mostrar = Arrays.toString(novaLista);
				// System.out.println(Arrays.toString(novaLista) + listaTransicoes.size());
			}

			String transicoesEstados = JOptionPane.showInputDialog(
					"Entre com as transições de estados: \n" + mostrar + "\nPara seguir basta clicar em `cancelar`");

			if (transicoesEstados == null || transicoesEstados.equals("")) {
				break;
			} else {
				String array[] = new String[100];

				array = transicoesEstados.split(",");
				Transicao transicao = new Transicao();

				for (int i = 0; i < array.length; i++) {
					transicao.setEstadoAtual(array[0]);
					transicao.setCaractere(array[1]);
					transicao.setEstadoSeguinte(array[2]);
				}
				listaTransicoes.add(transicao);
			}
		}

		boolean situacao = VerificarAFNouAFD(estadoInicial, arrayConjuntoEstadosFinais, arrayAlfabeto, listaTransicoes);

		if (situacao == true) {
			JOptionPane.showMessageDialog(null, ExibicaoAFD());
		} else {
			JOptionPane.showMessageDialog(null, ExibicaoAFN());
		}

	}

	public static boolean VerificarAFNouAFD(String estadoInicial, String[] arrayConjuntoEstadosFinais,
			String[] arrayAlfabeto, List<Transicao> listaTransicoes) {

		boolean situacao = false;
		int contador = 0;
		boolean situacaoInterna = false;

		String Proximo = null;

		for (int i = 0; i < arrayAlfabeto.length; i++) {

			System.out.println("Primeira letra do alfabeto: " + arrayAlfabeto[i] + " Estado: " + estadoInicial + "\n");

			String letra = arrayAlfabeto[i];

			// EXECUTA PRIMEIRO
			for (int j = 0; j < listaTransicoes.size(); j++) {

				String estadoAtual = listaTransicoes.get(j).getEstadoAtual();
				String caractere = listaTransicoes.get(j).getCaractere();
				String estadoSeguinte = listaTransicoes.get(j).getEstadoSeguinte();

				System.out.println("TESTE 2 NAO ENCONTRO LIGACAO: " + i + " " + estadoAtual + " " + caractere + " "
						+ estadoSeguinte + "\n");

				if (estadoInicial.equals(estadoAtual) && caractere.equals(letra)) {

					System.out.println("TESTE 3 ENCONTROU UM LIGAÇAO:" + " " + estadoAtual + " " + caractere + " "
							+ estadoSeguinte + "\n");

					Proximo = estadoSeguinte;

					contador++;

					System.out.println("ACIONANDO O CONTADOR DE LIGACAO NO TESTE3: " + contador + "\n");

				} else {
					// System.out.println(contador);
				}
			}

			// VERIFICAR CONTADOR E PROXIMO
			if (contador == 1 && Proximo == null) {

				System.out.println("TRUE - AFD");
				situacao = true;

			} else if (contador == 1 && Proximo != null) {

				String letraProximo;

				for (int novoi = 0; novoi < arrayAlfabeto.length; novoi++) {

					letraProximo = arrayAlfabeto[novoi];

					System.out.println(
							"Primeira letra do alfabeto TESTE11: " + letraProximo + " Estado: " + Proximo + "\n");

					// String letra2 = arrayAlfabeto[novoi];
					int contadorInterno = 0;

					System.out.println("SITUACAO INTERNA: " + situacaoInterna + "\n");

					if (situacaoInterna == false) {
						for (int novoj = 0; novoj < listaTransicoes.size(); novoj++) {

							String estadoAtual = listaTransicoes.get(novoj).getEstadoAtual();
							String caractere = listaTransicoes.get(novoj).getCaractere();
							String estadoSeguinte = listaTransicoes.get(novoj).getEstadoSeguinte();

							System.out.println("CONTADOR INTERNO: " + contadorInterno + "\n");

							System.out.println("CASO " + novoj + " VALORES DA LISTA DE TRANSICOES " + estadoAtual + " "
									+ caractere + " " + estadoSeguinte + "\n");

							System.out.println(
									"CASO " + novoj + " VALORES PARA ANALISAR " + Proximo + " " + letraProximo + "\n");

							System.out.println("CASO " + novoj + " ESTADO ATUAL DO PROXIMO: " + Proximo + " "
									+ estadoAtual + "\n");

							System.out.println(
									"CASO " + novoj + " LETRA DO PROXIMO: " + caractere + " " + letraProximo + "\n");

							if (contadorInterno < 2) {

								if (Proximo.equals(estadoAtual) && caractere.equals(letraProximo)) {

									if (arrayConjuntoEstadosFinais[novoi].equals(estadoSeguinte)) {
										System.out.println("TRUE - AFD");
										situacao = true;
										break;
									}

									contadorInterno++;

									System.out.println("TESTE 33 CASO " + novoj + " " + estadoAtual + " " + caractere
											+ " " + estadoSeguinte);

									Proximo = estadoSeguinte;

									contador++;

									System.out.println("ACIONANDO O CONTADOR: " + contador);

								}

							} else if (contadorInterno == 2) {
								System.out.println("PARA AQUI - MUDA PARA AFN");
								situacaoInterna = true;
								break;
							}
						}
					} else if (situacaoInterna == true) {
						System.out.println("PARA AQUI2 - MUDA PARA AFN");
						situacao = false;
						break;
					}

				}

			} else if (contador >= 2 && Proximo != null && situacaoInterna != true) {
				System.out.println("TRUE - AFD");
				situacao = true;
			}
		}

		return situacao;

	}

	public static String ExibicaoAFD() {
		String exibicao = "";
		exibicao += " ===================================================\n";
		exibicao += "É um AFD";
		exibicao += "\n ===================================================";
		return exibicao;
	}

	public static String ExibicaoAFN() {
		String exibicao = "";
		exibicao += " ===================================================\n";
		exibicao += "É um AFN";
		exibicao += "\n ===================================================";
		return exibicao;
	}

}
