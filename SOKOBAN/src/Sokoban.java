package elementy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
* G��wna klasa gry
 */

public class Sokoban extends JFrame{
	MainWindow mainWindow;

	private static final long serialVersionUID = 1L;


    /**
     *Konstruktor g��wnej klasy. Tworzone jest startowe okno programu w k�rym wybieramy gre online/offline ustawiaja� odpowiedni� flag�
     */
		public Sokoban(){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setUndecorated(true);
			setResizable(false);
			
	    	JPanel preview = new JPanel();
	    	
			JButton online = new JButton("Gra online");
			JButton offline = new JButton("Gra offline");
			JButton exit = new JButton("Wyjscie");
			preview.add(online);
			preview.add(offline);
			preview.add(exit);
			preview.setBorder(BorderFactory.createLineBorder(Color.CYAN, 1));
			preview.setVisible(true);
			add(preview);

			ActionListener startOffline = new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					config.online = false;
					config.getNumber();
					Start();
					}
				};
				
				
				ActionListener startOnline = new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						
						
						if(Client.checkServer(config.ip, config.port) == null)
								{
							JOptionPane.showMessageDialog(null, "B��d po��czenia z serwerem", "Warning", JOptionPane.WARNING_MESSAGE);
							System.exit(0);
								}
						else
						{
						config.online = true;
						config.getNumber();
						Start();
						}
						
					}

					};
			
				ActionListener exitGame = new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.exit(0);
					}
				};
				
				online.addActionListener(startOnline);
				offline.addActionListener(startOffline);
				exit.addActionListener(exitGame);
			super.dispose();
		}
	
	
	    /**
	     * Metoda zamyka obecne okno i zostaje uruchomione startowe okno gry
	     */
	  private void Start() {
			this.dispose();
			 mainWindow = new MainWindow(true);
			Dimension dm = mainWindow.getToolkit().getScreenSize();
			mainWindow.setVisible(true);
			mainWindow.setLocation(
				(int) (dm.getWidth() / 2 - mainWindow.getWidth() / 2),
				(int) (dm.getHeight() / 2 - mainWindow.getHeight() / 2));
	  }
			

	    /**
	     * G��wna funkcja programu
	     * @param args ...
	     */
	public static void main(String[] args) 
	    {
		
	    	/** Metoda pobieraj�ca parametry z pliku konfiguracyjnego */
    	new config();
    	config.online=true;
	    	/** Stworzenie obiektu gry */
	    	Sokoban sokoban = new Sokoban();
	    	sokoban.setVisible(true);
	    	sokoban.pack();
	    	/** Zmienna ustawienia pozycji okna pocz�tkowego */
			Dimension dm = sokoban.getToolkit().getScreenSize();
			sokoban.setLocation(
					(int) (dm.getWidth() / 2 - sokoban.getWidth() / 2),
					(int) (dm.getHeight() / 2 - sokoban.getHeight() / 2));
	    }

}
