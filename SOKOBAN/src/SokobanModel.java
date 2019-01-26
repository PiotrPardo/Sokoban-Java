package elementy;

/**
 * Klasa modeluj¹ca planszê z gr¹
 *
 */
public class SokobanModel {
	private ObiektyGry obiektyGry;
	private TworzeniePlanszy tworzeniePlanszy = new TworzeniePlanszy();
	
	private int currentLevel = 1;
	
    /**
     *odstêp miêdzy obiektami w poziomie
     */
	int spaceX;
    /**
     *odstêp miêdzy obiektami w pionie
     */
	int spaceY;
    /**
     * ilosc pikseli przesuniecia animacji w pozimie
     */
	int velX;
    /**
     * ilosc pikseli przesuniecia animacji w pionie
     */
	int velY;
	
	/**
	 * Konstruktor modelu
	 */
	public SokobanModel()
	{
	spaceX = config.gameWindowX/13;
	spaceY = config.gameWindowY/14;
	velX = spaceX/10;
	velY = spaceY/10;
	}

	/**
	 * Metoda zwracaj¹ca obecny level
	 *@return obecny level
	 */
	public int getCurrentLevel()
	{
		return currentLevel;
	}
	
	/**
	 * Metoda restartuj¹ca planszê na planszê o odpowiednim numerze
	 *@param level numer planszy która ma siê wyœwietliæ
	 */
    public void restartLevel(int level)
    {
    	if(config.online==true)
    	{
    	config.checkOnline();
    	}
    	obiektyGry = tworzeniePlanszy.setLevel(level,spaceX,spaceY);
    }
    
	/**
	 * Metoda uruchamiajaca kolejn¹ planszê. Numer poziomu zostaje zwiêkszony i zostaje wywo³ana metoda restart()
	 *
	 */
    public void startNextLevel()
    {
            currentLevel++;
           // obiektyGry.getAll().removeAll(null);
            restart();
    }
    
	/**
	 * Metoda ustawiajaca poziom jaki odpowiada atrybutowy currentLevel
	 *
	 */
    public void restart()
    {
    	restartLevel(currentLevel);
    }
    
	/**
	 * Metoda zwracaj¹ca obiekty planszy
	 *@return obiekty planszy
	 */
	public ObiektyGry getObiektyGry()
	{
		return obiektyGry;
	}
	
	/**
	 * Metoda poruszaj¹ca graczem. Sprawdzamy czy nie nastepuje kolizja gracza ze scian¹ czy pude³kiem i je¿eli nie to przemieszczamy gracza
	 * @param kierunek kierunek w którym przesuwamy obiekt
	 */
    public void move(Kierunek kierunek)
    {
        Player player = obiektyGry.getPlayer();

        if(checkWallCollision(player, kierunek))
            return;
        if(checkBoxCollision(kierunek))
            return;
        if(kierunek.equals(Kierunek.LEWO))
        {
            player.move(-velX,0);
        }
        else if(kierunek.equals(Kierunek.PRAWO))
        {
            player.move(velX,0);
        }
        else if(kierunek.equals(Kierunek.GORA))
        {
            player.move(0,-velY);
        }
        else if(kierunek.equals(Kierunek.DOL))
        {
            player.move(0,velY);
        }
        
        isCompleted();
    }
	
    
	/**
	 * Metoda sprawdzajaca czy obiekt nie jest w kolizji ze scian¹
	 *	@param obiektGry obiekt dla którego sprawdzamy kolizje
	 *	@param kierunek kierunek w którym sprawdzamy kolizje 
	 *@return
	 *false w przypadku braku kolizji
	 */
    public boolean checkWallCollision(ObiektyKolizyjne obiektGry, Kierunek kierunek)
    {
        for(Wall wall : obiektyGry.getWalls())
        {
            if(obiektGry.czyKolizja(wall,kierunek,spaceX,spaceY))
                return true;
        }
        return false;
    }
    
	/**
	 * Metoda sprawdzajaca czy gracz nie ma kolizji ze skrzynk¹, czy skrzynka nie jest w kolizji ze sciana a potem czy skrzynka  nie jest w kolizji z inn¹ skrzynk¹.
	 * Je¿eli gracz jest w kolzji ze skrzynk¹ ale skrzynka nie ma kolzji to przesuwamy skrzynkê
	 *	@param kierunek kierunek w którym sprawdzamy kolizje 
	 *	@return
	 *false w przypadku braku kolizji
	 */
    public boolean checkBoxCollision(Kierunek kierunek)
    {
    	Player player = obiektyGry.getPlayer();
        for(Box box : obiektyGry.getBoxes())
        {
        	if(player.czyKolizja(box, kierunek,spaceX,spaceY))
        	{
                    if(checkWallCollision(box,kierunek))
                        return true;

        	else
        	{
        		obiektyGry.getBoxes().remove(box);
                for(Box box1: obiektyGry.getBoxes())
                {
                    if(box.czyKolizja(box1,kierunek,spaceX,spaceY))
                    {
                        obiektyGry.getBoxes().add(box);
                        return true;
                    }
                }
                obiektyGry.getBoxes().add(box);
        	}
        	 if(kierunek.equals(Kierunek.GORA))
                 box.move(0,-velY);
             else if(kierunek.equals(Kierunek.DOL))
                 box.move(0,velY);
             else if(kierunek.equals(Kierunek.LEWO))
                 box.move(-velX,0);
             else  if(kierunek.equals(Kierunek.PRAWO))
                 box.move(velX,0);
             return false;
        }
        }
		return false;
    }

    
	/**
	 * Metoda sprawdzajaca czy wszystkie skrzynki znajduj¹ siê w bazach
	 *	@return
	 *	true w przypadku ukoñczenia poziomu
	 */
    
    public boolean isCompleted() {

        int num = 0;
        for(Home home : obiektyGry.getHomes())
        {
            for(Box box : obiektyGry.getBoxes())
            {
                if(box.getX() == home.getX() && box.getY() == home.getY())
                    num++;
            }
        }
        if(num == obiektyGry.getHomes().size())
        {
        	return true;
        }
		return false;
				
	
}
    

    
	/**
	 * Metoda zwracaj¹ca obiekt gracza
	 *	@return obiekt gracza
	 */
    public Player getPlayer()
    {
    	Player player = obiektyGry.getPlayer();
    	return player;
    	
    }
	/**
	 * Metoda zwracaj¹ca pozycjê poziom¹ gracza	
	 *	@return  pozycja pozioma
	 */
    public int getPlayerPositionX()
    {
    	int player = obiektyGry.getPlayer().getX();
		return player;
    }
    
	/**
	 * Metoda zwracaj¹ca pozycjê pionow¹ gracza
	 *	@return  pozycja pionowa
	 */
    public int getPlayerPositionY()
    {
    	int player = obiektyGry.getPlayer().getX();
		return player;
    }
    
    
	/**
	 * Metoda ustwiajaca nowe odstêpy miêszy obiektami w przypadku zmiany wielkosci okna
	 *	@param x szerokosæ okna
	 *	@param y wysokoœæ okna
	 */
    public void setSpace(int x, int y)
    {
    spaceX=x/13;
    spaceY=y/14;
    }
    
    
	/**
	 * Metoda ustwiajaca nowy krok animacji w przypadku zmieny wielkosci okna
	 *	@param x szerokosæ okna
	 *	@param y wysokoœæ okna
	 */
    public void setVel(int x, int y)
    {
    velX=x/config.gameWindowX;
    velY=y/config.gameWindowY;
    }
    
}
