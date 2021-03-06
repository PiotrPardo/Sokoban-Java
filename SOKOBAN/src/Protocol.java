package elementy;


/**
 * Klasa przechowująca flagi protokołów
 */
public class Protocol {
	/**
	 * Flaga do sprwadzenia połączenia klient-serwer
	 */
	public static final String CHECK = "Avaliable";
	/**
	 * Flaga do wysyłania wyniku
	 */
	public static final String SENDINGSCORE = "Sending score";
	/**
	 * Flaga do pobrania najlepszego wyniku
	 */
	public static final String GETHIGHSCORES = "Get high scores";
	/**
	 * Flaga do pobierania mapy
	 */
    public static final String GETMAP = "Get map";
	/**
	 * Flaga error
	 */
	public static final String ERROR = "Error";
	/**
	 * Flaga do pobrania ilości map
	 */
	public static final String GETNUMBERMAPS = "Get number of maps";
}
