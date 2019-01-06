import java.util.Iterator;
import java.util.Scanner;

import org.overture.codegen.runtime.VDMSeq;
import org.overture.codegen.runtime.VDMSet;

public class Interface {
	private static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		boolean end = false;
		while (!end) {
			mainMenu();
			int option = readOption();
			clearScreen();
			if (option == 0) {
				end = true;
				continue;
			} else if (option < 0 || option > 30) {
				System.out.println("\nOpção inválida");
			} else
				processInput(option);

			pressEnterKeyToContinue();
			clearScreen();
		}
		in.close();
	}

	private static void processInput(int option) {
		switch (option) {
		case 1:
			callgetVisitors();
			break;
		case 2:
			callgetExhibitors();
			break;
		case 3:
			callgetEvents();
			break;
		case 4:
			callgetRooms();
			break;
		case 5:
			callgetEvent();
			break;
		case 6:
			callgetRoom();
			break;
		case 7:
			callgetVisitor();
			break;
		case 8:
			callgetExhibitor();
			break;
		case 9:
			callregisterVisitor();
			break;
		case 10:
			callregisterExhibitor();
			break;
		case 11:
			callremoveVisitor();
			break;
		case 12:
			callremoveExhibitor();
			break;
		case 13:
			callcreateEvent();
			break;
		case 14:
			callremoveEvent();
			break;
		case 15:
			ExhibitionCentre.getInstance().closeCompletedEvents();
			System.out.println("Events Closed Successfully");
			break;
		case 16:
			callforceCloseEvent();
			break;
		case 17:
			calladdParticipantToEvent();
			break;
		case 18:
			callremoveParticipantFromEvent();
			break;
		case 19:
			calladdRoom();
			break;
		case 20:
			callremoveRoom();
			break;
		case 21:
			callgetAvailableRooms();
			break;
		case 22:
			calloverlapsEvents();
			break;
		case 23:
			callgetEventsFromVisitor();
			break;
		case 24:
			callgetEventsFromExhibitor();
			break;
		case 25:
			callgetEventStatistics();
			break;
		case 26:
			calltotalMoneySpent();
			break;
		case 27:
			calltotalProfit();
			break;
		case 28:
			callgetNotClosedEvents();
			break;
		}

	}
	
	private static void callgetNotClosedEvents() {
		VDMSet events = ExhibitionCentre.getInstance().getNotClosedEvents();
		System.out.println("Eventos: \n");
		for (Iterator iterator_24 = events.iterator(); iterator_24.hasNext();) {
			Event x = (Event) iterator_24.next();
			System.out.println(x);
		}
	}

	private static void calltotalProfit() {
		System.out.print("Insira o ID de utilizador: ");
		Number id = readInt();
		
		System.out.println("Lucro Total: " + ExhibitionCentre.getInstance().totalProfit(ExhibitionCentre.getInstance().getExhibitor(id)));
	}

	private static void calltotalMoneySpent() {
		System.out.print("Insira o ID de utilizador: ");
		Number id = readInt();
		
		System.out.println("Total gasto em eventos: " + ExhibitionCentre.getInstance().totalMoneySpent(ExhibitionCentre.getInstance().getVisitor(id)));	
	}

	private static void callgetEventStatistics() {
		System.out.print("ID a Procurar: ");
		Number id = readInt();
		
		VDMSeq statistics=  ExhibitionCentre.getInstance().getEventStatistics(ExhibitionCentre.getInstance().getEvent(id));
		
		System.out.println("\nPreço do evento:" + statistics.get(0));
		System.out.println("Custo da sala:" + statistics.get(1));
		System.out.println("Receita:" + statistics.get(2));
		System.out.println("Lucro:" + statistics.get(3));
		System.out.println("Número de participantes:" + statistics.get(4));
	}

	private static void callgetEventsFromExhibitor() {
		System.out.print("ID a Procurar: ");
		Number id = readInt();
		
		Exhibitor exhibitor= ExhibitionCentre.getInstance().getExhibitor(id);
		
		
		VDMSet events = ExhibitionCentre.getInstance().getEventsFromExhibitor(exhibitor);
		System.out.println("\nEventos do utilizador " + exhibitor.getName() +":\n");
		for (Iterator iterator_24 = events.iterator(); iterator_24.hasNext();) {
			Event x = (Event) iterator_24.next();
			System.out.println(x);
		}
		
	}

	private static void callgetEventsFromVisitor() {
		System.out.print("ID a Procurar: ");
		Number id = readInt();
		
		Visitor visitor= ExhibitionCentre.getInstance().getVisitor(id);
		
		
		VDMSet events = ExhibitionCentre.getInstance().getEventsFromVisitor(visitor);
		System.out.println("\nEventos do utilizador" + visitor.getName() +":\n");
		for (Iterator iterator_24 = events.iterator(); iterator_24.hasNext();) {
			Event x = (Event) iterator_24.next();
			System.out.println(x);
		}
		
	}

	private static void calloverlapsEvents() {
		System.out.println("Insira a data inicial do Evento: ");
		Number date[]= readDate();
		Date iDate = new Date(date[0], date[1], date[2]);
		System.out.println("\nInsira a data final do Evento");
		date = readDate();
		Date fDate = new Date(date[0], date[1], date[2]);
		System.out.print("ID da Sala: ");
		Number id_room=readInt();
		
		if(ExhibitionCentre.getInstance().overlapsEvents(iDate, fDate, id_room))
			System.out.println("\nSala indisponivel entre estas datas");
		else 
			System.out.println("\nA sala está disponível entre essas datas");
	}

	private static void callgetAvailableRooms() {
		System.out.println("Insira a data inicial: ");
		Number date[]= readDate();
		Date iDate = new Date(date[0], date[1], date[2]);
		System.out.println("\nInsira a data final");
		date = readDate();
		Date fDate = new Date(date[0], date[1], date[2]);
		
		System.out.println("\nSalas disponíveis entre " + iDate + " e " + fDate + ": ");
		
		VDMSet rooms = ExhibitionCentre.getInstance().getAvailableRooms(iDate, fDate);
		for (Iterator iterator_25 = rooms.iterator(); iterator_25.hasNext();) {
			Room x = (Room) iterator_25.next();
			System.out.println(x);
		}
	}

	private static void callremoveRoom() {
		System.out.print("Insira o ID da Sala: ");
		Number id = readInt();
		ExhibitionCentre.getInstance().removeRoom(ExhibitionCentre.getInstance().getRoom(id));
		System.out.println("Removed Succefully");	
	}

	private static void calladdRoom() {
		System.out.print("Insira Preço de reserva da sala por um dia: ");
		Number price = readInt();
		System.out.print("Insira o número máximo de pessoas que podem comparecer na sala ao mesmo tempo ");
		Number space = readInt();
		System.out.println("Sala foi criada, não se esqueça do seu id:");
		System.out.println("\n"+ExhibitionCentre.getInstance().addRoom(price, space));
	}

	private static void callremoveParticipantFromEvent() {
		System.out.print("Insira o ID do Evento: ");
		Number id = readInt();
		System.out.print("Insira o ID do Utilizador: ");
		Number id2 = readInt();
		ExhibitionCentre.getInstance().removeParticipantFromEvent(ExhibitionCentre.getInstance().getEvent(id), 
				ExhibitionCentre.getInstance().getVisitor(id2));
	}

	private static void calladdParticipantToEvent() {
		System.out.print("Insira o ID do Evento: ");
		Number id = readInt();
		System.out.print("Insira o ID do Utilizador: ");
		Number id2 = readInt();
		ExhibitionCentre.getInstance().addParticipantToEvent(ExhibitionCentre.getInstance().getEvent(id), 
				ExhibitionCentre.getInstance().getVisitor(id2));
	}

	private static void callforceCloseEvent() {
		System.out.print("Insira o ID do Evento: ");
		Number id = readInt();
		System.out.print("Insira a password: ");
		String password= readString();
		ExhibitionCentre.getInstance().forceCloseEvent(ExhibitionCentre.getInstance().getEvent(id), password);
		System.out.println("Removed Succefully");	
		
	}

	private static void callremoveEvent() {
		System.out.print("Insira o ID do Evento: ");
		Number id = readInt();
		ExhibitionCentre.getInstance().removeEvent(ExhibitionCentre.getInstance().getEvent(id));
		System.out.println("Removed Succefully");	
		
	}

	private static void callcreateEvent() {
	System.out.println("Insira a data inicial do Evento: ");
	Number date[]= readDate();
	Date iDate = new Date(date[0], date[1], date[2]);
	System.out.println("\nInsira a data final do Evento");
	date = readDate();
	Date fDate = new Date(date[0], date[1], date[2]);
	System.out.print("ID da Sala: ");
	Number id_room=readInt();
	System.out.print("ID de Organizador: ");
	Number id_organizer=readInt();
	System.out.print("Preço de participação no evento: ");
	Number price=readInt();
	System.out.print("Nome do evento: ");
	String name=readString();
	
	System.out.println("Evento foi criado, não se esqueça do seu id:");
	System.out.println("\n"+ExhibitionCentre.getInstance().createEvent(iDate, fDate, 
			ExhibitionCentre.getInstance().getRoom(id_room), ExhibitionCentre.getInstance().getExhibitor(id_organizer), price, name));
		
	}

	private static void callremoveExhibitor() {
		System.out.print("Insira o ID de Organizador: ");
		Number id = readInt();
		ExhibitionCentre.getInstance().removeExhibitor(ExhibitionCentre.getInstance().getExhibitor(id));
		System.out.println("Removed Succefully");	
	}

	private static void callremoveVisitor() {
		System.out.print("Insira o ID de utilizador: ");
		Number id = readInt();
		ExhibitionCentre.getInstance().removeVisitor(ExhibitionCentre.getInstance().getVisitor(id));
		System.out.println("Removed Succefully");	
	}

	private static void callregisterExhibitor() {
		System.out.print("Insira o seu nome: ");
		String name = readString();
		
		System.out.println("Utilizador foi criado, não se esqueça do seu id:");
		System.out.println("\n"+ExhibitionCentre.getInstance().registerExhibitor(name));	
	}

	private static void callregisterVisitor() {
		System.out.print("Insira o seu nome: ");
		String name = readString();
		
		System.out.println("Utilizador foi criado, não se esqueça do seu id:");
		System.out.println("\n"+ExhibitionCentre.getInstance().registerVisitor(name));
		
	}

	private static void callgetExhibitor() {
		System.out.print("ID a Procurar: ");
		Number id = readInt();
		
		System.out.println("\n"+ExhibitionCentre.getInstance().getExhibitor(id));
		
	}

	private static void callgetVisitor() {
		System.out.print("ID a Procurar: ");
		Number id = readInt();
		
		System.out.println("\n"+ExhibitionCentre.getInstance().getVisitor(id));
		
	}

	private static void callgetRoom() {
		System.out.print("ID a Procurar: ");
		Number id = readInt();
		
		System.out.println("\n"+ExhibitionCentre.getInstance().getRoom(id));
	}

	private static void callgetEvent() {
		System.out.print("ID a Procurar: ");
		Number id = readInt();
		
		System.out.println("\n"+ExhibitionCentre.getInstance().getEvent(id).toStringComplete());
	}
	
	private static String readString() {
		try {
			return in.next();
		} catch (Exception e) {
		}
		return "";
	}
	
	private static Number[] readDate() {
		try {
			Number []date =  new Number [3];
			System.out.print("year: ");
			date[0] = in.nextInt();
			System.out.print("month: ");
			date[1] = in.nextInt();
			System.out.print("day: ");
			date[2] = in.nextInt();
			return date;
		} catch (Exception e) {
		}
		return null;
	}

	private static Number readInt() {
		try {
			
			return in.nextInt();
		} catch (Exception e) {
		}
		return -1;
	}

	private static void callgetRooms() {
		VDMSet rooms = ExhibitionCentre.getInstance().getRooms();
		System.out.println("Salas Disponíveis para Reserva: \n");
		for (Iterator iterator_25 = rooms.iterator(); iterator_25.hasNext();) {
			Room x = (Room) iterator_25.next();
			System.out.println(x);
		}
	}

	private static void callgetEvents() {
		VDMSet events = ExhibitionCentre.getInstance().getEvents();
		System.out.println("Eventos: \n");
		for (Iterator iterator_24 = events.iterator(); iterator_24.hasNext();) {
			Event x = (Event) iterator_24.next();
			System.out.println(x);
		}
	}

	private static void callgetExhibitors() {
		VDMSet exhibitors = ExhibitionCentre.getInstance().getExhibitors();
		System.out.println("Organizadores: \n");
		for (Iterator iterator_27 = exhibitors.iterator(); iterator_27.hasNext();) {
			Exhibitor x = (Exhibitor) iterator_27.next();
			System.out.println(x);
		}

	}

	private static void callgetVisitors() {
		VDMSet visitors = ExhibitionCentre.getInstance().getVisitors();
		System.out.println("Utilizadores: \n");
		for (Iterator iterator_26 = visitors.iterator(); iterator_26.hasNext();) {
			Visitor x = (Visitor) iterator_26.next();
			System.out.println(x);
		}
	}

	private static void call() {
		// TODO Auto-generated method stub

	}

	public static void mainMenu() {
		System.out.println("\t\t\t\t\t\t\t\t\t\t--WELCOME TO FIL- Center of Exhibitions of Lisbon--\n\n");
		System.out.println(" 1 - Ver Utilizadores Registados");
		System.out.println(" 2 - Ver Organizadores Registados");
		System.out.println(" 3 - Ver todos os Eventos");
		System.out.println(" 4 - Ver salas disponíveis para reserva");
		System.out.println(" 5 - Procurar um Evento");
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
		System.out.println("19 - Adicionar uma sala");
		System.out.println("20 - Remover uma sala");
		System.out.println("21 - Ver salas disponiveis para reserva entre duas datas");
		System.out.println("22 - Posso Reservar uma determinada sala entre duas datas ");
		System.out.println("23 - Ver todos os eventos em que um utilizador é participante");
		System.out.println("24 - Ver todos os eventos de um organizador");
		System.out.println("25 - Ver estatísticas de um Evento");
		System.out.println("26 - Ver total de dinheiro gasto por um utilizador");
		System.out.println("27 - Ver total de lucro de um Organizador");
		System.out.println("28 - Ver todos os eventos não concluídos");
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
			return in.nextInt();
		} catch (Exception e) {
		}
		return -1;
	};

}
