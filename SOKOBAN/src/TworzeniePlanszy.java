package elementy;

import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za utworzenie planszy
 * 
 */
public class TworzeniePlanszy {
	
	/**
	 * Konstruktor domy�lny
	 */
	public TworzeniePlanszy()
	{	
	}
	GamePanel gamePanel;
	

	/**
	 * Metoda tworz�ca plansze. Pobrana do metody zostaje zmienna z plansz� i ka�dy z element�w
	 * tej zmiennej staje si� obiektem o okre�lonym typie i pozycji
	 * @param numberOfLevel  poziom kt�ry ma zosta� utworzony
	 * @param spacex  odst�p mi�szy obiektami w poziomie
	 * @param spacey  odst�p mi�szy obiektami w pionie
	 * @return zwraca obiekty posiadaj�ce rozmiar i pozycj� na podstawie kt�rych zostanie narysowana na ekran plansza
	 */
	public ObiektyGry setLevel(int numberOfLevel, int spacex, int spacey)
	{
ArrayList<Wall> walls = new ArrayList<Wall>();
ArrayList<Box> boxes = new ArrayList<Box>();
ArrayList<Home> homes = new ArrayList<Home>();
Player player = null;;
//String level = config.PROPERTIES_FILE.getProperty("level" + Integer.toString(numberOfLevel));
String level = config.getLevel(numberOfLevel);
//int spacex=50;
//int spacey=50;
int x = spacex;
int y = spacey;

for (int i = 0; i < level.length(); i++) {


	char item = level.charAt(i);

    if (item == '1')
    {
        y += spacey;
        x = spacex;
    }     
    else if (item == '2') {
        walls.add(new Wall(x, y));
        x += spacex; 
    } else if (item == '3') {
        boxes.add(new Box(x, y));
        x += spacex;
    } else if (item == '4') {
        homes.add(new Home(x, y));
        x += spacex;        
    } else if (item == '5') {
    	player = new Player(x,y);
        x += spacex;
    } else if (item == '0') {
    	x += spacex;
    }
    

}


return new ObiektyGry(walls,boxes,homes,player);	
	}
	
}
