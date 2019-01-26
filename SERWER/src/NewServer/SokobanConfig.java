package NewServer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Klasa zawieraj�ca metody s�u��ce do odczytywania i zapisywania do pliku.
 */
public class SokobanConfig {

	/**
	 * Metoda pobieraj�ca mapy z pliku
	 * @param number numer planszy, kt�ra ma by� odczytana
	 * @return temp mapa jako ci�g string�w
	 */
	public static String[] getMap(String number) {
		File file = new File("Levels/" + "level" + number + ".txt");
		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Taka plansza nie istnieje");
			return null;
		}
		String[] temp = new String[20];
		try {
			for (int i = 0; i < 1; i++) {
				temp[i] = in.nextLine();
			}
		} catch (NoSuchElementException e) {
			System.out.println("Nie mo�na odczyta� "
					+ "parametr�w poziomu z pliku1");
			try {
				in.close();
			} catch (IllegalStateException ise) {
			}
			return null;
		} catch (IllegalStateException e) {
			System.out.println("Nie mo�na odczyta� "
					+ "parametr�w poziomu z pliku2");
			try {
				in.close();
			} catch (IllegalStateException ise) {
			}
			return null;
		}
		try {
			in.close();
		} catch (IllegalStateException e) {
		}
		return temp;
	}
	/**
	 * Metoda pobieraj�ca najlepsze wyniki z pliku
	 * @return temp lista najlepszych wynik�w
	 */
	public static String[] getHighScores() {
		File file = new File("high_scores.txt");
		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			return null;
		}
		String[] temp = new String[20];
		try {
			for (int i = 0; i < 20; i++) {
				temp[i] = in.nextLine();
			}
		} catch (NoSuchElementException e) {
			try {
				in.close();
			} catch (IllegalStateException ise) {
			}
			return null;
		} catch (IllegalStateException e) {
			try {
				in.close();
			} catch (IllegalStateException ise) {
			}
			return null;
		}
		try {
			in.close();
		} catch (IllegalStateException e) {
		}
		return temp;
	}
	/**
	 * Metoda pobieraj�ca liczb� map na serwerze z pliku.
	 * @return temp liczba map na serwerze
	 */
	public static String[] getNumberMaps() {
		File file = new File("Levels/" + "Levels.txt");
		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			return null;
		}
		String[] temp = new String[20];
		try {
			for (int i = 0; i < 1; i++) {
				temp[i] = in.nextLine();
			}
		} catch (NoSuchElementException e) {
			try {
				in.close();
			} catch (IllegalStateException ise) {
			}
			return null;
		} catch (IllegalStateException e) {
			try {
				in.close();
			} catch (IllegalStateException ise) {
			}
			return null;
		}
		try {
			in.close();
		} catch (IllegalStateException e) {
		}
		return temp;
	}
	
	/**
	 * Metoda zapisuj�ca najlepszy wynik pobrany od klienta
	 * @param name nick u�ytkownika pobrany od klienta
	 * @param score wynik u�ytkownika pobrany od klienta
	 */
	public static void saveScore(String name, String score) {
		File file = new File("high_scores.txt");
		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Nie mo�na odczyta� najlepszych wynik�w z pliku");
			return;
		}
		String[] names = new String[10];
		String[] scores = new String[10];
		try {
			for (int i = 0; i < 10; i++) {
				names[i] = in.nextLine();
				scores[i] = in.nextLine();
			}
		} catch (NoSuchElementException e) {
			System.out.println("Nie mo�na odczyta� najlepszych wynik�w z pliku");
			try {
				in.close();
			} catch (IllegalStateException ise) {
			}
			return;
		} catch (IllegalStateException e) {
			System.out.println("Nie mo�na odczyta� najlepszych wynik�w z pliku");
			try {
				in.close();
			} catch (IllegalStateException ise) {
			}
			return;
		}
		try {
			in.close();
		} catch (IllegalStateException e) {
		}
		try {
			int high = Integer.parseInt(score);
			for (int i = 0; i < 10; i++) {
				if (Integer.parseInt(scores[i]) < high) {
					for (int j = 9; j > i; j--) {
						scores[j] = scores[j - 1];
						names[j] = names[j - 1];
					}
					scores[i] = score;
					names[i] = name;
					break;
				}
				if (i == 9) {
					return;
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Dane wys�ane przez klienta s� niepoprawne");
			return;
		}
		PrintWriter save = null;
		try {
			save = new PrintWriter("high_scores.txt");
			for (int i = 0; i < 10; i++) {
				save.println(names[i]);
				save.println(scores[i]);
			}
			save.close();
			System.out.println("Nowy wynik zosata� zapisany do pliku");
		} catch (FileNotFoundException e) {
			System.out.println("Wysy�anie najlepszych wynik�w");
		} catch (IllegalStateException e) {
			System.out.println("Wysy�anie najlepszych wynik�w");
			try {
				save.close();
			} catch (IllegalStateException ise) {
			}
		}
	}
}
