import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class Lab4p1 
{
  /* Numarul de intrari in recursivitate (pentru evaluarea performantelor). */
  static int recursions = 0;

  /* Cuvintele disponibile in dictionar (ca vector de stringuri). */
  static Vector<String> vocabular = new Vector<String>();

  /* Obiectul Rebus in sine. Are doar cateva metode ajutatoare gata scrise,
   * verificati documentatia pentru detalii. */
  static Rebus rebus = new Rebus();

  public static void readDictionar()
  {
    File file = new File("Vocabular.txt");
    Scanner scanner = null;
    try 
    {
      scanner = new Scanner(file);
    }
    catch (FileNotFoundException e) 
    {
      e.printStackTrace();
    }

    while(scanner.hasNextLine())
    {
      vocabular.add(scanner.nextLine());
    }
    scanner.close();
  }

  public static void main(String[] args) throws FileNotFoundException 
  {
    /* citim rebusul */
    rebus.read(new Scanner(new File("Puzzle.rebus")));

    /* citim dictionarul */
    readDictionar();

    /* Inregistram timpul de inceput */
    long timeStartNs = System.nanoTime();

    Domains horizontal = new Domains(rebus.rows, rebus.columns);
    Domains vertical = new Domains(rebus.rows, rebus.columns);
    buildWordSets(horizontal, vertical);

    Vector<String> prefixes = new Vector<String>();
    for(int i = 0; i < rebus.columns; i++)
      prefixes.add("");
    backtracking(0, 0, horizontal, vertical, prefixes);

    /* Inregistram timpul de sfarsit */
    long timeEndNs = System.nanoTime();

    /* Afisam numarul de apeluri recursive si diferenta de timp. */
    long timeDiff = timeEndNs - timeStartNs;
    System.out.println("Total recursive calls: " + recursions);
    System.out.println("Total time spent: " + timeDiff);
  }

  /* Functie care umple cele doua tabele de restrictii, orizontala sau verticala
   * cu cuvinte care ar putea, ipotetic, sa fie completate in Rebus. */
  static void buildWordSets(Domains horizontal, Domains vertical)
  {
    /* Si completam. */
    for (int i = 0; i < rebus.rows; ++i) {
      for (int j = 0; j < rebus.columns; ++j) {
        if (rebus.is_empty(i, j) &&
            (j == 0 || rebus.is_empty(i, j - 1) == false)) {
          /* TODO: Puneti in horizontal, la pozitia (i, j), toate cuvintele care
           * ar putea ipotetic sa fie completate incepand de acolo spre dreapta. */
        }
        if (rebus.is_empty(i, j) &&
            (i == 0 || rebus.is_empty(i - 1, j) == false)) {
          /* TODO: Puneti in vertical, la pozitia (i, j), toate cuvintele care ar
           * putea ipotetic sa fie completate incepand de acolo in jos. */
        }
      }
    }
  }

  static boolean found_solution = false;
  static void backtracking(
      int row,
      int col,
      Domains horizontal,
      Domains vertical,
      Vector<String> verticalPrefixes)
  {

    /* Marcam faptul ca am mai efectuat o intrare in recursivitate. */
    recursions++;

    /* Daca e solutie, afiseaz-o si iesi. */
    if (rebus.is_done()){
      System.out.println(rebus.toString());
      found_solution = true;
      return;
    } else if (row == rebus.rows) {
      return;
    }

    if (rebus.is_empty(row, col)) {
      /* Daca la (row, col) nu este completat, atunci incercam sa completam
       * orizontal unul dintre stringurile din domeniu pentru (row, col). */

      Vector<String> possibilities = horizontal.get(new Position(row, col));

      /* TODO: Incercati pe rand toate variantele de a completa de la (row, col)
       * in dreapta. Puteti folosi functia putString din Rebus.h */  

    }
    else 
    {
      /* Cand treci peste un '*', sterge prefixul care se forma vertical pentru
       * coloana respectiva. */
      if (rebus.get(row, col) == '*') 
      {
        verticalPrefixes.set(col, "");
      }

      /* TODO: Fiind deja completat, treci mai departe la completarea cu
       * backtracking a urmatoarei celule de la dreapta (si daca suntem la
       * sfarsitul randului, a primei celule de pe urmatoarea linie). */
    }
  }

  /* Functie care verifica daca avem blocare. */
  static boolean verificaInainte(int row,
                                 int col,
                                 String s,
                                 Domains horizontal,
                                 Domains vertical,
                                 Vector<String> prefixes) {

    /* Reducem domeniul orizontal la s - din moment ce tocmai am completat "s"
     * incepand de la (row, col) spre dreapta, atunci domeniul se colapseaza la
     * acest string. */
    horizontal.get(new Position(row, col)).clear();
    horizontal.get(new Position(row, col)).add(s);

    /* TODO: Parcurgem prefixele nou modificate si stergem din domeniul vertical tot ce
     * nu se potriveste cu prefixele. Daca la un moment dat, vreo unul dintre
     * domenii ajunge sa fie domeniul vid, atunci intoarce false. */
    for (int i = 0; i < s.length(); ++i) {
    }

    return true;
  }

  /* Functie care propaga constrangerile dupa completarea unui string s orizontal
   * incepand de la pozitia (row, col) din rebus. */
  static boolean propagateConstraints(Domains horizontal, Domains vertical)
  {
    /* Cel mai simplu de implementat este AC1, deoarece ne lipseste o reprezentare
     * determinista a domeniilor care se intersecteaza, iar cautarea suplimentara
     * ar aduce un cost de performanta destul de mare. Se poate implementa,
     * desigur, si AC3. */


    /* TODO: Trebuie sa parcurgeti cele doua multimi de domenii, orizontal si
     * vertical, si:
     *
     * a) Sa determinati care dintre ele se intersecteaza (de exemplu, un domeniu
     * orizontal care incepe de la (1, 2) si cel vertical care incepe de la (0, 2)
     * au in comun o litera in pozitia (1, 2).
     *
     * b) Pentru acele domenii care se intersecteaza, trebuie sa eliminati avele
     * posibilitati de cuvinte care nu au nici un corespondent posibil, de exemplu
     * daca cuvintele din domeniul orizontal sunt ('ANA', 'ARE' si 'MERE'), si
     * cele din domeniul vertical sunt ('RAM', si 'ART'), atunci se poate elimina
     * cuvantul 'MERE' din domeniul orizontal pentru ca nu exista nici o
     * restrictie care il acopere (nu exista nici un cuvant vertical cu 'M' in a
     * doua pozitie).
     *
     * Obs: In functie de algoritmul ales, veti restrange domeniile in diferite
     * moduri: simetric, doar pe cel vertical, iterativ sau printr-un singur
     * "pass".
     */ 
    return true;
  }

  static Domains copyDomains(Domains d, int rows, int columns)
  {
    Domains newD = new Domains();
    for(int i = 0; i < rows; i++)
    {
      for(int j = 0; j < columns; j++)
      {
        Position pos = new Position(i, j);
        newD.put(pos, copyVector(d.get(pos)));
      }
    }
    return newD;
  }

  static Vector<String> copyPrefixes(Vector<String> p)
  {
    return copyVector(p);
  }

  static Vector<String> copyVector(Vector<String> v)
  {
    Vector<String> newV = new Vector<String>();
    for(String s : v)
    {
      newV.add(s);
    }
    return newV;
  }

}

class Position
{
  public int first;
  public int second;

  Position(int first, int second)
  {
    this.first = first;
    this.second = second;
  }

  @Override
      public int hashCode() 
      {
        return first % 666013 + second % 666013;
      }

  @Override
      public boolean equals(Object obj) 
      {
        Position other = (Position)obj;
        return other.first == this.first && other.second == this.second;
      }
}

/* Vom modela o tabela de variable ca fiind un map de la o coordonata (i, j)
 * la o multime de cuvinte care ar putea ipotetic sa fie introduse in Rebus
 * incepand cu prima litera de la (i, j), fie in pozitie orizontala (spre
 * dreapta), fie in pozitie verticala (spre in jos). */
@SuppressWarnings("serial")
class Domains extends HashMap<Position, Vector<String>>
{
  public Domains(int rows, int columns)
  {
    super();

    /* Init void domains */
    for(int i = 0; i < rows; i++)
    {
      for(int j = 0; j < columns; j++)
      {
        this.put(new Position(i, j), new Vector<String>());
      }
    }
  }

  public Domains()
  {
    super();
  }
}

