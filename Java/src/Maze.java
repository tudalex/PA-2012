import java.util.Scanner;

public class Maze implements Readable {
  private int height, width;

  private char[][] cell;

  private void allocate() {
    cell = new char[height][width];
    for (int i = 0; i < height; i++)
      for (int j = 0; j < width; j++)
        cell[i][j] = 1;
  }

  public Maze(int height, int width) {
    this.height = height;
    this.width = width;
    allocate();
  }

  public int get_width() {
    return width;
  }

  public int get_height() {
    return height;
  }

  public boolean is_walkable(int line, int column) {
    if (line >= 0 && line < height && column >= 0 && column < width) {
      return cell[line][column] != '#';
    } else {
      return false;
    }
  }

  public boolean is_walkable(Coord coord) {
    return is_walkable(coord.lin, coord.col);
  }

  public boolean is_exit_point(int line, int column) {
    if (line >= 0 && line < height && column >= 0 && column < width) {
      return is_walkable(line, column)
          && (line == 0 || line == height - 1 || column == 0 || column == width - 1);
    } else {
      return false;
    }
  }

  public boolean is_exit_point(Coord coord) {
    return is_exit_point(coord.lin, coord.col);
  }

  public void mark_solution_step(int line, int column) {
    if (line >= 0 && line < height && column >= 0 && column < width) {
      cell[line][column] = '*';
    }
  }

  public void mark_solution_step(Coord coord)
  {
    mark_solution_step(coord.lin, coord.col);
  }

  @Override
      public void read(Scanner scanner) {
        for (int i = 0; i < height; i++) {
          String cLine = scanner.nextLine();
          for (int j = 0; j < width; j++) {
            cell[i][j] = cLine.charAt(j);
          }
        }
      }

  public void print() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++)
        System.out.print(cell[i][j]);
      System.out.println();
    }
  }
}
