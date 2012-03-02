import java.util.Scanner;

public class Lab2p4 
{
  static final int UNDEF = -1;

  static int valoareMaxima(int t, Mobila[] v)
  {
    /* TODO: Calculati valoarea maxima transportabila de catre camionul de
     * capacitate t. */
    return 0;
  }


  public static void main(String[] args)
  {
    /* Declaram capacitatea camionului si un vector care sa retina tipurile de
     * mobila sub forma de perechi (greutate, valoare) si citim datele de intrare.
     */
    int t = (new Scanner(System.in)).nextInt();
    Mobila[] v = VectorUtil.readArrayOfReadables(new Mobila[0], Mobila.class);

    /* Afisam valoarea maxima transportabila de catre camion. */
    System.out.println("Valoarea maxima a mobilierului transportabil: " + valoareMaxima(t, v));
  }
}

class Mobila implements Readable
{
  public int greutate;
  public int valoare;

  public void read(Scanner scanner)
  {
    greutate = scanner.nextInt();
    valoare = scanner.nextInt();
  }
}
