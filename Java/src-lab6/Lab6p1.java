import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Lab6p1 {

  static final int dirLin[] = { -1, 0, 1, 0 };
  static final int dirCol[] = { 0, 1, 0, -1 };

  public static void find_exit(Maze maze, Coord source) {
    /* Pentru a reconstitui drumul, vom folosi o matrice de parinti. */
    Coord parent[][] = new Coord[maze.get_height()][maze.get_width()];

    for (int i = 0; i < maze.get_height(); ++i) {
      for (int j = 0; j < maze.get_width(); ++j) {
        parent[i][j] = Coord.NOWHERE;
      }
    }

    /* TODO: Implementati o cautare care sa depisteze drumul cel mai scurt de la
     * pozitia "source" la o iesire din labiring (vezi functia "is_exit_point").
     *
     * Dupa ce ati calculat drumul, inainte de iesirea din functie, trebuie sa il
     * marcati celula cu celula folosind functia "mark_solution_step" din clasa
     * Maze. */
  }

  public static void main(String[] args) throws FileNotFoundException {

    Scanner scanner = new Scanner(new File("Labirint.txt"));

    /* read height, width */
    int height = scanner.nextInt();
    int width = scanner.nextInt();
    scanner.nextLine();

    /* allocate & read maze */
    Maze maze = new Maze(height, width);
    maze.read(scanner);

    /* read robot location */
    int lineTrudy = scanner.nextInt();
    int columnTrudy = scanner.nextInt();
    System.out.println("trudy: " + lineTrudy + " " + columnTrudy);

    /* solve */
    find_exit(maze, new Coord(lineTrudy, columnTrudy));

    /* print path */
    maze.print();
  }
}

