package elementy;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Klasa reprezentuj¹ca obiekt gracza
 * 
 */
public class Player extends ObiektyKolizyjne
	{
    /**
     * Przechowuje obrazek obiektu
     */
	private Image image;
	    /**
	     * Konstruktor obiektu gracza
	     *@param x pozycja pozioma
		 * @param y pozycja pionowa
	     */
		public Player(int x, int y) {
			super(x, y);
	        ImageIcon iia = new ImageIcon("img/gracz.png");
	        image = iia.getImage();
	        this.setImage(image);
		}
	    /**
	     *  Metoda przesuwaj¹ca gracza w nowe miejscie
	     *  @param x zmiana w poziomie
	     *  @param y zmiana w pionie
	     *  */
		 public void move(int x, int y) 
		 {	
		        int nx = this.getX() + x; //nowa pozycja
		        int ny = this.getY() + y;
		        this.setX(nx);			//ustawienie nowej pozycji
		        this.setY(ny);
		    }
	}