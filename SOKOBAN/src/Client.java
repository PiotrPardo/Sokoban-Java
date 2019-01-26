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
 * Klasa s³u¿¹ca do komunikacji z serwerem
 */
public class Client {
	/**
	 * Numer poziomu który ma zostaæ wczytany
	*/
	static int level = 1;
	
	/**
	 * Metoda sprawdzaj¹ca po³¹czenie z serwerem
	 * @param ip numer ip serwera
	 * @param port port na którym mo¿emy po³¹czyæ siê z serwerem
	 * @return answer odpowiedŸ serwera
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
	 * Metoda pobieraj¹ca liczbê map na serwerze
	 * @param ip numer ip serwera
	 * @param port port na którym mo¿emy po³¹czyæ siê z serwerem
	 * @return answer odebrana od serwera wartoœæ oznaczaj¹ca liczbê map na serwerze
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
	 * Metoda pobieraj¹ca liste najlepszych wyników z serwera
	 * @param ip numer ip serwera
	 * @param port port na którym mo¿emy po³¹czyæ siê z serwerem
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
	 * Metoda pobieraj¹ca mapê z serwera
	 * @param ip numer ip serwera
	 * @param port port na którym mo¿emy po³¹czyæ siê z serwerem
	 * @param Level numer mapy która ma zostaæ pobrana, zmienia wartoœæ statycznej 'level'
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
	 * Metoda pwysy³aj¹ca wynki uzyskany przez u¿ytkownika do serwera
	 * @param name nick u¿ytkownika
	 * @param score wynik u¿ytkownika
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
	 * Metoda odpowiadaj¹ca za po³¹cznie z serwerem
	 * @param command zmienna do wyboru odpowiedniego protoko³u
	 * @param ip numer ip serwera
	 * @param port port na którym mo¿emy po³¹czyæ siê z serwerem
	 * @return zwraca wartoœæ o któr¹ wys³aliœmy ¿¹danie
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
		 * Protokó³ sprwadzj¹cy po³¹czenie klient-serwer
		 * @return getLines odpowiedŸ serwera zapisana w tablicy stringów
		 */
		case Protocol.CHECK: {
			return getLines(1, br, socket);
		}
		/**
		 * Protokó³ pobieraj¹cy liczbê map na serwerze
		 * @return getLines odebrana od serwera wartoœæ oznaczaj¹ca liczbê map na serwerze zapisana w tablicy stringów
		 */
		case Protocol.GETNUMBERMAPS: {
			return getLines(1, br, socket);
		}
		/**
		 * Protokó³ pobieraj¹cy najlepsze wyniki
		 * @return getLines odebrane od serwera najlepsze wyniki zapisane w tablicy stringów
		 */
		case Protocol.GETHIGHSCORES: {
			return getLines(20, br, socket);
		}
		/**
		 * Protokó³ pobieraj¹cy mapê
		 * @return getLines odebrana od serwera mapa zapisana w tablicy stringów
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
	 * Metoda odczytuj¹ca zmienn¹ anwser i zapisuj¹ca informacje do tablicy stringów
	 * @param count zmienna okreœlaj¹ca wielkosæ tablicy
	 * @param br zmienna do odczytania z bufora
	 * @param socket zmienna ³¹cz¹ca siê z odpowiednim socketem
	 * @return getLines zwrócona wiadomoœc zapisana w tablicy stringów
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
