package elementy;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.text.DecimalFormat;

import javax.swing.JPanel;


/**
 * Klasa reprezentuj¹ca panel boczny
 *Wyswietla aktualny poziom, iloœæ punktów i liczbê pozosta³ych ¿yæ
 */
public class ScorePanel extends JPanel {


	private static final long serialVersionUID = 1L;
	/**
	 * Obecny level w grze
	 */
	private int Level;
	/**
	 * Wynik punktowy
	 */
	private int Score;
	/**
	 * Liczba ¿yæ
	 */
	private int Lives;
	/**
	 * format do wyœwietlania wyniku
	 */
	private DecimalFormat format;

	/**
	 * Konstruktor panelu bocznegi
	 *@param lvl pocz¹tkowy poziom 
	 *@param rst pocz¹tkowa iloœæ ¿yæ
	 */
	public ScorePanel(int lvl, int rst)
	{
		Level = lvl;
		Lives = rst;
		format = new DecimalFormat("######");
		format.setMaximumIntegerDigits(6);
		format.setMinimumIntegerDigits(6);
	}
	
	
	/**
	 * Rysowanie panelu bocznego
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setDoubleBuffered(true);
		String score = format.format(Score);
		String level = "Poziom " + Integer.toString(Level);
		String lives = "¯ycia " + Integer.toString(Lives);

		int w = this.getWidth();
		int h = this.getHeight();
		FontMetrics fm = g.getFontMetrics();
		int rw = fm.stringWidth(lives);
		int lw = fm.stringWidth(level);
		int sw = fm.stringWidth(score);
		int sh = fm.getAscent();



		g.drawString(level, w / 2 - lw / 2, h / 8);
		g.drawString(lives, w / 2 - rw / 2, (h / 2)+(h/4));
		g.drawString(score, w / 2 - sw / 2, h / 2 + sh / 2);
	}

	
	/**
	 * Ustawienie nowego wyniku i przerysowanie
	 * @param score wynik który nale¿y wyœwietliæ
	 */
	public void setScore(int score) {
		Score = score;
		repaint();
	}
	/**
	 * Ustawienie nowego poziomu i przerysowanie
	 * @param level poziom który nale¿y wyœwietliæ
	 */
	public void setLevel(int level) {
		Level = level;
		repaint();
	}
	
	/**
	 * Ustawienie nowej liczby ¿yæ i przerysowanie
	 * @param lives iloœæ ¿yæ któr¹ nale¿y wyœwietliæ
	 */
	public void setLives(int lives) {
		Lives = lives;
		repaint();
	}

}
