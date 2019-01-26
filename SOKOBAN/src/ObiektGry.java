package elementy;

import java.awt.Image;

/**
 * Klasa reprezentuj¹ca pojedynczy obiekt planszy
 * 
 */

public class ObiektGry {
    /**
     * Pozycja pozioma
     */
    private int x;
    /**
     * Pozycja pionowa
     */
    private int y;
    /**
     * Obrazek obiektu
     */
    private Image image;

    /** Konstruktor obiektu gry 
     * @param x pozycja pozioma
     * @param y pozycja pionowa
     * 
     * */
    public ObiektGry(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /** 
     * Zwraca obrazek obiektu
     * @return zwraca obrazek obiektu
     * */
    public Image getImage() {
        return this.image;
    }
    /** 
     * Ustawienie obrazka obiektowi
     * @param img obrazek
     * */
    public void setImage(Image img) {
        image = img;
    }
    
    /** 
     * Metoda zwracaj¹ca pozycje poziom¹ obiektu
     * @return zwraca pozycje poziom¹ obiektu
     * */
    public int getX() {
        return this.x;
    }
    /** 
     * Metoda zwracaj¹ca pozycje pionow¹ obiektu
     * @return zwraca pozycje pionow¹ obiektu
     * */
    public int getY() {
        return this.y;
    }
    
    /** 
     *  Ustawia pozycjê poziom¹ obiektu
     *  @param x pozycja pozioma ktora nale¿y ustawiæ
     *  */
    public void setX(int x) {
        this.x = x;
    }
    /** 
     *  Ustawia pozycjê pionow¹ obiektu
     *  @param y pozycja pionowa ktora nale¿y ustawiæ
     *  */
    public void setY(int y) {
        this.y = y;
    }
    
    
}
