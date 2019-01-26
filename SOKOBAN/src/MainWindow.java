package elementy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * Klasa reprezentujaca g³ówn¹ ramkê gry. Tworzone s¹ w niej przyciski, akcje na nie i dodawane s¹ odpowienie ramki które póŸnej wyœwietlane s¹ na ekran 
 *  
 *  */
public class MainWindow extends JFrame{


	private static final long serialVersionUID = 1L;
	private ScorePanel scorePanel;
	private GamePanel gamePanel;
	private MainWindow mainWindow;
    /**
     * Przechowuje wynik gracza
     */
	private int SCORE;
    /**
     * Przechwuje numer poziou wyœwietlanego na ekranie
     */
	private int level = 1;
    /**
     * Przechwuje iloœæ restartów
     */
	private int restart;
    /**
     * Przechowuje nick gracza
     */
	private static String NAME;
    /**
     * Flaga startowa do wywo³ania dpowiedniego konstruktora
     */
	boolean start;
    /**
     * Panel
     */
	JPanel StartWindow;
    /**
     * Konstruktor z oknem startowym. Dodaje przyciski i ich akcje.
     *  
     *  @param start flaga do wywo³ania odpowiedniego konstruktora
     *  */
	public MainWindow(boolean start)
	{
		mainWindow=this;
		setSize(config.mainWindowX,config.mainWindowY);
		setTitle("Sokoban");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton newGame = new JButton("Nowa gra");
		JButton highScores = new JButton("Najlepsze Wyniki");
		JButton exit = new JButton("Wyjscie");

		StartWindow = new JPanel();
		StartWindow.setBackground(Color.RED);
		StartWindow.add(newGame);
		StartWindow.add(highScores);
		StartWindow.add(exit);
		add(StartWindow);
		setResizable(false);
		setVisible(true);
		
		ActionListener startGameEvent = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				TextField text = new TextField();
				String name = JOptionPane.showInputDialog(text, "Podaj Nick", "Nowa Gra", JOptionPane.PLAIN_MESSAGE);
				setName(name);
				if((name!=null)&&(name.length() > 0))
				{
				mainWindow.dispose();
				Dimension dm = mainWindow.getToolkit().getScreenSize();
				mainWindow = new MainWindow();
				mainWindow.setVisible(true);
				mainWindow.setLocation(
						(int) (dm.getWidth() / 2 - mainWindow.getWidth() / 2),
						(int) (dm.getHeight() / 2 - mainWindow.getHeight() / 2));
				}
			}
		};
		
		ActionListener exitGameEvent = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int odpowiedz = JOptionPane.showConfirmDialog(null, "Czy jesteœ pewny ,¿e chcesz wyjœæ?","Pytanie" ,JOptionPane.YES_NO_OPTION);
				if(odpowiedz==JOptionPane.YES_OPTION)
				System.exit(0);
			}
		};
		ActionListener scoreGameEvent = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				showScore();
			}
		};
		
		
		
		
		exit.addActionListener(exitGameEvent);
		newGame.addActionListener(startGameEvent);	
		highScores.addActionListener(scoreGameEvent);
	}
	
	
    /**
     * Konstruktor z oknem gry. Dodaje przyciski i ich akcje.
     * Do ramki zostaje dodany panel boczny i panel z gr¹
     *
     *  */
	public MainWindow()
	{
		
        //*******************************************************
        setResizable(false);
        
		mainWindow=this;
		setTitle("Sokoban");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		restart = config.restart;
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu game = new JMenu("GRA");

		
		menuBar.add(game);

		JMenuItem newGame = new JMenuItem("Nowa gra");
		JMenuItem highScores = new JMenuItem("Najlepsze Wyniki");
		JMenuItem exit = new JMenuItem("Wyjscie");
		
		game.add(newGame);
		game.add(highScores);
		game.addSeparator();
		game.add(exit);

		gamePanel = new GamePanel(this);
		gamePanel.setPreferredSize(new Dimension(config.gameWindowX, config.gameWindowY));
		scorePanel = new ScorePanel(level,restart);
		scorePanel.setPreferredSize(new Dimension(config.scoreWindowX, config.scoreWindowY));
		
		add(gamePanel, BorderLayout.CENTER);
		add(scorePanel, BorderLayout.WEST);
		
		
		pack();

		
		ActionListener startGameEvent = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int odpowiedz = JOptionPane.showConfirmDialog(null, "Czy jesteœ pewny ,¿e chcesz rozpocz¹c now¹ grê?"," " ,JOptionPane.YES_NO_OPTION);
				if(odpowiedz==JOptionPane.YES_OPTION)
				{
				TextField text = new TextField();
				String name = JOptionPane.showInputDialog(text, "Podaj Nick", "Nowa Gra", JOptionPane.PLAIN_MESSAGE);
				if((name!=null)&&(name.length() > 0))
				{
				mainWindow.dispose();
				Dimension dm = mainWindow.getToolkit().getScreenSize();
				mainWindow = new MainWindow();
				mainWindow.setVisible(true);
				mainWindow.setLocation(
						(int) (dm.getWidth() / 2 - mainWindow.getWidth() / 2),
						(int) (dm.getHeight() / 2 - mainWindow.getHeight() / 2));
				}
				}
			}
		};
		
	
		ActionListener highScoresEvent = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				showScore();
			}
		};
		
		ActionListener exitEvent = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int odpowiedz = JOptionPane.showConfirmDialog(null, "Czy jesteœ pewny ,¿e chcesz wyjœæ?","Pytanie" ,JOptionPane.YES_NO_OPTION);
				if(odpowiedz==JOptionPane.YES_OPTION)
				System.exit(0);
			}
		};
		
		highScores.addActionListener(highScoresEvent);
		newGame.addActionListener(startGameEvent);
		exit.addActionListener(exitEvent);
		
	}
	
    /**
     * Metoda wyœwietlaj¹ca listê najleszych wyników
     *  
     *  */
	private void showScore() {
		if(config.online)
		{
		config.checkOnline();
		String scores[] = Client.getHighScores(config.ip, config.port);
		String[] scoress = new String[10];
		for (int i = 0; i < 10; i++)
			scoress[i]= (i+1) + " - " + scores[i*2]+" - "+scores[i*2+1];

		JOptionPane.showMessageDialog(mainWindow,scoress,"Najlepsze Wyniki", JOptionPane.INFORMATION_MESSAGE);
	
	}
		else
		{
			JOptionPane.showMessageDialog(mainWindow,"Najlepsze wyniki tylko przy grze online ","Najlepsze Wyniki", JOptionPane.INFORMATION_MESSAGE);
		}
		
		
	}
	
	
    /**
     * Wywo³anie tej metody powoduje zamkniêcie okna z gr¹ i powrót do okna startowego
     *  
     *  */
	public void finished()
	{
		saveScore();
		mainWindow.dispose();
		Dimension dm = mainWindow.getToolkit().getScreenSize();
		mainWindow = new MainWindow(true);
		mainWindow.setVisible(true);
		mainWindow.setLocation(
				(int) (dm.getWidth() / 2 - mainWindow.getWidth() / 2),
				(int) (dm.getHeight() / 2 - mainWindow.getHeight() / 2));
		
	}

	
    /**
     * Metoda zapisujaca nick Gracz i jego wynik do najlepszych wyników
     *  
     *  */
	public void saveScore()
	{
		if(config.online)
		{
			Client.sendScore(NAME, SCORE);
		}		
	}
	
	
    /**
     * Metoda ustawiajaca nazwê graczowi
     *  
     *  */
	public void setName(String name)
	{
		NAME = name;
	}
	
	
    /**
     * Metoda aktualizuj¹ca panel z gr¹
     *  */
    public void update()
    {
    	gamePanel.repaint();
		revalidate();
    }
    
    
    /**
     * Metoda aktualizuj¹ca wynik gracza 
     * @param newScore o tyle zaktualizuje sie wynik
     * */
    void changeScore(int newScore)
    {
    	SCORE = SCORE + newScore;
    	updateScore();
    }
    
    
    /**
     * Metoda wyœwietlaj¹ca aktualny wynik
     */
    public void updateScore()
    {
    	scorePanel.setScore(SCORE);;
    }
    
    
    /**
     * Metoda wyœwietlaj¹ca aktualny numer planszy
     * 
     * @param level numer poziomu do ustawienia
     */
    public void updateLevel(int level)
    {
    	scorePanel.setLevel(level);;
    }
    
    
    /**
     * Metoda aktualizuj¹ca ilosæ ¿yæ i ustawiaj¹ca aktualn¹ jej wartoœæ na penelu bocznym
     */
    public void updateLives()
    {
    	restart--;
    	scorePanel.setLives(restart);
    }
    
    /**
     * Metoda zwracaj¹ca iloœc ¿yæ
     * @return iloœæ ¿yæ
     */
    public int getLives()
    {
		return restart;
    }
     
}

