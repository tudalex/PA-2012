public class Coord {

  final static Coord NOWHERE = new Coord(-1, -1);

  public int lin, col;

  public Coord(int lin, int col) {
    this.lin = lin;
    this.col = col;
  }
}
