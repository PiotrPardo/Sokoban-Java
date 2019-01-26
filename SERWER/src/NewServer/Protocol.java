package NewServer;

/**
 * Klasa przechowuj¹ca protoko³y
 */
public class Protocol {
	/**
	 * Protokó³ sprwadzj¹cy po³¹czenie klient-serwer
	 */
	public static final String CHECK = "Avaliable";
	/**
	 * Protokó³ wysy³aj¹cy najlepszy wynik
	 */
	public static final String SENDINGSCORE = "Sending score";
	/**
	 * Protokó³ pobieraj¹cy najlepsze wyniki
	 */
	public static final String GETHIGHSCORES = "Get high scores";
	/**
	 * Protokó³ pobieraj¹cy mapê
	 */
	public static final String GETMAP = "Get map";
	/**
	 * Protokó³ informuj¹cy o b³êdzie
	 */
	public static final String ERROR = "Error";
	/**
	 * Protokó³ pobieraj¹cy liczbê map na serwerze
	 */
	public static final String GETNUMBERMAPS = "Get number of maps";
}