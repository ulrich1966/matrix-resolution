package main;

import java.util.Scanner;

/**
 * Steuerung und Prüfung der Eingabe der Matrix-Werte
 * 
 * @author kevin bobzien
 *
 */
public class InputHandler {
	private Scanner scanner = new Scanner(System.in);
	private int size = 0;
	private int[][] adMatirx = null;
	private int counter = 0;

	/**
	 * Einstieg Einlesen der Konsoleneigabe und Steuerung der Eingabe fuer
	 * Einzeleingabe der Matrix-Zeilen und kommaseparierter eingabe einer kompletten
	 * Matrix in einer Zeile
	 */
	public int[][] input() {

		System.out.print("Gebe eine beliebg lange (aber micht all zu lange) Folge\nan 0 und 1 ein: ");
		String input = scanner.nextLine();

		if (input.contains(",")) {
			String[] split = input.split(",");
			for (String val : split) {
				readAndCheck(val);
				counter++;
			}
			return adMatirx;
		}

		readAndCheck(null);

		for (; counter < size;) {
			System.out.print("Gebe die " + (counter + 1) + ". Folge von " + size + " Ziffern 0 und 1 ein: ");
			readAndCheck(null);
			counter++;
		}

		// iterateMatrix(adMatirx);
		return adMatirx;
	}

	/**
	 * Validation der Eingabe und Aufnahme der Matrix-Zeilen in die Ausgangs - AD
	 * Matrix
	 * 
	 * @param input
	 */
	private void readAndCheck(String input) {
		char[] charArray = input.toCharArray();
		int i = 0;

		for (char c : charArray) {
			String is = String.format("%s", c).trim();
			if (!"0".equals(is) && !"1".equals(is)) {
				System.err.println(c + " ist weder eine 0 oder eine 1! Du musst von vorne anfangen");
				System.exit(1);
			}
			if (null == adMatirx) {
				size = charArray.length;
				adMatirx = new int[size][size];
			}
			if (charArray.length > size) {
				System.err.println("Die Eingabe von " + charArray.length + " Zeichen ist größer als die erforderliche Länge von " + size + " Zeichen");
				System.exit(1);
			}
			if (charArray.length < size) {
				System.err.println("Die Eingabe von " + charArray.length + " Zeichen ist kleiner als die erforderliche Länge von " + size + " Zeichen");
				System.exit(1);
			}
			adMatirx[counter][i] = Integer.valueOf(String.format("%s", charArray[i]));
			i++;
		}
	}
}
