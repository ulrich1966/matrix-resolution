package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author Kevin Bobzien
 *
 */
public class Start {
	private static MatrixModeler modeler;
	public static final boolean DEBUG = false;

	/**
	 * Einstiegspunkt des Programms. Zeigt zunaechst die Arrays der Ausgangsmatrix
	 * und der transponierten Matrix, so wie das der Nout-Matrix. Erstellt
	 * eine Instanz des {@link CrissCross} - Objekt und uebernimmt die Steuerung der
	 * Durchlaeufe der AD Matrix - Arrays und praesentiert die Loesungsmengen in der
	 * Konsole.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		InputHandler inputHandler = new InputHandler();
		int[][] adMatrix = inputHandler.input();

		int length = adMatrix[0].length;
		int[] dimensions = { length, length };

		modeler = new MatrixModeler();
		modeler.matrixModeling(adMatrix, dimensions);
		modeler.matrixMultiplication();
		modeler.createLines();
		System.out.println();
		modeler.printMatrix();
		System.out.println();

		CrissCross crissCross = new CrissCross(modeler.getAdMatrix(), modeler.getnOutMatrix());
		int count = crissCross.getAd().length;

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < count; i++) {
			if (DEBUG) {
				System.out.println("-----------------------------------------");
				System.out.println("	Lap: " + (i + 1) + " / " + count);
				System.out.println("-----------------------------------------");
			}

			crissCross.setSolutionSet(new ArrayList<>());
			crissCross.walkThroughAd(i, 0);

			sb.append("Z(" + (i + 1) + ")={");

			if (!crissCross.getSolutionSet().isEmpty()) {
				sort(crissCross.getSolutionSet());
				crissCross.getSolutionSet().forEach(entry -> sb.append(entry + ","));
				String substring = sb.substring(0, (sb.lastIndexOf(",")));
				sb.replace(0, sb.length(), substring);
			} else {
				sb.append("Ø");
			}
			sb.append("}\n");
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++++++\n");
		System.out.println(sb.toString());
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
	}

	/**
	 * Sortiert die Array-Liste der Loesungsmenge.
	 * 
	 * @param list
	 */
	private static void sort(List<Integer> list) {
		Collections.sort(list, new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return (a).compareTo(b);
			}
		});
	}
}
