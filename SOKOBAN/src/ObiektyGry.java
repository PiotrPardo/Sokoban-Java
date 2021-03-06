package elementy;

import java.util.ArrayList;

/**
 * Klasa reprezentujaca wszystkie obiekty planszy
 * 
 */
public class ObiektyGry {

    /**
     * Lista obiektów "wall"
     */
	private ArrayList<Wall> walls;;
    /**
     * Lista obiektów "box"
     */
    private ArrayList<Box> boxes;;
    /**
     * Lista obiektów "home"
     */
    private ArrayList<Home> homes;;
    /**
     * Obiekt gracza
     */
    private Player player;;
	
    /**
     * Konstruktor klasy.
     * Obiekty określonego typu zostają przypisane do odpowiedniej array listy
     * @param walls obiekty Wall
     * @param boxes obiekty Box
     * @param homes obiekty Home
     * @param player obiekt Player
     */
    public ObiektyGry(ArrayList<Wall> walls, ArrayList<Box> boxes,
    				ArrayList<Home> homes,Player player )
    {
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;	
    }
    
    /**
     * Metoda zwracająca obiekty Wall
     * @return zwraca obiekty Wall
     */
    public ArrayList<Wall> getWalls()
    {
    	return walls;
    }
    /**
     * Metoda zwracająca obiekty Box
     * @return zwraca obiekty Box
     */
    public ArrayList<Box> getBoxes()
    {
    	return boxes;
    }
    /**
     * Metoda zwracająca obiekty Home
     * @return zwraca obiekty Home
     */
    public ArrayList<Home> getHomes()
    {
    	return homes;
    }
    /**
     * Metoda zwracająca Gracza
     * @return zwraca obiekt Gracza
     */
    public Player getPlayer()
    {
    	return player;
    } 
    /**
     * Metoda zwracająca wszystkie obiekty
     * @return zwraca wszystkie obiekty
     */
    public ArrayList<ObiektGry> getAll()
    {
    ArrayList<ObiektGry> world = new ArrayList<ObiektGry>();
    world.addAll(walls);
    world.addAll(homes);
    world.addAll(boxes);
    world.add(player);
	return world;
    }
    
}
