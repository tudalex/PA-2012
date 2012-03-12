import java.util.Scanner;


public class Lab3p3 
{
  public static void main(String[] args)
  {
    /* Declaram si citim lungimea unei linii si un vector de cuvinte. */
    Scanner inputScanner = new Scanner(System.in);
    int l = inputScanner.nextInt();
    String line = inputScanner.nextLine();
    while(line.split(" ").length == 0)
      line = inputScanner.nextLine();
    String[] words = inputScanner.nextLine().split(" ");

    /* Verificam sa nu avem cuvinte mai lungi de o linie. */
    for (int i = 0; i < words.length; ++i) {
      if (words[i].length() > l) {
        System.out.println("Imposibil de rezolvat problema. Cuvantul " 
                           + words[i] + " este mai lung decat o linie.");
        return;
      }
    }

    /* Afisam impartirea greedy. Se vor afisa L caractere pe o linie, urmate de un
     * caracter pipe ('|') la final. */
    greedyFormat(l, words);

    System.out.println();

    /* Afisam impartirea folosind functia de cost din TeX. Se vor afisa L
     *caractere pe o linie, urmate de un caracter pipe ('|') la final.*/
    texFormat(l, words);

  }

  static void texFormat(int l, String[] v)
  {
    System.out.println("Tex format:");

    /* TODO: Calculati costul formatarii Tex si afisati textul formatat
     * folosind aceasta strategie. Afisati cate "l" caractere pe o linie, urmate de
     * un caracter "|" inainte de terminatorul de linie. */

    /* Afisam costul total. */
    System.out.println("TOTAL COST: " + 0);
  }

  static void greedyFormat(int l, String[] v)
  {
    System.out.println("Greedy format:");
    int cost = 0;

    /* TODO: Calculati costul formatarii Greedy si afisati textul formatat
     * folosind aceasta strategie. Afisati cate "l" caractere pe o linie, urmate de
     * un caracter "|" inainte de terminatorul de linie. */

    /* Afisam costul. */
    System.out.println("TOTAL COST: " + cost);
  }

  static void fillWithSpaces(int spaceCount)
  {
    while(spaceCount > 0)
    {
      System.out.print(" ");
      spaceCount--;
    }
  }
}

