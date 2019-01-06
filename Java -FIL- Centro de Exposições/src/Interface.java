import java.io.IOException;
import java.util.Scanner;

public class Interface {

	public static void main(String[] args) {
		boolean end = false;
		while (!end) {
			mainMenu();
			int option = readOption();
			clearScreen();
			if (option == 0) {
				end = true;
				continue;
			}
			else if (option < 0 || option > 30) {
				System.out.println("\nOpção inválida");
			} else
				processInput(option);
			
			pressEnterKeyToContinue();
			clearScreen();
		}
	}

	private static void processInput(int option) {
		// TODO Auto-generated method stub

	}

	public static void mainMenu() {
		System.out.println("\t\t\t\t\t\t\t\t\t\t--WELCOME TO FIL- Center of Exhibitions of Lisbon--\n\n");
		System.out.println(" 1 - Ver Utilizadores Registados");
		System.out.println(" 2 - Ver Organizadores Registados");
		System.out.println(" 3 - Ver todos os Eventos");
		System.out.println(" 4 - Ver salas disponíveis para reserva");
		System.out.println(" 5 - Procurar um Rvento");
		System.out.println(" 6 - Procurar uma Sala");
		System.out.println(" 7 - Procurar um Utilizador");
		System.out.println(" 8 - Procurar um Organizador");
		System.out.println(" 9 - Registar um Utilizador");
		System.out.println("10 - Registar um Organizador");
		System.out.println("11 - Remover um Utilizador");
		System.out.println("12 - Remover um Organizador");
		System.out.println("13 - Criar um Evento");
		System.out.println("14 - Remover um Evento");
		System.out.println("15 - Fechar Eventos Concluídos");
		System.out.println("16 - Forçar um Fecho de um evento (requer uma password especial, PASSWORD especial)");
		System.out.println("17 - Adicionar Participante a um evento");
		System.out.println("18 - Remover Participante de um evento");
		System.out.println("19 - Adicionar Participantes a um evento");
		System.out.println("20 - Remover Participantes de um evento");
		System.out.println("21 - Adicionar uma sala");
		System.out.println("22 - Remover uma sala");
		System.out.println("23 - Ver salas disponiveis para reserva entre duas datas");
		System.out.println("24 - Posso Reservar uma determinada sala entre duas datas ");
		System.out.println("25 - Ver todos os eventos em que um utilizador é participante");
		System.out.println("26 - Ver todos os eventos de um organizador");
		System.out.println("27 - Ver estatísticas de um Evento");
		System.out.println("28 - Ver total de dinheiro gasto por um utilizador");
		System.out.println("29 - Ver total de lucro de um Organizador");
		System.out.println("30 - Ver todos os eventos não concluídos");
		System.out.println("0 - Fechar Programa");

		System.out.print("\nEscolha uma opção: ");
	}

	public static void clearScreen() {
		System.out.println(new String(new char[50]).replace("\0", "\r\n"));
	}

	private static void pressEnterKeyToContinue() {
		System.out.println("\nPress Enter key to continue...");
		try {
			System.in.read();
		} catch (Exception e) {
		}
	}

	private static int readOption() {
		try {
			Scanner in = new Scanner(System.in);
			return in.nextInt();
		} catch (Exception e) {
		}
		return -1;
	};

}
