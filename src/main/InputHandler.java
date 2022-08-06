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
			// Wenn die Eingabezeile ein Komma enthaelt wird versucht die Matrix anhand der
			// (hoffentlich kompletten) Zeile zu erstellen.
			String[] split = input.split(",");
			for (String val : split) {
				nextLine(val);
			}
			return adMatirx;
		} else {
			// Wenn die Eingabezeile KEIN Komma enthaelt wird zur Eingabe der jeweils
			// naechsten Zeile aufgefordert.
			nextLine(input);
			for (; counter < size;) {
				System.out.print("Gebe die " + (counter + 1) + ". Folge von " + size + " Ziffern 0 und 1 ein: ");
				input = scanner.nextLine();
				nextLine(input);
			}
			return adMatirx;
		}
	}

	/**
	 * Stoeesst das Verarbeiten der aktuellen Zeile an und erhoeht den
	 * Zeilen-Zaehler;
	 * 
	 * @param val
	 */
	private void nextLine(String val) {
		readAndCheck(val);
		counter++;
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

		if (null == input || input.isEmpty()) {
			System.out.println("input hat keinen Inhalt");
			System.exit(1);
		}

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
