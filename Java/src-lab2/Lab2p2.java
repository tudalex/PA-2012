import java.util.Scanner;
import java.util.Vector;

public class Lab2p2 
{
  public static void main(String[] args)
  {
    /* Citim x si y. */
    Scanner inputScanner = new Scanner(System.in);
    long x = inputScanner.nextLong();
    long y = inputScanner.nextLong();

    /* Calculam descompunerea in fractii egiptene. */
    Vector<Fractie> v = descompunere_fractii_egiptene(x, y);

    /* Si o afisam. */
    System.out.println(v);
  }

  private static long ceil(long a, long b)
  {
    return a / b + (a % b > 0 ? 1 : 0); 
  }

  private static Vector<Fractie> descompunere_fractii_egiptene(long x, long y) 
  {
    Vector<Fractie> v = new Vector<Fractie>();
    /* Cat timp nu am ajuns cu descompunerea la 0, continuam sa alegem cea mai
     * mare fractie de forma 1/n care este strict mai mica decat x/y, o adaugam la
     * suma si incecam sa descompunem mai departe diferenta (x/y - 1/n).
     *
     * Se observa ca acea fractie este mereu 1/(CEIL(y/x)).
     */
    do 
    {
      v.add(new Fractie(1, ceil(y, x)));
      long newx = (x - y % x) % x;
      long newy = y * ceil(y, x);
      x = newx;
      y = newy;
    } while (x != 0);

    return v;
  }
}

class Fractie
{
  public long numarator;
  public long numitor;

  public Fractie(long numarator, long numitor)
  {
    this.numarator = numarator;
    this.numitor = numitor;
  }

  public String toString()
  {
    return numarator + " / " + numitor;
  }
}
