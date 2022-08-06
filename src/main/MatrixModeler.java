package main;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Kevin Bobzien
 *
 */

public class MatrixModeler {
	private int[][] adMatrix = null;
	private int[][] adtMatrix = null;
	private int[][] nOutMatrix = null;
	private int[] dimensions = null;
	private List<String> adLines = null;
	private List<String> adtLines = null;
	private List<String> nOutLines = null;

	/**
	 * Default Konstruktor
	 */
	public MatrixModeler() {
		super();
	}

	/**
	 * Bietet eine oeffentliche Aufrufmoeglichkeit der gekapselten Model-Funktion
	 * (matrixModeling). Setzt die Klassen-Variablen der Ausgangsmatrix und ihre
	 * Dimensionen. Delegiert an die private Model-Funktion (matrixModeling())
	 * weiter.
	 * 
	 * @param matrix
	 * @param dimensions
	 * @return
	 * @throws RuntimeException
	 */
	public int[][] matrixModeling(int[][] matrix, int[] dimensions) throws RuntimeException {
		this.adMatrix = matrix;
		this.dimensions = dimensions;
		return matrixModeling();
	}

	/**
	 * Erzeugt eine neue Instanz von int Array fuer die Zielmatrix (adtMatrix).
	 * Durchlaeuft die Ausgangsmatrix (adMatrix) und weist den Zeilen-Wert der
	 * adtMatrix als Spaltenwerte zu, so dass aus Zeilen Spalten werden. Gibt die
	 * gefuellte adtMatrix Matrix zurueck.
	 * 
	 * @return
	 * @throws RuntimeException
	 */
	private int[][] matrixModeling() throws RuntimeException {

		if (null == this.adMatrix || null == this.dimensions) {
			throw new RuntimeException("Es gibt kein Matrix-Array oder die Dimensionen des Arrays wurden nicht angegeben");
		}

		adtMatrix = new int[this.dimensions[0]][this.dimensions[1]];

		for (int i = 0; i < adMatrix.length; i++) {
			for (int y = 0; y < adMatrix[i].length; y++) {
				adtMatrix[y][i] = adMatrix[i][y];
			}
		}
		return adtMatrix;
	}

	/**
	 * Multipliziert zwei Matrizen (die adMatrix mit der adtMatrix) und gibt das
	 * Ergebnis als neue Matrix (nOutMatrix) zurueck.
	 * 
	 * @return
	 * @throws RuntimeException
	 */
	public int[][] matrixMultiplication() throws RuntimeException {
		this.nOutMatrix = new int[this.dimensions[0]][this.dimensions[1]];
		int a = 0;
		int b = 0;
		int c = 0;
		int temp = 0;
		for (int i = 0; i < adMatrix.length; i++) {
			if (Start.DEBUG) {
				System.out.println("-----------------------------------------");
				System.out.println("\tA Zeile: " + (i + 1));
				System.out.println("    A\t\t    B\n");
				printMatrix();
			}
			for (int j = 0; j < adMatrix[i].length; j++) {
				if (Start.DEBUG)
					System.out.println("\n-> B Spalte: " + (j + 1) + "\n");
				for (int y = 0; y < adtMatrix[i].length; y++) {
					a = adMatrix[i][y];
					b = adtMatrix[y][j];
					c = a * b;
					temp = c + temp;
					if (Start.DEBUG)
						System.out.println(" " + a + " * " + b + " = " + c);
				}
				this.nOutMatrix[i][j] = temp;
				if (Start.DEBUG) {
					System.out.println("---------");
					System.out.println(" =       " + temp);
				}
				temp = 0;
			}
			if (Start.DEBUG)
				System.out.println();
		}
		return this.nOutMatrix;
	}

	/**
	 * Zur uebersichtlieren Ausgabe werden die Matrix-Arrays in
	 * {@link java.uil.ArrayLists} ueberfuehrt.
	 */
	public void createLines() {
		this.adLines = new ArrayList<>();
		this.adtLines = new ArrayList<>();
		this.nOutLines = new ArrayList<>();
		
		for (int i = 0; i < adMatrix.length; i++) {
			String adLine = "";
			for (int j = 0; j < adMatrix[i].length; j++) {
				adLine = String.format("%s %d", adLine, adMatrix[i][j]);
			}
			this.adLines.add(adLine);
		}
		for (int i = 0; i < adtMatrix.length; i++) {
			String modLine = "";
			for (int j = 0; j < adtMatrix[i].length; j++) {
				modLine = String.format("%s %d", modLine, adtMatrix[i][j]);
			}
			this.adtLines.add(modLine);
		}
		for (int i = 0; i < nOutMatrix.length; i++) {
			String resultLine = "";
			for (int j = 0; j < nOutMatrix[i].length; j++) {
				resultLine = String.format("%s %d", resultLine, nOutMatrix[i][j]);
			}
			this.nOutLines.add(resultLine);
		}
	}
	
	/**
	 * Ausgabe / Darstellung der generierten Matrix {@link java.util.ArrayList} -
	 * Objekte auf der Konsole.
	 */
	public void printMatrix() {
		System.out.print(" AD");
		System.out.print("\t\t");
		System.out.print(" ADT");
		System.out.print("\t\t");
		System.out.println(" Nout");
		for (int i = 0; i < this.getAdLines().size(); i++) {
			System.out.print(this.adLines.get(i));
			System.out.print("\t");
			System.out.print(this.adtLines.get(i));
			System.out.print("\t");
			System.out.print(this.nOutLines.get(i));
			System.out.println();
		}
	}
	
	

	/**
	 * Getter und Setter der Klassenvariablen.
	 */

	/**
	 * @return the adMatrix
	 */
	public int[][] getAdMatrix() {
		return adMatrix;
	}

	/**
	 * @return the adtMatrix
	 */
	public int[][] getAdtMatrix() {
		return adtMatrix;
	}

	/**
	 * @return the nOutMatrix
	 */
	public int[][] getnOutMatrix() {
		return nOutMatrix;
	}

	/**
	 * @return the dimensions
	 */
	public int[] getDimensions() {
		return dimensions;
	}

	/**
	 * @return the adLines
	 */
	public List<String> getAdLines() {
		return adLines;
	}


	/**
	 * @return the adtLines
	 */
	public List<String> getAdtLines() {
		return adtLines;
	}

	/**
	 * @return the nOutLines
	 */
	public List<String> getnOutLines() {
		return nOutLines;
	}
}
