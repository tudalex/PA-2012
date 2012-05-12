import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Lab10p1 {

  /* Fisiere de intrare */
  final static String TASK_1_INPUT_FILE = "task1.in";
  final static String TASK_2_INPUT_FILE = "task2.in";

  /* Sursa si destinatia pentru flux */
  final static int flowSource = Graph.UNDEFINED;
  final static int flowDest = Graph.UNDEFINED;

  public static int maxFlow(Graph g, int u, int v) {

    int maxFlow = Graph.UNDEFINED;

    /*
     * TODO
     * 
     * calculeaza fluxul maxim care poate fi transportat prin retea de la
     * nodul u la nodul v
     */

    System.out.println("Flux maxim de la " + u + " la " + v + ": " + maxFlow);
    return maxFlow;
  }

  public static void minCut(Graph g, int u, int v) {
    Vector<Integer> A = new Vector<Integer>();
    Vector<Integer> B = new Vector<Integer>();

    /*
     * TODO
     * 
     * Imparte nodurile grafului in 2 multimi disjuncte, A si B.
     * 
     * Puteti folosi matricea g.flow calculata anterior.
     */

    /* afisam cele doua multimi */
    System.out.println("Taietura minima:");
    System.out.println("A: " + A);
    System.out.println("B: " + B);
    System.out.println();
  }

  public static void disjointPaths(Graph g, int u, int v) {

    /*
     * TODO
     * 
     * Afiseaza un set de cai disjuncte intre nodurile u si v.
     * 
     * Puteti folosi matricea g.flow calculata anterior.
     */
  }

  public static void task2() throws FileNotFoundException {

    Scanner scanner = new Scanner(new File(TASK_2_INPUT_FILE));

    /* numar de linii */
    int n = scanner.nextInt();

    /* numar de coloane */
    int m = scanner.nextInt();

    /* matricea de numere reale */
    float numbers[][] = new float[n][m];

    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        numbers[i][j] = scanner.nextFloat();

    scanner.close();

    /*
     * TODO
     * 
     * Task 2.
     * 
     * Folositi n, m, numbers (vezi mai sus). Pentru a crea un graf nou,
     * gol, folositi constructorul Graph(size, type) si adaugati muchii
     * folosind Graph.addEdge(x, y, c).
     */
  }

  public static void main(String[] args) throws FileNotFoundException {
    Graph g = new Graph(TASK_1_INPUT_FILE, Graph.Type.DIRECTED);

    /* TASK 1 - a */
    maxFlow(g, flowSource, flowDest);

    /* TASK 1 - b */
    minCut(g, flowSource, flowDest);

    /* TASK 1 - c */
    disjointPaths(g, flowSource, flowDest);

    /* TASK 2 */
    task2();
  }
}
