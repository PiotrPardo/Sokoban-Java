package elementy;

import java.util.ArrayList;

/**
 * Klasa reprezentujaca wszystkie obiekty planszy
 * 
 */
public class ObiektyGry {

    /**
     * Lista obiekt�w "wall"
     */
	private ArrayList<Wall> walls;;
    /**
     * Lista obiekt�w "box"
     */
    private ArrayList<Box> boxes;;
    /**
     * Lista obiekt�w "home"
     */
    private ArrayList<Home> homes;;
    /**
     * Obiekt gracza
     */
    private Player player;;
	
    /**
     * Konstruktor klasy.
     * Obiekty okre�lonego typu zostaj� przypisane do odpowiedniej array listy
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
     * Metoda zwracaj�ca obiekty Wall
     * @return zwraca obiekty Wall
     */
    public ArrayList<Wall> getWalls()
    {
    	return walls;
    }
    /**
     * Metoda zwracaj�ca obiekty Box
     * @return zwraca obiekty Box
     */
    public ArrayList<Box> getBoxes()
    {
    	return boxes;
    }
    /**
     * Metoda zwracaj�ca obiekty Home
     * @return zwraca obiekty Home
     */
    public ArrayList<Home> getHomes()
    {
    	return homes;
    }
    /**
     * Metoda zwracaj�ca Gracza
     * @return zwraca obiekt Gracza
     */
    public Player getPlayer()
    {
    	return player;
    } 
    /**
     * Metoda zwracaj�ca wszystkie obiekty
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
