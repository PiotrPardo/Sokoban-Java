package NewServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;

/**
 * Klasa odpowiadaj¹ca za komunikacje, numeru portu.
 */

public class Connection extends Thread{

	private ServerSocket _serverSocket;
	private int _port;
	private boolean _online;
	
	
	/**
	 * Metoda umo¿liwiaj¹ca wypisanie portu innej metodzie
	 *@param port numer portu
	 */
	public Connection(int port) {
		_port = port;
		_online = true;
		start();
	}
	
	/**
	 * Metoda wypisuj¹ca numer u¿ywanego portu
	 */
	public void run() {
		try {
			_serverSocket = new ServerSocket(_port);
			System.out.println("Serwer dzia³a na porcie" + " " + _port);
		} catch (IOException e) {
			System.out.println("Port" + " " + _port + " "
					+ "jest ju¿ u¿ywany");
			return;
		} catch (IllegalArgumentException e) {
			System.out.println("Niepoprawnie wpisany numer portu");
			return;
		}

		while (_online) {
			try {
				new Data(_serverSocket.accept());
			} catch (SocketException e) {
			} catch (IOException e) {
				System.out.println("B³¹d servera");
			}
		}
	}
}
