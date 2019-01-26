package elementy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import java.net.UnknownHostException;

/**
 * Klasa s�u��ca do komunikacji z serwerem
 */
public class Client {
	/**
	 * Numer poziomu kt�ry ma zosta� wczytany
	*/
	static int level = 1;
	
	/**
	 * Metoda sprawdzaj�ca po��czenie z serwerem
	 * @param ip numer ip serwera
	 * @param port port na kt�rym mo�emy po��czy� si� z serwerem
	 * @return answer odpowied� serwera
	 */
	public static String[] checkServer(String ip, String port) {
		String[] answer;
		try {
			answer = connection(Protocol.CHECK, ip, port);
		} catch (NumberFormatException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return answer;
	}
	
	/**
	 * Metoda pobieraj�ca liczb� map na serwerze
	 * @param ip numer ip serwera
	 * @param port port na kt�rym mo�emy po��czy� si� z serwerem
	 * @return answer odebrana od serwera warto�� oznaczaj�ca liczb� map na serwerze
	 */
	public static String[] getNumberMaps(String ip, String port) {
		String[] answer;
		try {
			answer = connection(Protocol.GETNUMBERMAPS, ip, port);
		} catch (NumberFormatException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return answer;
	}
	/**
	 * Metoda pobieraj�ca liste najlepszych wynik�w z serwera
	 * @param ip numer ip serwera
	 * @param port port na kt�rym mo�emy po��czy� si� z serwerem
	 * @return answer odebrane od serwera najlepsze wyniki
	 */
	public static String[] getHighScores(String ip, String port) {
		String[] answer;
		try {
			answer = connection(Protocol.GETHIGHSCORES, ip, port);
		} catch (NumberFormatException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return answer;
	}
	
	/**
	 * Metoda pobieraj�ca map� z serwera
	 * @param ip numer ip serwera
	 * @param port port na kt�rym mo�emy po��czy� si� z serwerem
	 * @param Level numer mapy kt�ra ma zosta� pobrana, zmienia warto�� statycznej 'level'
	 * @return answer odebrana od serwera mapa
	 */
	public static String[] getMap(String ip, String port, int Level) {
		String[] answer;
		level=Level;
		try {
			answer = connection(Protocol.GETMAP, ip, port);
		} catch (NumberFormatException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return answer;
	}
	

	/**
	 * Metoda pwysy�aj�ca wynki uzyskany przez u�ytkownika do serwera
	 * @param name nick u�ytkownika
	 * @param score wynik u�ytkownika
	 */
	public static void sendScore(String name, int score) {

		Socket socket;
		try {
			socket = new Socket(config.ip, 5000);
			socket.setSoTimeout(3000);
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			pw.println(Protocol.SENDINGSCORE);
			pw.println(name);
			pw.println(Integer.toString(score));
		} catch (UnknownHostException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metoda odpowiadaj�ca za po��cznie z serwerem
	 * @param command zmienna do wyboru odpowiedniego protoko�u
	 * @param ip numer ip serwera
	 * @param port port na kt�rym mo�emy po��czy� si� z serwerem
	 * @return zwraca warto�� o kt�r� wys�ali�my ��danie
	 */
	private static String[] connection(String command, String ip, String port)
			throws NumberFormatException, IOException 
	{

		Socket socket = new Socket(ip, Integer.parseInt(port));
		socket.setSoTimeout(3000);
		OutputStream os = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(os, true);
		InputStream is = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		pw.println(command);

		switch (command) {
		/**
		 * Protok� sprwadzj�cy po��czenie klient-serwer
		 * @return getLines odpowied� serwera zapisana w tablicy string�w
		 */
		case Protocol.CHECK: {
			return getLines(1, br, socket);
		}
		/**
		 * Protok� pobieraj�cy liczb� map na serwerze
		 * @return getLines odebrana od serwera warto�� oznaczaj�ca liczb� map na serwerze zapisana w tablicy string�w
		 */
		case Protocol.GETNUMBERMAPS: {
			return getLines(1, br, socket);
		}
		/**
		 * Protok� pobieraj�cy najlepsze wyniki
		 * @return getLines odebrane od serwera najlepsze wyniki zapisane w tablicy string�w
		 */
		case Protocol.GETHIGHSCORES: {
			return getLines(20, br, socket);
		}
		/**
		 * Protok� pobieraj�cy map�
		 * @return getLines odebrana od serwera mapa zapisana w tablicy string�w
		 */
		case Protocol.GETMAP: {
			pw.println(level);    
			return getLines(1 , br, socket);
		}

		default: {
			socket.close();
			throw new IOException("Nieznana Komenda");
		}
		}
	}
	
	/**
	 * Metoda odczytuj�ca zmienn� anwser i zapisuj�ca informacje do tablicy string�w
	 * @param count zmienna okre�laj�ca wielkos� tablicy
	 * @param br zmienna do odczytania z bufora
	 * @param socket zmienna ��cz�ca si� z odpowiednim socketem
	 * @return getLines zwr�cona wiadomo�c zapisana w tablicy string�w
	 */
	private static String[] getLines(int count, BufferedReader br, Socket socket)
			throws IOException {
		String[] answer = new String[count];
		for (int i = 0; i < count; i++) {
			answer[i] = br.readLine();
			if (answer[i].equals(Protocol.ERROR))
				throw new IOException("server error");
		}
		socket.close();
		return answer;
	
	}

}
