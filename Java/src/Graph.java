import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class Graph {

  /**
   * Muchie, reprezentata prin cele doua noduri (capetele) si capacitatea
   * asociata.
   */
  static class Edge {
    public int x, y, c;

    /**
     * @param x
     *            primul nod
     * @param y
     *            al doilea nod
     * @param c
     *            capacitatea asociata muchiei
     */
    public Edge(int x, int y, int c) {
      this.x = x;
      this.y = y;
      this.c = c;
    }
  }

  static enum Type {
    /**
     * Graf ORIENTAT
     */
    DIRECTED,

        /**
         * Graf NEOORIENTAT
         */
        UNDIRECTED
  }

  /**
   * NEDEFINIT
   */
  public final static int UNDEFINED = Integer.MAX_VALUE;

  /**
   * INIFINIT
   */
  public final static int INFINITY = Integer.MAX_VALUE;

  /**
   * Matricea de adiacenta asociata grafului.
   * 
   * adjMatrix[x][y] == true <=> exista un arc de la nodul "x" la nodul "y"
   */
  public boolean[][] adjMatrix;

  /**
   * Matricea de capacitati asociata grafului.
   * 
   * capacityMatrix[x][y] == capacitatea asociata arcului de la nodul "x" la
   * nodul "y"
   */
  public int[][] capacityMatrix;

  /**
   * Matricea de flux asociata grafului (poate fi folosita pentru a retine
   * fluxul transportat de retea).
   * 
   * flow[x][y] == fluxul asociat arcului de la nodul "x" la nodul "y"
   */
  public int[][] flow;

  private Vector<Vector<Integer>> neigh;
  private Vector<Edge> edges;
  private Vector<Integer> nodes;

  private int[] parents;
  private int size;

  private String filename = null;
  private Graph.Type type = null;

  public Graph.Type getType() {
    return type;
  }

  /**
   * @param filename
   *            numele fisierului din care va fi incarcat graful
   * @param type
   *            tipul grafului (orientat / neorientat)
   */
  public Graph(String filename, Graph.Type type) throws FileNotFoundException {
    /* remember constructor params, we will need them in on reloadFromDisk() */
    this.filename = filename;
    this.type = type;

    load(filename, type);
  }

  public Graph(int size, Graph.Type type) {
    newEmptyGraph(size, type);
  }

  /**
   * Reconstruieste graful folosind aceiasi paramentri din constructor.
   * 
   * Functia poate fi chemata pentru a reseta graful la starea initiala.
   */
  public void reloadFromDisk() throws FileNotFoundException {
    if (filename == null) {
      System.out.println("Graful nu a fost incarcat dintr-un fisier.");
      throw new FileNotFoundException();
    } else {
      load(filename, type);
    }
  }

  private void newEmptyGraph(int size, Graph.Type type) {
    this.size = size;
    this.type = type;

    allocObjects();
    initAdjMatrix();
    initCapacityMatrix();
    clearParents();
    clearFlow();
  }

  private void load(String filename, Graph.Type type) throws FileNotFoundException {
    Scanner inputScanner = new Scanner(new File(filename));

    int size = inputScanner.nextInt();
    int edgeCount = inputScanner.nextInt();

    newEmptyGraph(size, type);

    for (int i = 0; i < edgeCount; i++) {
      int x = inputScanner.nextInt();
      int y = inputScanner.nextInt();
      int c = inputScanner.nextInt();

      addEdge(x, y, c);
    }

    inputScanner.close();
  }

  /**
   * Adauga un arc de capacitate c intre nodurile x si y. Daca graful este
   * neorientat, va fi adaugat de asemenea si arcul (y, x, c).
   */
  public void addEdge(int x, int y, int c) {
    _addEdge(x, y, c);
    if (type == Graph.Type.UNDIRECTED)
      _addEdge(y, x, c);
  }

  private void _addEdge(int x, int y, int c) {
    adjMatrix[x][y] = true;
    capacityMatrix[x][y] = c;
    neigh.get(x).add(y);
    edges.add(new Edge(x, y, c));
  }

  private void initCapacityMatrix() {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        capacityMatrix[i][j] = UNDEFINED;
      }
    }
  }

  private void initAdjMatrix() {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        capacityMatrix[i][j] = UNDEFINED;
      }
    }
  }

  /**
   * Initializeaza toti parintii cu Graph.UNDEFINED.
   */
  public void clearParents() {
    for (int i = 0; i < size; i++) {
      parents[i] = UNDEFINED;
    }
  }

  /**
   * Retine pe "parent" drept parintele lui "node".
   * 
   * @param node
   *            nodul pe al carui parinte vrem sa il setam
   * @param parent
   *            parintele nodului
   */
  public void setParent(int node, int parent) {
    parents[node] = parent;
  }

  /**
   * Intoarce parintele unui anumit nod sau Graph.UNDEFINED daca acesta nu a
   * fost setat.
   */
  public int getParent(int node) {
    return parents[node];
  }

  /**
   * Tipareste calea intre doua noduri. Este necesar ca, in prealabil, sa fi
   * fost folosita functia setParent() pentru a seta corect parintii.
   * 
   * @param first
   *            nodul sursa
   * @param last
   *            nodul destinatie
   */
  public void printPathBetween(int first, int last) {
    System.out.print("" + first + " ");
    Stack<Integer> path = new Stack<Integer>();
    int node = last;
    do {
      path.push(node);
      node = getParent(node);
    } while (node != first);
    while (!path.empty()) {
      node = path.pop();
      System.out.print("" + node + " ");
    }
    System.out.println();

  }

  private void allocObjects() {

    /* Adj, Cost and Flow matrixes */
    adjMatrix = new boolean[size][size];
    capacityMatrix = new int[size][size];
    flow = new int[size][size];

    /* Neighbours */
    neigh = new Vector<Vector<Integer>>();
    for (int i = 0; i < size; i++) {
      neigh.add(new Vector<Integer>());
    }

    /* Parents */
    parents = new int[size];

    /* Edges */
    edges = new Vector<Graph.Edge>();

    /* Nodes */
    nodes = new Vector<Integer>();
    for (int i = 0; i < size; i++) {
      nodes.add(i);
    }
  }

  /**
   * Afiseaza matricea de adiacenta asociata grafului.
   */
  public void printAdjMatrix() {
    System.out.println("Matrice de adiacenta:");
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        System.out.print(adjMatrix[i][j] ? "1 " : "0 ");
      }
      System.out.println();
    }
  }

  /**
   * Afiseaza matricea de capacitati asociata grafului.
   */
  public void printCapacityMatrix() {
    System.out.println("Matricea costurilor:");
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        System.out.print("" + (capacityMatrix[i][j] == UNDEFINED ? "U" : capacityMatrix[i][j]) + " ");
      }
      System.out.println();
    }
  }

  /**
   * Afiseaza matricea de flux asociata grafului.
   */
  public void printFlowMatrix() {
    System.out.println("Matricea de flux:");
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        System.out.print("" + (flow[i][j] == UNDEFINED ? "U" : flow[i][j]) + " ");
      }
      System.out.println();
    }
  }

  /**
   * Afiseaza vectorul de parinti.
   */
  public void printParents() {
    System.out.println("Parinti:");
    for (int i = 0; i < size; i++) {
      if (parents[i] == UNDEFINED) {
        System.out.println("" + i + " : NEDEFINIT");
      } else {
        System.out.println("" + i + " : " + parents[i]);
      }
    }
  }

  /**
   * Afiseaza graful sub forma de liste de adiacenta (fiecare nod este urmat
   * de vecinii sai).
   */
  public void printNeighbours() {
    System.out.println("Liste vecini:");
    for (int i = 0; i < size; i++) {
      System.out.print("" + i + " : ");
      for (Integer v : neigh.get(i)) {
        System.out.print("" + v + " ");
      }
      System.out.println();
    }
  }

  /**
   * Seteaza toate elementele matricii de flux la 0.
   */
  public void clearFlow() {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        flow[i][j] = 0;
      }
    }
  }

  /**
   * Afiseaza toate informatiile despre graf
   */
  public void print() {
    printNeighbours();
    printAdjMatrix();
    printCapacityMatrix();
    printFlowMatrix();
    printParents();
    printEdges();
    printNodes();
  }

  public void printNodes() {
    System.out.print("Noduri: ");
    for (int node : getNodes()) {
      System.out.print("" + node + " ");
    }
    System.out.println();
  }

  public void printEdges() {
    System.out.println("Arce: ");
    for (Edge e : getEdges()) {
      System.out.print("(" + e.x + ", " + e.y + ", " + e.c + ") ");
    }
    System.out.println();
  }

  /**
   * Intoarce numarul de noduri din graf.
   */
  public int getSize() {
    return size;
  }

  /**
   * Intoarce true in cazul in care intre nodurile date ca parametru exista
   * muchie si false in caz contrar.
   */
  public boolean isEdge(int x, int y) {
    return adjMatrix[x][y];
  }

  public Vector<Integer> getNeighbours(int node) {
    return neigh.get(node);
  }

  /**
   * Intoarce un vector cu toate muchiile din graf.
   */
  public Vector<Edge> getEdges() {
    return edges;
  }

  /**
   * Intoarce un vector cu toate nodurile din graf pentru a putea fi folosit
   * (de exemplu) intr-o constructie precum "for(int k : graph.getNodes())".
   */
  public Vector<Integer> getNodes() {
    return nodes;
  }
}
