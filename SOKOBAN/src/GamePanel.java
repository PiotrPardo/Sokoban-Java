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
 * Klasa odpowiedzialna okno z gr�.
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
	 * Flaga do pozwolenia ruchu w g�re
	 */
    public boolean pozwoleniewgore = false;
	/**
	 * Flaga do pozwolenia ruchu w d�
	 */
    public boolean pozwoleniewdol = false;
	/**
	 * Flaga do pozwolenie og�lnego ruchu
	 */
    public boolean pozwolenie = false;
	SokobanModel sokobanModel;
	/**
	 * Czas
	 */
	int temp= 0;

	
	/**
	 * Konstruktor panelu z gr�
	 * dodanie nas�uchiwania na przyciski
	 *@param mainWindow g��wny panel gry
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
	 * Metoda rysuj�ca obikety na panelu
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
     * Metoda wy�wietlajaca na ekran
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
	 * Metoda wykonujaca akcj�
	 *
	 */
	public void actionPerformed(ActionEvent e) {
		
		updateAll();

	}
    
	
	 /**
     * Metoda przemieszczaj�ca, animuj�ca obiekty, reaguje na zdarzenie. Przy zdarzeniu zostaj� usawione odpowiednie flagi i na ich podstawie wykonuje si� opowiedni ruch
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
     * Metoda kt�ra reaguje na nacisni�cie przyciku R na klawiaturze Je�eli liczba zy� jest wi�ksza od zera zostaje zrestartowany poziom
     * w przeciwnym wypadku wy�wietlony zostaje komunikat o ko�cu gry i uruchomiona metoda koncz�ca gr�
     */
	public void Rbutton()
	{
		if(mainWindow.getLives()>0)
        {mainWindow.updateLives(); sokobanModel.restart();} else {JOptionPane.showMessageDialog(null, "Nie uda�o Ci si� uko�czy� wszystkich etap�w", "Koniec Gry", 1);
    	mainWindow.finished();};
		
	}
	
	
	 
	 
	 
	 /**
     * Metoda wywo�uj�ca si� po uko�czonym poziomie. Wywo�uje metod� aktualizuj�c� wynik i sprawdza czy s� kolejne poziomy, je�li tak wywo�uj� metod� 
     * uruchamiajac� now� plansz�cw przeciwnym wypadku uruchamia komunikat o uko�czonej grze i ko�czy gre
     */
    public void levelCompleted()
    {
		mainWindow.changeScore(points());
    	if(sokobanModel.getCurrentLevel()<config.numberOfLevels)
    		{
    		JOptionPane.showMessageDialog(null, String.format("Uko�czy�e� poziom %d", sokobanModel.getCurrentLevel()), "Gratulacje!", 1);
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
     * Metoda licz�ca ilos� zdobytych punkt�w po uko�czonym poziomie
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




