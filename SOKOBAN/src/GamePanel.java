package elementy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;



/**
 * Klasa odpowiedzialna okno z gr¹.
 *
 */

public class GamePanel extends JPanel implements ActionListener, KeyListener
{
	/**
	 * Czas
	 */
	Timer tm = new Timer(config.timer, this);
	private static final long serialVersionUID = 1L;

	private MainWindow mainWindow;
	/**
	 * Flaga do pozwolenia ruchu w lewo
	 */
    public boolean pozwoleniewlewo = false;
	/**
	 * Flaga do pozwolenia ruchu w prawo
	 */
    public boolean pozwoleniewprawo = false;
	/**
	 * Flaga do pozwolenia ruchu w góre
	 */
    public boolean pozwoleniewgore = false;
	/**
	 * Flaga do pozwolenia ruchu w dó³
	 */
    public boolean pozwoleniewdol = false;
	/**
	 * Flaga do pozwolenie ogólnego ruchu
	 */
    public boolean pozwolenie = false;
	SokobanModel sokobanModel;
	/**
	 * Czas
	 */
	int temp= 0;

	
	/**
	 * Konstruktor panelu z gr¹
	 * dodanie nas³uchiwania na przyciski
	 *@param mainWindow g³ówny panel gry
	 */
	public GamePanel(MainWindow mainWindow)
	{ 
	
		this.mainWindow = mainWindow;
		this.getHeight();
		this.getWidth();
	 	addKeyListener(this);
        this.sokobanModel = new SokobanModel();
        sokobanModel.restart();
        setVisible(true);
        setFocusable(true);

	}
	
	/**
	 * Metoda rysuj¹ca obikety na panelu
	 *@param g 
	 */
    public void Rysuj(Graphics g)
    {

		sokobanModel.setSpace(this.getWidth(), this.getHeight());
		//sokobanModel.setVel(this.getWidth(), this.getHeight());
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        ObiektyGry obiektyGry = sokobanModel.getObiektyGry();
        
        ArrayList<ObiektGry> world = obiektyGry.getAll();

        for (int i = 0; i < world.size(); i++) {
        	
            ObiektGry item = (ObiektGry) world.get(i);
           // g2d.drawImage(item.getImage(), sokobanModel.spaceX*item.getX(), sokobanModel.spaceY*item.getY(), this.getWidth()/13, this.getHeight()/14, null);
            g2d.drawImage(item.getImage(), item.getX(), item.getY(), this.getWidth()/13, this.getHeight()/14, null);
    }    }
    

    /**
     * Metoda wyœwietlajaca na ekran
     *@param g 
     */
	public void paintComponent(Graphics g) {
       // super.paintComponent(g);
        Rysuj(g);
        repaint();
	}



	@Override
	/**
	 * Metoda reagujaca na przyciski 
	 *
	 */
	public void keyPressed(KeyEvent e) {
	        if(!pozwolenie)
	        {
	 		   tm.start();
	            switch (e.getKeyCode())
	            {
	   
	                case KeyEvent.VK_LEFT: pozwolenie = true; pozwoleniewlewo = true; temp=0;

	                    break;
	                case KeyEvent.VK_RIGHT: pozwolenie = true; pozwoleniewprawo = true; temp=0;

	                    break;
	                case KeyEvent.VK_UP: pozwolenie = true; pozwoleniewgore = true; temp=0;

	                    break;
	                case KeyEvent.VK_DOWN: pozwolenie = true; pozwoleniewdol = true; temp=0;

	                    break;
	                case KeyEvent.VK_R: Rbutton();
	                    break;
	            }
	        }
	}

    
	@Override
	/**
	 * Metoda wykonujaca akcjê
	 *
	 */
	public void actionPerformed(ActionEvent e) {
		
		updateAll();

	}
    
	
	 /**
     * Metoda przemieszczaj¹ca, animuj¹ca obiekty, reaguje na zdarzenie. Przy zdarzeniu zostaj¹ usawione odpowiednie flagi i na ich podstawie wykonuje siê opowiedni ruch
     * 
     */
	public void updateAll()
	{
		if(pozwolenie&&pozwoleniewlewo)
		{	
			if(temp >= sokobanModel.spaceX/sokobanModel.velX)
			{
				pozwolenie = false;
				pozwoleniewlewo = false;
				tm.stop();
				return;
			}
			sokobanModel.move(Kierunek.LEWO);
			temp++;
		}
		
		if(pozwolenie&&pozwoleniewprawo)
		{	
			if(temp >= sokobanModel.spaceX/sokobanModel.velX)
			{
				pozwolenie = false;
				pozwoleniewprawo = false;
				tm.stop();
				return;
			}
			sokobanModel.move(Kierunek.PRAWO);
			temp++;
		}
		
		if(pozwolenie&&pozwoleniewgore)
		{	
			if(temp >= sokobanModel.spaceY/sokobanModel.velY)
			{
				pozwolenie = false;
				pozwoleniewgore = false;
				tm.stop();
				return;
			}
			sokobanModel.move(Kierunek.GORA);
			temp++;
		}
		
		if(pozwolenie&&pozwoleniewdol)
		{	
			if(temp >= sokobanModel.spaceY/sokobanModel.velY)
			{
				pozwolenie = false;
				pozwoleniewdol = false;
				tm.stop();
				return;
			}
			sokobanModel.move(Kierunek.DOL);
			temp++;
		}
		
		if(sokobanModel.isCompleted())
		{
			levelCompleted();
		}
mainWindow.update();
	}
	
	
	
	 /**
     * Metoda która reaguje na nacisniêcie przyciku R na klawiaturze Je¿eli liczba zyæ jest wiêksza od zera zostaje zrestartowany poziom
     * w przeciwnym wypadku wyœwietlony zostaje komunikat o koñcu gry i uruchomiona metoda koncz¹ca grê
     */
	public void Rbutton()
	{
		if(mainWindow.getLives()>0)
        {mainWindow.updateLives(); sokobanModel.restart();} else {JOptionPane.showMessageDialog(null, "Nie uda³o Ci siê ukoñczyæ wszystkich etapów", "Koniec Gry", 1);
    	mainWindow.finished();};
		
	}
	
	
	 
	 
	 
	 /**
     * Metoda wywo³uj¹ca siê po ukoñczonym poziomie. Wywo³uje metodê aktualizuj¹c¹ wynik i sprawdza czy s¹ kolejne poziomy, jeœli tak wywo³ujê metodê 
     * uruchamiajac¹ now¹ planszêcw przeciwnym wypadku uruchamia komunikat o ukoñczonej grze i koñczy gre
     */
    public void levelCompleted()
    {
		mainWindow.changeScore(points());
    	if(sokobanModel.getCurrentLevel()<config.numberOfLevels)
    		{
    		JOptionPane.showMessageDialog(null, String.format("Ukoñczy³eœ poziom %d", sokobanModel.getCurrentLevel()), "Gratulacje!", 1);
    		sokobanModel.startNextLevel();
    		mainWindow.updateLevel(sokobanModel.getCurrentLevel());

    		}
    	else
    	{
    		mainWindow.changeScore(mainWindow.getLives()*config.finalcounter);
    	JOptionPane.showMessageDialog(null, "Koniec gry!", "Gratulacje!", 1);
    	mainWindow.finished();
    	}
    }

    
    /**
     * Metoda licz¹ca ilosæ zdobytych punktów po ukoñczonym poziomie
     *@return zwraca zdobyte punkty
     */
    public int points()
    {
    	int score = config.boxcounter*sokobanModel.getObiektyGry().getBoxes().size();  	
		return score;
    }
    
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}




