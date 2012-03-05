import java.util.Arrays;
import java.util.Scanner;

public class Lab2p1 
{  
  static double valoareMaxima(int t, Material[] v)
  {
    /* Deoarece putem lua si cantitati fractionare de materiale, vom sorta
     * descrescator dupa pretul per cantitate si vom alege cele mai scumpe
     * produse pana cand vom umple camionul. 
     */
    Arrays.sort(v);

    double valMax = 0;

    /* Parcurgem vectorul sortat si alegem produse pana cand nu mai este loc
     * in camion sau pana cand nu mai avem produse. 
     */
    for (int i = 0; i < v.length && t > 0; ++i){
      if (t >= v[i].greutate)
      {
        /* Daca putem lua produsul in intregime. */
        valMax += v[i].valoare;
        t -= v[i].greutate;
      } 
      else 
      {
        /* Nu avem destul loc, luam doar cat mai este loc in camion */
        valMax += (v[i].valoare / v[i].greutate) * t;
        t = 0;
      }
    }

    /* Valoarea maxima a produselor care se poate obtine este val_max */
    return valMax;
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
      public int compareTo(Material other) {
        double ratioThis = valoare / (double)greutate;
        double ratioOther = other.valoare / (double)other.greutate;

        if(ratioThis > ratioOther) return -1;
        return 1;
      }
}
