package elementy;

import java.awt.Image;

import javax.swing.ImageIcon;



/**
 * Klasa reprezentuj¹ca obiekty Box
 * @author qwertacz
 */
public class Box extends ObiektyKolizyjne {
	
    /**
     * Przechowuje obrazek obiektu
     */
	private Image image;

	/**
	 * Konstruktor obiektu Box
	 * 
	 * @param x
	 *            pozycja pozioma
	 * @param y
	 *            pozycja pionowa
	 */
	public Box(int x, int y) {
		super(x, y);
		ImageIcon iia = new ImageIcon("img/klocek.png");
		image = iia.getImage();
		this.setImage(image);
	}

	/**
	 * Metoda przesuwaj¹ca Box w nowe miejscie
	 * 
	 * @param x
	 *            zmiana w poziomie
	 * @param y
	 *            zmiana w pionie
	 */
	public void move(int x, int y) {
		int nx = this.getX() + x; // nowa pozycja
		int ny = this.getY() + y;
		this.setX(nx); // ustawienie nowej pozycji
		this.setY(ny);
	}

}