import java.util.Scanner;


public class Lab1p1 
{
  static long karatsuba(long x, long y, int exp)
  {
    /* TODO: Implementati metoda de inmultire a doua numere folosind algoritmul
     * Karatsuba, scriind cele doua numere in baza (2^exp) si efectuand
     * inmultirile conform enuntului din laborator.
     *
     * TODO(pe hartie): Cate inmultiri elementare se efectueaza pentru algoritmul
     * de inmultire Karatsuba? Se stie ca pentru a imulti doua numere a cate K
     * biti fiecare, este nevoie de K^2 inmultiri elementare.
     */
    return 0;
  }

  public static void main(String[] args)
  {
    /* Citim doua numere int, de 32 biti, pe care le vom inmulti. */
    Scanner scanner = new Scanner(System.in);

    int n1 = scanner.nextInt();
    int n2 = scanner.nextInt();

    /* Afisam rezultatul inmultirii Karatasuba. */
    long prod_classic = ((long) n1) * ((long) n2);
    long prod_karatsuba = karatsuba(n1, n2, 16);

    if (prod_classic == prod_karatsuba) 
    {
      System.out.println("OK!");
      System.out.println("Produsul celor doua numere este: " + prod_classic);
    }
    else 
    {
      System.out.println("Fail!");

      System.out.println("Produsul celor doua numere este: " + prod_classic + 
                         ", iar Karatsuba a calculat: " + prod_karatsuba);
    }
  }
}
