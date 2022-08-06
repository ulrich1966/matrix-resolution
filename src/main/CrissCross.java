package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Kevin Bobzien
 *
 */

public class CrissCross {
	private int[][] ad;
	private int[][] adt;
	private List<Integer> solutionSet = new ArrayList<>();
	private Integer currentSolutionItem;

	/**
	 * Konstruktor, der die zu verarbeitenden Arrays uebernimmt, die AD und
	 * ADT abbilden.
	 * 
	 * @param ad
	 * @param adt
	 */
	public CrissCross(int[][] ad, int[][] adt) {
		super();
		this.ad = ad;
		this.adt = adt;
	}

	/**
	 * Durlaeft das Array der AD Matrix und ruft ggf. die Methode zum Durchlaufen
	 * der ADT Matrix - Array auf.
	 * 
	 * @param adRow
	 * @param adCol
	 */
	public void walkThroughAd(int adRow, int adCol) {
		for (; adCol < ad[adRow].length; adCol++) {
			if(Start.DEBUG) 
				System.out.println("-----------------------------------------");
			if (ad[adRow][adCol] == 1) {
				if(Start.DEBUG) 
					System.out.println(String.format("AD: Row %d: Col %d - Wert: %d prüfen", adRow + 1, adCol + 1, ad[adRow][adCol]));
				walkThroughAdT(adRow, adCol);
			} else {
				if(Start.DEBUG) 
					System.out.println(String.format("AD: Row %d: Col %d - Wert: %d skiped", adRow + 1, adCol + 1, ad[adRow][adCol]));
			}
		}
		if(Start.DEBUG) 
			System.out.println("-----------------------------------------");
	}

	/**
	 * Durlaeuft das Array fuer die ADT Matrix. Prueft auf 0 und ermittelt, die
	 * Selbstreferenz. Ist die aktuelle Spalte nicht 0, keine Selbstreferenz und
	 * nicht bereits hinterlegt, wird der Wert in einer ArrayList fuer die
	 * Loesungsmenge gespeichert.
	 * 
	 * @param adRow
	 * @param adCol
	 */
	public void walkThroughAdT(int adRow, int adCol) {
		int adtCol = 0;
		boolean ref = false;

		for (; adtCol < adt[adCol].length; adtCol++) {
			if(Start.DEBUG)
				System.out.println("------------------ " + (adtCol + 1) + " --------------------");
			if (adt[adCol][adtCol] == 0) {
				if(Start.DEBUG)
					System.out.println("\tADT: " + (adtCol + 1) + " = " + adt[adCol][adtCol] + " skipted 0");
				continue;
			}
			currentSolutionItem = (adtCol + 1);
			if (!solutionSet.contains(currentSolutionItem)) {
				if (!ref && isSelfReference(adRow, adCol, adtCol)) {
					ref = true;
					if(Start.DEBUG)
						System.out.println("\tADT: " + (adtCol + 1) + " skipted SR");
					continue;
				}
				solutionSet.add(currentSolutionItem);
				if(Start.DEBUG)
					System.out.println("\tADT: " + currentSolutionItem + " added LM");
				walkThroughAd(adtCol, 0);
			} else {
				if(Start.DEBUG)
					System.out.println("\tADT: " + currentSolutionItem + " skipted EXIST");
			}
		}
	}

	/**
	 * Gibt die Matrizen AD und ADT auf der Konsole aus.
	 * 
	 * @param matrix
	 */
	public void iterateMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Prueft, ob ein Wert aus der AD Matrix die Selbstreferenz auf der ADT Matrix
	 * bildet. Die Bedingungen sind: 
	 * - Der Wert aus der AD Matrix muss gleich dem Wert aus der transponierten Matrix 
	 * sein. 
	 * - Der aus der Multiplikation der Koordinaten resultierende Bruch muss 1 sein, 
	 * um sicherzustellen, dass es sich um die Koordinate aus der AD der Selbstreferenz
	 * in der ADT Matrix handelt.
	 * 
	 * @param adRow
	 * @param adCol
	 * @param adtCol
	 * @return
	 */
	private boolean isSelfReference(int adRow, int adCol, int adtCol) {
		boolean isRef = false;
		int adtRow = adCol;
		int adVal = ad[adRow][adCol];
		int adtVal = adt[adtRow][adtCol];

		if(Start.DEBUG)
			System.out.println("\tad:\t" + adRow + ":" + adCol + " = " + adVal + "\n\tadt:\t" + adtRow + ":" + adRow + " = " + adtVal);

		isRef = adVal == adtVal;
		// Um 1 erweitern, damit wir nicht in die java.lang.ArithmeticException: Div by 0 laufen
		int numerators = (adRow + 1) * (adCol + 1);
		int denominator = (adtRow + 1) * (adtCol + 1);

		if(Start.DEBUG)
			System.out.println("\tZähler: " + numerators + " Nenner: " + denominator);

		if (isRef) {
			if ((numerators % denominator) == 0 && (numerators / denominator) == 1) {
				isRef = true;
			} else {
				isRef = false;
			}
		} else {
			isRef = false;
		}

		if(Start.DEBUG)
			System.out.println("\tselbstreferenzierned: " + isRef);
		
		return isRef;
	}

	public int[][] getAd() {
		return ad;
	}

	public int[][] getAdt() {
		return adt;
	}

	public List<Integer> getSolutionSet() {
		Arrays.sort(solutionSet.toArray());
		return solutionSet;
	}

	public void setSolutionSet(List<Integer> solutionSet) {
		this.solutionSet = solutionSet;
	}
}
