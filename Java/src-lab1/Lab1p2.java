public class Lab1p2 
{
  public static int find_kth_smallest(int[] v, int k)
  {
    /* TODO: functie care returneaza al k-lea element in ordine crescatoare din
     * vectorul primit ca parametru. */
    return 0;
  }

  public static void main(String[] args)
  {
    /* Declaram si citim un vector de numere intregi de la tastatura. */
    int[] v = VectorUtil.readArrayOfIntegers();

    /* Afisam elementul care se afla pe pozitia mediana in vectorul sortat. */
    System.out.println("Elementul median din multime este: " +
                       find_kth_smallest(v, v.length / 2));
  }

}
