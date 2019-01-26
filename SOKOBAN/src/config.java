package elementy;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

/**
 * Klasa przechowuj¹ca wszystkie sta³e wartosci u¿ywane w programie. Wartosci pobierane s¹ z pliku konfiguracyjnego
 * @author Piotr Pardo ,Rados³aw Bu³a
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
	 * Czas co ile odœwie¿a siê ekran
	 */
	public static int timer;
	/**
	 * licznik skrzynek do ustalania iloœci zdobytych punktów
	 */
	public static int boxcounter;
	/**
	 * licznik to ustalenia koncowych wyników
	 */
	public static int finalcounter;
	/**
	 * szerokoœæ okna g³ównego
	 */
	public static int mainWindowX;
	/**
	 * wysokoœæ okna g³ównego
	 */
	public static int mainWindowY;
	/**
	 * szerokoœæ okna z gr¹
	 */
	public static int gameWindowX;
	/**
	 * wysokoœæ okna z gr¹
	 */
	public static int gameWindowY;
	/**
	 * iloœæ "¿yæ"
	 */
	public static int restart;
	/**
	 * szerokoœæ panelu bocznego
	 */
	public static int scoreWindowX;
	/**
	 * wysokoœæ panelu bocznego
	 */
	public static int scoreWindowY;
	/**
	 * ilosæ poziomów w grze
	 */
	public static int numberOfLevels;

	/**
	 * Konstruktor klasy pobieraj¹cy plik konfiguracyjny u ustawiaj¹cy odpowiednie wartosci
	 */
	public config()
	{
	
		loadPropertiesFile("src/configer.properties");
		setOffline();
	}
	
	/**
	 * Metoda ustawiaj¹ca parametrom wartoœci pobrane z pliku konfiguracyjnego
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
	 * Metoda pobieraj¹ca plik konfiguracyjny
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
		 * Metoda pobieraj¹ca iloœæ plansz z serwera albo z pliku konfiguracyjnego
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
	* Metoda pobieraj¹ca mapê z serwera lub pliku konfiguracyjnego
	* @param level poziom który chcemy pobraæ
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
		 * Metoda sprawdzaj¹ca czy jest po³¹czenie z serwerem
		 */
	 public static void checkOnline()
	 {
		 
			if(Client.checkServer(config.ip, config.port) == null)
			{
		JOptionPane.showMessageDialog(null, "B³¹d po³¹czenia z serwerem", "Warning", JOptionPane.WARNING_MESSAGE);
		System.exit(0);
			}
		 
	 }	
	
}
