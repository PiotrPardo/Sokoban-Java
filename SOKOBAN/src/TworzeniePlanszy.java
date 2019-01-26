package elementy;

import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za utworzenie planszy
 * 
 */
public class TworzeniePlanszy {
	
	/**
	 * Konstruktor domyœlny
	 */
	public TworzeniePlanszy()
	{	
	}
	GamePanel gamePanel;
	

	/**
	 * Metoda tworz¹ca plansze. Pobrana do metody zostaje zmienna z plansz¹ i ka¿dy z elementów
	 * tej zmiennej staje siê obiektem o okreœlonym typie i pozycji
	 * @param numberOfLevel  poziom który ma zostaæ utworzony
	 * @param spacex  odstêp miêszy obiektami w poziomie
	 * @param spacey  odstêp miêszy obiektami w pionie
	 * @return zwraca obiekty posiadaj¹ce rozmiar i pozycjê na podstawie których zostanie narysowana na ekran plansza
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
