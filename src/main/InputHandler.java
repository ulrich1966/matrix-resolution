package main;

import java.util.Scanner;

/**
 * Steuerung und Prüfung der Eingabe der Matrix-Werte.
 * 
 * @author Kevin Bobzien
 *
 */
public class InputHandler {
	private Scanner scanner = new Scanner(System.in);
	private int size = 0;
	private int[][] adMatrix = null;
	private int counter = 0;

	/**
	 * Einstieg: Einlesen der Konsoleneingabe und Steuerung der Eingabe fuer
	 * Einzeleingabe der Matrix-Zeilen und kommaseparierter Eingabe einer kompletten
	 * Matrix in einer Zeile.
	 */
	public int[][] input() {

		System.out.print("Gebe eine beliebig lange Folge\nan 0 und 1 ein (Zeile einer Matrix) an"
				+ "\noder eine mit Kommatas getrennte gleichbleibend große Zahlenreihe \naus 0 und 1: ");
		String input = scanner.nextLine();

		if (input.contains(",")) {
			// Wenn die Eingabezeile ein Komma enthaelt wird versucht die Matrix anhand der
			// Zeile zu erstellen.
			String[] split = input.split(",");
			for (String val : split) {
				nextLine(val);
			}
			return adMatrix;
		} else {
			// Wenn die Eingabezeile KEIN Komma enthaelt wird zur Eingabe der jeweils
			// naechsten Zeile aufgefordert.
			nextLine(input);
			for (; counter < size;) {
				System.out.print("Gebe die " + (counter + 1) + ". Folge von " + size + " Ziffern 0 und 1 ein: ");
				input = scanner.nextLine();
				nextLine(input);
			}
			return adMatrix;
		}
	}

	/**
	 * Stoesst das Verarbeiten der aktuellen Zeile an und erhoeht den
	 * Zeilen-Zaehler;
	 * 
	 * @param val
	 */
	private void nextLine(String val) {
		checkLine(val);
		counter++;
	}

	/**
	 * Validation der Eingabe und Aufnahme der Matrix-Zeilen in die Ausgangs - AD
	 * Matrix
	 * 
	 * @param input
	 */
	private void checkLine(String input) {
		if (null == input || input.isEmpty()) {
			System.out.println("input hat keinen Inhalt");
			System.exit(1);
		}

		char[] charArray = input.toCharArray();
		int i = 0;

		for (char c : charArray) {
			String inputString = String.format("%s", c).trim();
			if (!"0".equals(inputString) && !"1".equals(inputString)) {
				System.err.println(inputString + " ist weder eine 0 noch eine 1! Du musst von vorne anfangen");
				System.exit(1);
			}
			if (null == adMatrix) {
				this.size = charArray.length;
				adMatrix = new int[this.size][this.size];
			}
			if (charArray.length > size) {
				System.err.println("Die Eingabe von " + charArray.length + " Zeichen ist größer als die erforderliche Länge von " + size + " Zeichen");
				System.exit(1);
			}
			if (charArray.length < size) {
				System.err.println("Die Eingabe von " + charArray.length + " Zeichen ist kleiner als die erforderliche Länge von " + size + " Zeichen");
				System.exit(1);
			}
			adMatrix[counter][i] = Integer.valueOf(inputString);
			i++;
		}
	}
}
