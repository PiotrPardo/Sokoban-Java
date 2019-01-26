package NewServer;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Klasa odpowiadaj�ca za wygl�d i dzia�anie okna serwera.
 */
public class Server extends JFrame {
	
	/**
	 * Uniwersalny identyfikator wersji dla klasy Serializable.
	 */
	private static final long serialVersionUID = 1L;
	public Connection connection;
	
	/**
	 * Kostruktor two��cy okno serwera
	 */
	public Server() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	     setTitle("Server");
         setSize(250, 200);

		JButton stopB = new JButton("STOP");
		final JButton startB = new JButton("START");
		JLabel label = new JLabel("SERVER");
		JLabel ip = new JLabel("");
		JLabel host = new JLabel("Host: 5000");
		label.setFont(new Font("Serif", Font.PLAIN, 60));
		add(label, BorderLayout.NORTH );
		add(startB,BorderLayout.EAST);
		add(stopB,BorderLayout.WEST);
		
			  try {
				ip = new JLabel("   " + "IP Serwera" + ":   "
						+ InetAddress.getLocalHost().getHostAddress() + "           ");
			} catch (UnknownHostException e) {
				System.exit(0);
			}
			 add(ip,BorderLayout.SOUTH);
			 add(host, BorderLayout.CENTER);

			ActionListener exit = new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.exit(0);
					}
				};	 
				
			ActionListener start = new ActionListener() {
					public void actionPerformed(ActionEvent event) {
    						int port = 5000;
    						try {
    							connection = new Connection(port);
    				              startB.setEnabled(false);
    						} catch (Exception e) {
    						}
    					}    									
				};	 
			 
				stopB.addActionListener(exit);
				startB.addActionListener(start);	
			 
		setVisible(true);
		
	}

	/**
	 * Klasa g��wna odpowidaj�ca za utworzenie i utworzenie serwera
	 * @param args zmienna w g�ownej klasie
	 */
	public static void main(String[] args) {
		
				Server server = new Server();
				server.pack();
				Dimension dm = server.getToolkit().getScreenSize();
				server.setLocation(
						(int) (dm.getWidth() / 2 - server.getWidth() / 2),
						(int) (dm.getHeight() / 2 - server.getHeight() / 2));

	}
}