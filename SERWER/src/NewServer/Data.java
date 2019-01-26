package NewServer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Klasa zawierajaca metody s³u¿¹ce do komunikacji z klientem
 */

public class Data extends Thread {

	private Socket socket;
	private BufferedReader BuffRead;
	private PrintWriter writer;
	
	/**
	 * Konstruktor zawieraj¹cy strumienie pobieraj¹ce i wypisuj¹ce
	 */
	Data(Socket Socket) throws IOException {

		socket = Socket;
		InputStream input = socket.getInputStream();
		BuffRead = new BufferedReader(new InputStreamReader(input));
		OutputStream output = socket.getOutputStream();
		writer = new PrintWriter(output, true);
		start();
	}
	
	/**
	 * Metoda odpowiadaj¹ca za komunikacje z klientem w zale¿noœci od komend wys³anych od klienta.
	 * Metoda ta umo¿liwia: sprawdzenie czy klient jest aktywny, wys³anie i pobranie najlepszych wyników oraz wysy³anie plansz.   
	 */
	public void run() {

		try {
			
			String command = BuffRead.readLine();
			System.out.println("Od" + " "
					+ socket.getInetAddress().getHostAddress() + ": "
					+ command);

			switch (command) {

			case Protocol.CHECK: {
				writer.println("Server avaliable");
				System.out.println("Do" + " "
						+ socket.getInetAddress().getHostAddress() + ": "
						+ "Server available");
				socket.close();
				break; 
				
				
				
				
			} 
			
			case Protocol.SENDINGSCORE: {
				System.out.println("Oczekuje na wynik");
				try {
					socket.setSoTimeout(3000);
					String name = BuffRead.readLine();
					System.out.println("Od" + " "
							+ socket.getInetAddress().getHostAddress() + ": "
							+ name);
					String score = BuffRead.readLine();
					System.out.println("Od" + " "
							+ socket.getInetAddress().getHostAddress() + ": "
							+ score);
					socket.setSoTimeout(0);
					SokobanConfig.saveScore(name, score);
				} catch (SocketTimeoutException e) {
					socket.setSoTimeout(0);
					System.out.println("Zbyt d³ugi czas oczekiwania");
				}
				socket.close();
				break;
			}

			case Protocol.GETHIGHSCORES: {
				String[] answer = SokobanConfig.getHighScores();
				if (answer == null) {
					writer.println(Protocol.ERROR);
					System.out.println("Nie mo¿na odczytaæ najlepszych wyników z pliku");
					System.out.println("Do" + " "
							+ socket.getInetAddress().getHostAddress() + ": "
							+ Protocol.ERROR);
					socket.close();
					break;
				}
				
				System.out.println("Wysy³anie najlepszych wyników");
				for (int i = 0; i < 20; i++) {
					writer.println(answer[i]);
					System.out.println("Do" + " "
							+ socket.getInetAddress().getHostAddress() + ": "
							+ answer[i]);
				}
				socket.close();
				break;
			}
			
			
			
			case Protocol.GETNUMBERMAPS: {
				String[] answer = SokobanConfig.getNumberMaps();
				if (answer == null) {
					writer.println(Protocol.ERROR);
					System.out.println("Nie mo¿na odczytaæ listy plansz z serwera");
					System.out.println("Do" + " "
							+ socket.getInetAddress().getHostAddress() + ": "
							+ Protocol.ERROR);
					socket.close();
					break;
				}
				System.out.println("Liczba wszystkich plansz na serwerze");
				for (int i = 0; i < 1; i++) {
					writer.println(answer[i]);
					System.out.println("Do" + " "
							+ socket.getInetAddress().getHostAddress() + ": "
							+ answer[i]);
				}
				socket.close();
				break;
			}
			
			case Protocol.GETMAP: {
				String[] answer = null;
				System.out.println("Oczekuje na numer poziomu");
				try {
					socket.setSoTimeout(3000);
					String number = BuffRead.readLine();
					System.out.println("Od" + " "
							+ socket.getInetAddress().getHostAddress() + ": "
							+ number);
					socket.setSoTimeout(0);
					answer = SokobanConfig.getMap(number);
				} catch (SocketTimeoutException e) {
					socket.setSoTimeout(0);
					System.out.println("Zbyt d³ugi czas oczekiwania");
				}
				if (answer == null) {
					writer.println(Protocol.ERROR);
					System.out.println("Do" + " "
							+ socket.getInetAddress().getHostAddress() + ": "
							+ Protocol.ERROR);
					socket.close();
					break;
				}
				System.out.println("Wysy³anie mapy");
				for (int i = 0; i < 1; i++) {
					writer.println(answer[i]);
					System.out.println("Do" + " "
							+ socket.getInetAddress().getHostAddress() + ": "
							+ answer[i]);
				}
				socket.close();
				break;
			}

			default:
				writer.println(Protocol.ERROR);
				System.out.println("Niepoprawna komenda");
				System.out.println("Do" + " "
						+ socket.getInetAddress().getHostAddress() + ": "
						+ Protocol.ERROR);
				socket.close();
				break;
			}
			socket.close();

		} catch (IOException e) {
			System.out.println("B³¹d");
		}
	}
}