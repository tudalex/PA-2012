import java.util.Scanner;

public class Lab3p2 
{
  public static void main(String[] args)
  {
    /* Declaram si citim un vector de matrice de la consola. */
    Matrice[] v = VectorUtil.readArrayOfReadables(new Matrice[0], Matrice.class);

    /* Verificam intai ca lantul de matrice chiar se poate inmulti. */
    for (int i = 0; i < v.length - 1; ++i) 
    {
      if (v[i].nrColoane != v[i + 1].nrLinii) 
      {
        System.out.println("Nu se pot inmulti matricele " + i + " si " + (i + 1) + 
                           " de dimensiuni: " + v[i] + ", respectiv " + v[i + 1]);
        System.exit(0);
      }
    }

    /* Afisam numarul minim de operatii. */
    System.out.println("Numarul minim de operatii de inmultire elementare este: " +
                       minMultiplications(v));

    return;
  }

  private static int minMultiplications(Matrice[] v) 
  {
    /* Tiparim efectiv lantul parantezat si intoarcem numarul minim de operatii de
     * inmultire elementare. */
    return 0;
  }
}

class Matrice implements Readable
{
  int nrLinii;
  int nrColoane;

  public void read(Scanner scanner)
  {
    nrLinii = scanner.nextInt();
    nrColoane = scanner.nextInt();
  }

  public String toString()
  {
    return "[" + nrLinii + " x " + nrColoane + "]";
  }
}
