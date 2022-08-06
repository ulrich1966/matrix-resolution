package main;

import java.util.ArrayList;
import java.util.List;

public class MatrixModeler {
	private int[][] sourceMatrix = null;
	private int[][] transMatrix = null;
	private int[][] resultMatrix = null;
	private static int[] dimensions = null;
	private List<String> sourceLines = null;
	private List<String> modLines = null;
	private List<String> resultLines = null;

	/**
	 * Default Konstruktor
	 */
	public MatrixModeler() {
		super();
	}

	/**
	 * Bietet eine oeffentliche Aufrufmoeglichkeit der gekapselten Model-Funktion
	 * (matrixModeling). Setzt die Klassen-Variablen der Ausgangs-Matrix und die
	 * ihre Dimensionen. Deligiert an die private Model-Funktion (matirxModeling())
	 * weiter.
	 * 
	 * @param matrix
	 * @param dimensions
	 * @return
	 * @throws RuntimeException
	 */
	public int[][] matirxModeling(int[][] matrix, int[] dimensions) throws RuntimeException {
		this.sourceMatrix = matrix;
		MatrixModeler.dimensions = dimensions;
		return matrixModeling();
	}

	/**
	 * Erzeugt eine neue Instanz von int Array fuer die modifierte Matrix
	 * (modMatrix). Durchlaeuft die Ausgansmatrix (sourceMatrix) und weist den
	 * Zeilen-Wert der modifizieten Matrix als Spaltenwerte zu, so dass aus Zeilen
	 * Spalten werden. Gibt die gefuellte mod. Matrix zurueck
	 * 
	 * @return
	 * @throws RuntimeException
	 */
	private int[][] matrixModeling() throws RuntimeException {

		if (this.sourceMatrix == null || MatrixModeler.dimensions == null) {
			throw new RuntimeException("Es gibt kein Matrix-Array oder die Dimensionen des Arrays wurden nicht angegeben");
		}

		transMatrix = new int[MatrixModeler.dimensions[0]][MatrixModeler.dimensions[1]];

		for (int i = 0; i < sourceMatrix.length; i++) {
			for (int y = 0; y < sourceMatrix[i].length; y++) {
				transMatrix[y][i] = sourceMatrix[i][y];
			}
		}
		// createLines();
		return transMatrix;
	}

	/**
	 * Multipliziert zwei Matrixen (die sourceMatrix mit der modMatrix). und gibt
	 * das Ergebnis als neue Matrix zurueck.
	 * 
	 * @return
	 * @throws RuntimeException
	 */
	public int[][] matrixMultiplication() throws RuntimeException {
		this.resultMatrix = new int[MatrixModeler.dimensions[0]][MatrixModeler.dimensions[1]];
		int a = 0;
		int b = 0;
		int c = 0;
		int temp = 0;
		for (int i = 0; i < sourceMatrix.length; i++) {
			if (Start.DEBUG) {
				System.out.println("-----------------------------------------");
				System.out.println("\tA Zeile: " + (i + 1));
				System.out.println("    A\t\t    B\n");
				printMatrix();
			}
			for (int j = 0; j < sourceMatrix[i].length; j++) {
				if (Start.DEBUG)
					System.out.println("\n-> B Spalte: " + (j + 1) + "\n");
				for (int y = 0; y < transMatrix[i].length; y++) {
					// if(Start.DEBUG) System.out.print("i="+i+" j="+j+" y="+y+"\t");
					a = sourceMatrix[i][y];
					b = transMatrix[y][j];
					c = a * b;
					temp = c + temp;
					if (Start.DEBUG)
						System.out.println(" " + a + " * " + b + " = " + c);
				}
				this.resultMatrix[i][j] = temp;
				if (Start.DEBUG) {
					System.out.println("---------");
					System.out.println(" =       " + temp);
				}
				temp = 0;
			}
			if (Start.DEBUG)
				System.out.println();
		}
		// if (Start.DEBUG)
		// iterateMatrix(resultMatrix);
		return this.resultMatrix;
	}

	/**
	 * Zur uebersichtlieren Ausgabe werden die Matirx Arrays in
	 * {@link java.uil.ArrayLists} ueberfuehrt
	 */
	public void createLines() {
		this.sourceLines = new ArrayList<String>();
		this.modLines = new ArrayList<String>();
		this.resultLines = new ArrayList<String>();
		for (int i = 0; i < sourceMatrix.length; i++) {
			String sourceLine = "";
			for (int j = 0; j < sourceMatrix[i].length; j++) {
				sourceLine = String.format("%s %d", sourceLine, sourceMatrix[i][j]);
			}
			this.sourceLines.add(sourceLine);
		}
		for (int i = 0; i < transMatrix.length; i++) {
			String modLine = "";
			for (int j = 0; j < transMatrix[i].length; j++) {
				modLine = String.format("%s %d", modLine, transMatrix[i][j]);
			}
			this.modLines.add(modLine);
		}
		for (int i = 0; i < resultMatrix.length; i++) {
			String resultLine = "";
			for (int j = 0; j < resultMatrix[i].length; j++) {
				resultLine = String.format("%s %d", resultLine, resultMatrix[i][j]);
			}
			this.resultLines.add(resultLine);
		}
	}

	/**
	 * Iteriert ueber ein Matrix-Array und gibt dessen Werte auf der Konsole aus.
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
	 * Ausgabe / Darstellung der generierten Matrix {@link java.util.ArrayList} -
	 * Objekte auf der Konsole.
	 */
	public void printMatrix() {
		System.out.print(" AD");
		System.out.print("\t\t");
		System.out.print(" ADT");
		System.out.print("\t\t");
		System.out.println(" Mpl.");
		for (int i = 0; i < this.getSourceLines().size(); i++) {
			System.out.print(this.sourceLines.get(i));
			System.out.print("\t");
			System.out.print(this.modLines.get(i));
			System.out.print("\t");
			System.out.print(this.resultLines.get(i));
			System.out.println();
		}
	}

	/**
	 * Getter und Setter der Klassenvariablen
	 */

	public int[][] getResultMatrix() {
		return resultMatrix;
	}

	public int[][] getSourceMatrix() {
		return sourceMatrix;
	}

	public int[][] getTransMatrix() {
		return transMatrix;
	}

	public void setResultMatrix(int[][] resultMatrix) {
		this.resultMatrix = resultMatrix;
	}

	public static int[] getDimensions() {
		return dimensions;
	}

	public static void setDimensions(int[] dimensions) {
		MatrixModeler.dimensions = dimensions;
	}

	public List<String> getSourceLines() {
		return sourceLines;
	}

	public List<String> getModLines() {
		return modLines;
	}
}
