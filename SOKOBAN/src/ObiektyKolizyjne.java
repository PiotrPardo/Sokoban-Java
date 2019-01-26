package elementy;

/**
 * Klasa roszerzaj¹ca klasê ObiektGry dla obiektów kolizyjnych
 * 
 */

public class ObiektyKolizyjne extends ObiektGry{
	
	/**
	 * Konstruktor obiektów kolizyjnych
	 * @param x pozycja pozioma
	 * @param y pozycja pionowa
	 */
	public ObiektyKolizyjne(int x, int y) {
		super(x,y);
	}
	
	/**
	 * Metoda sprawdzaj¹ca czy nie wystêpuje kolizja obiektu z obiektem na rzecz którego wywo³ujemy metodê
	 * @param obiektyGry obiekt z którym sprawdzamy kolizja
	 * @param kierunek kierunek w ktorym sprawdzay kolizje
	 * @param spaceX odstêp miêdzy obiektami w poziomie
	 * @param spaceY odstêp miêdzy obiektami w pionie
	 * @return zwraca false w przybadku braku kolizji
	 */
	public boolean czyKolizja(ObiektGry obiektyGry, Kierunek kierunek, int spaceX, int spaceY)
	{
		if(kierunek == Kierunek.GORA)
		{
			 if (((this.getY() - spaceY) == obiektyGry.getY()) &&
			            (this.getX() == obiektyGry.getX())) {
			            return true;
			        } else {
			            return false;
			        }
		}
		if(kierunek == Kierunek.DOL)
		{
			 if (((this.getY() + spaceY) == obiektyGry.getY()) &&
			            (this.getX() == obiektyGry.getX())) {
			            return true;
			        } else {
			            return false;
			        }
		}
		if(kierunek == Kierunek.LEWO)
		{
			 if (((this.getX() - spaceX) == obiektyGry.getX()) &&
			            (this.getY() == obiektyGry.getY())) {
			            return true;
			        } else {
			            return false;
			        }
		}
		if(kierunek == Kierunek.PRAWO)
		{
			 if (((this.getX() + spaceX) == obiektyGry.getX()) &&
			            (this.getY() == obiektyGry.getY())) {
			            return true;
			        } else {
			            return false;
			        }
		}
		
		
		return false;
}



}

	
