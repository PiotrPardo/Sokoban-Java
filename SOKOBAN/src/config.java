package elementy;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

/**
 * Klasa przechowuj�ca wszystkie sta�e wartosci u�ywane w programie. Wartosci pobierane s� z pliku konfiguracyjnego
 * @author Piotr Pardo ,Rados�aw Bu�a
 * @version 1
 */
public class config {
	/**
	 * swtorzenie obiektu klasy properties dla pliku konfiguracyjnego
	 */
	public static final Properties PROPERTIES_FILE = new Properties();
	
	//public static final Properties PROPERTIES_FILE = new Properties();
	/**
	 * Adres IP serwera
	 */
	public static String ip;
	/**
	 * Port serwera
	 */
	public static String port;
	/**
	 * Flaga czy gramy z serwerem
	 */
	public static boolean online;
	/**
	 * Czas co ile od�wie�a si� ekran
	 */
	public static int timer;
	/**
	 * licznik skrzynek do ustalania ilo�ci zdobytych punkt�w
	 */
	public static int boxcounter;
	/**
	 * licznik to ustalenia koncowych wynik�w
	 */
	public static int finalcounter;
	/**
	 * szeroko�� okna g��wnego
	 */
	public static int mainWindowX;
	/**
	 * wysoko�� okna g��wnego
	 */
	public static int mainWindowY;
	/**
	 * szeroko�� okna z gr�
	 */
	public static int gameWindowX;
	/**
	 * wysoko�� okna z gr�
	 */
	public static int gameWindowY;
	/**
	 * ilo�� "�y�"
	 */
	public static int restart;
	/**
	 * szeroko�� panelu bocznego
	 */
	public static int scoreWindowX;
	/**
	 * wysoko�� panelu bocznego
	 */
	public static int scoreWindowY;
	/**
	 * ilos� poziom�w w grze
	 */
	public static int numberOfLevels;

	/**
	 * Konstruktor klasy pobieraj�cy plik konfiguracyjny u ustawiaj�cy odpowiednie wartosci
	 */
	public config()
	{
	
		loadPropertiesFile("src/configer.properties");
		setOffline();
	}
	
	/**
	 * Metoda ustawiaj�ca parametrom warto�ci pobrane z pliku konfiguracyjnego
	 */
	private void setOffline() {
		timer = Integer.valueOf(PROPERTIES_FILE.getProperty("timer"));
		boxcounter = Integer.valueOf(PROPERTIES_FILE.getProperty("boxcounter"));
		finalcounter = Integer.valueOf(PROPERTIES_FILE.getProperty("finalcounter"));
		mainWindowX = Integer.valueOf(PROPERTIES_FILE.getProperty("mainWindowX"));
		mainWindowY = Integer.valueOf(PROPERTIES_FILE.getProperty("mainWindowY"));
		gameWindowX = Integer.valueOf(PROPERTIES_FILE.getProperty("gameWindowX"));
		gameWindowY = Integer.valueOf(PROPERTIES_FILE.getProperty("gameWindowY"));
		scoreWindowX = Integer.valueOf(PROPERTIES_FILE.getProperty("scoreWindowX"));
		scoreWindowY = Integer.valueOf(PROPERTIES_FILE.getProperty("scoreWindowY"));
		restart = Integer.valueOf(PROPERTIES_FILE.getProperty("restart"));
		ip = PROPERTIES_FILE.getProperty("ip");
		port = PROPERTIES_FILE.getProperty("port");
	}
	

	/**
	 * Metoda pobieraj�ca plik konfiguracyjny
	 * @param fileName nazwa pliku konfiguracyjnego
	 */
	 public void loadPropertiesFile(String fileName) {
	      //  InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
	       
	        try {
	        	 InputStream inputStream = new FileInputStream("config.properties");
	            PROPERTIES_FILE.load(inputStream);
				inputStream.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 
		/**
		 * Metoda pobieraj�ca ilo�� plansz z serwera albo z pliku konfiguracyjnego
		 */
	 public static void getNumber ()
	 {
		 if(online == false)
		 {
			 numberOfLevels = Integer.valueOf(PROPERTIES_FILE.getProperty("numberOfLevels"));
		 }
		 else
		 {
			 String temp[] = Client.getNumberMaps(ip, port);
			 numberOfLevels = Integer.valueOf(temp[0]);
		 }
		 
	 }		
	 
	/**
	* Metoda pobieraj�ca map� z serwera lub pliku konfiguracyjnego
	* @param level poziom kt�ry chcemy pobra�
	* @return zwraca plansze
	*/
	 public static String getLevel (int level)
	 {
		 if(online == false)
		 {
		 String _level = PROPERTIES_FILE.getProperty("level" + Integer.toString(level));
		 return _level;
		 }
		 else
		 {
			 String _level[] = Client.getMap(ip, port, level);
			 return _level[0];
		 }
		 
	 }
	 
		/**
		 * Metoda sprawdzaj�ca czy jest po��czenie z serwerem
		 */
	 public static void checkOnline()
	 {
		 
			if(Client.checkServer(config.ip, config.port) == null)
			{
		JOptionPane.showMessageDialog(null, "B��d po��czenia z serwerem", "Warning", JOptionPane.WARNING_MESSAGE);
		System.exit(0);
			}
		 
	 }	
	
}
