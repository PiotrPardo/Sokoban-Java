package NewServer;

/**
 * Klasa przechowuj�ca protoko�y
 */
public class Protocol {
	/**
	 * Protok� sprwadzj�cy po��czenie klient-serwer
	 */
	public static final String CHECK = "Avaliable";
	/**
	 * Protok� wysy�aj�cy najlepszy wynik
	 */
	public static final String SENDINGSCORE = "Sending score";
	/**
	 * Protok� pobieraj�cy najlepsze wyniki
	 */
	public static final String GETHIGHSCORES = "Get high scores";
	/**
	 * Protok� pobieraj�cy map�
	 */
	public static final String GETMAP = "Get map";
	/**
	 * Protok� informuj�cy o b��dzie
	 */
	public static final String ERROR = "Error";
	/**
	 * Protok� pobieraj�cy liczb� map na serwerze
	 */
	public static final String GETNUMBERMAPS = "Get number of maps";
}