package elementy;


/**
 * Klasa przechowuj�ca flagi protoko��w
 */
public class Protocol {
	/**
	 * Flaga do sprwadzenia po��czenia klient-serwer
	 */
	public static final String CHECK = "Avaliable";
	/**
	 * Flaga do wysy�ania wyniku
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
	 * Flaga do pobrania ilo�ci map
	 */
	public static final String GETNUMBERMAPS = "Get number of maps";
}
