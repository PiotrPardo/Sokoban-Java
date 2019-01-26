package elementy;

/**
 * Klasa roszerzaj�ca klas� ObiektGry dla obiekt�w kolizyjnych
 * 
 */

public class ObiektyKolizyjne extends ObiektGry{
	
	/**
	 * Konstruktor obiekt�w kolizyjnych
	 * @param x pozycja pozioma
	 * @param y pozycja pionowa
	 */
	public ObiektyKolizyjne(int x, int y) {
		super(x,y);
	}
	
	/**
	 * Metoda sprawdzaj�ca czy nie wyst�puje kolizja obiektu z obiektem na rzecz kt�rego wywo�ujemy metod�
	 * @param obiektyGry obiekt z kt�rym sprawdzamy kolizja
	 * @param kierunek kierunek w ktorym sprawdzay kolizje
	 * @param spaceX odst�p mi�dzy obiektami w poziomie
	 * @param spaceY odst�p mi�dzy obiektami w pionie
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

	
