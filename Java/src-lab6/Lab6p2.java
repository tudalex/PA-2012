import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

enum Color {
  WHITE, GRAY, BLACK
}

class Node implements Comparable<Node> {
  public Color color;
  public int finishTime;
  public String subjectName;
  public int inDegree;
  public Vector<Node> neigh = new Vector<Node>();

  public Node(String subjectName) {
    this.subjectName = subjectName;
  }

  @Override
      public int compareTo(Node o) {
        return o.finishTime - this.finishTime;
      }
}

@SuppressWarnings("serial")
class Graph extends Vector<Node> {
}

public class Lab6p2 {

  static HashMap<String, Integer> subjectToInt = new HashMap<String, Integer>();

  static int time;

  static void dfs(Node node) {
    /* TODO: Puteti folosi aceasta functie pentru a implementa o parcurgere in
     * adancime (in cadrul careia sa completati si timpii de finish). */
  }

  static void topologicalSorting(Graph graph) {
    /* TODO: Sortati vectorul primit ca parametru astfel incat sa contina
     * nodurile in ordine topologica. */
  }

  static void planYears(Graph graph, Vector<Vector<String>> yearlyPlanning) {
    /* TODO: Folosind algoritmul lui Kahn, impartiti materiile la care fac
     * referire nodurile din vectorul graph pe ani, astfel incat:
     *
     * (a) Daca o materie A depinde de o materie B, atunci anul in care se face
     * materia A trebuie sa fie _strict_ mai mare decat anul in care se face
     * materia B.
     *
     * (b) Intr-un an nu se pot face mai mult de 5 materii.
     *
     * (c) Fiecare matrie trebuie planificata.
     *
     * In varaibila yearly planning veti pune in ordine, vectori de stringuri care
     * contin materiile ce vor fi parcurse in anii consecutivi de studiu: 1, 2, 3,
     * etc.
     */
  }

  public static void main(String[] args) throws FileNotFoundException {
    /* Declaram un graf. */
    Graph graph = new Graph();

    /* Citim un graf din fisierul de intrare. */
    Scanner scanner = new Scanner(new File("Materii.txt"));
    int edgeCount = scanner.nextInt();

    String subjectA, subjectB;
    for (int i = 0; i < edgeCount; ++i) {

      subjectA = scanner.next();
      scanner.next();
      subjectB = scanner.next();

      /*
       * Daca n-am mai vazut niciodata subjectA, atunci creaza-i un cod
       * unic.
       */
      if (subjectToInt.containsKey(subjectA) == false) {
        int code = subjectToInt.size();
        subjectToInt.put(subjectA, code);
        graph.add(new Node(subjectA));
      }

      /* La fel facem si cu subjectB. */
      if (subjectToInt.containsKey(subjectB) == false) {
        int code = subjectToInt.size();
        subjectToInt.put(subjectB, code);
        graph.add(new Node(subjectB));
      }

      /* Adaugam subjectB ca vecin al lui subjectA. */
      graph.get(subjectToInt.get(subjectA)).neigh.add(graph
                                                      .get(subjectToInt.get(subjectB)));
      graph.get(subjectToInt.get(subjectB)).inDegree++;
    }

    topologicalSorting(graph);

    System.out.println("O posibila sortare topologica este: ");
    for (int i = 0; i < graph.size(); ++i) {
      System.out.println("\t" + graph.get(i).subjectName);
    }

    /* Facem impartirea pe ani. */
    Vector<Vector<String>> yearlyPlanning = new Vector<Vector<String>>();
    planYears(graph, yearlyPlanning);

    System.out.println();
    System.out.println("Iar o impartire pe ani ar putea fi urmatoarea: ");
    for (int i = 0; i < yearlyPlanning.size(); ++i) {
      System.out.println("Anul " + (i + 1));
      Vector<String> subjects = yearlyPlanning.get(i);
      for (int j = 0; j < subjects.size(); ++j) {
        System.out.println("\t" + subjects.get(j));
      }
    }
  }
}
