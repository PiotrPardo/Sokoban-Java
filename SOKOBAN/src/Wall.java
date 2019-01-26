package elementy;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Klasa reprezentuj¹ca obiekty Wall
 * 
 */
public class Wall extends ObiektyKolizyjne{
    /**
     * Przechowuje obrazek obiektu
     */
	private Image image;
	
    /**
     * Konstruktor obiektu wall
     *@param x pozycja pozioma
	 * @param y pozycja pionowa
     */
	public Wall(int x, int y) {
		super(x, y);
       ImageIcon iia = new ImageIcon("img/sciana.png");
       image = iia.getImage();
       this.setImage(image);
	}

}