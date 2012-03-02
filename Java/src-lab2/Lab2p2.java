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

    /* TODO: Puneti in v un sir de fractii care sa reprezinte descompunerea in
     * fractii egiptene a lui x/y. */

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
