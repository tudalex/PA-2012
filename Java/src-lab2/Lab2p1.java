import java.util.Arrays;
import java.util.Scanner;

public class Lab2p1 
{	
  static double valoareMaxima(int t, Material[] v)
  {
    /* TODO: Valoarea maxima care se poate transporta. */
    return 0;
  }


  public static void main(String[] args)
  {
    /* Declaram capacitatea camionului si un vector care sa retina tipurile
     * de material sub forma de perechi (greuate, valoare) si citim datele
     * de intrare.
     */
    int t = (new Scanner(System.in)).nextInt();
    Material[] v = VectorUtil.readArrayOfReadables(new Material[0], Material.class);

    System.out.println("Valoarea maxima a unui transport: " + valoareMaxima(t, v));
  }
}

class Material implements Readable, Comparable<Material>
{
  public int greutate;
  public double valoare;

  public void read(Scanner scanner)
  {
    greutate = scanner.nextInt();
    valoare = scanner.nextDouble();
  }

  public String toString()
  {
    return "(W = " + greutate + ", V = " + valoare + ")";
  }

  @Override
      public int compareTo(Material other)
      {
        double ratioThis = valoare / (double)greutate;
        double ratioOther = other.valoare / (double)other.greutate;

        if(ratioThis > ratioOther) return -1;
        return 1;
      }
}
