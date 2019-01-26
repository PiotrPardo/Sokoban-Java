package elementy;

import java.awt.Image;

import javax.swing.ImageIcon;
/**
 * Klasa reprezentuj¹ca obiekty Home
 * @author Piotr Pardo ,Rados³aw Bu³a
 * @version 1
 */
public class Home extends ObiektGry
{
    /**
     * Przechowuje obrazek obiektu
     */
	private Image image;
    /**
     * Tworzy obiekt Home
     * @param x pozycja pozioma
	 * @param y pozycja pionowa
     */
	public Home(int x, int y) {
		super(x, y);
        ImageIcon iia = new ImageIcon("img/baza.png");
        image = iia.getImage();
        this.setImage(image);
	}
	
}