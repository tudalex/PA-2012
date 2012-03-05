import java.util.Scanner;
import java.util.Vector;

public class VectorUtil
{
  @SuppressWarnings({ "unchecked", "rawtypes" })
      public static <T> T[] readArrayOfReadables(T[] vectorHint, Class classType)
      {
        Vector<T> v = new Vector<T>();
        Scanner inputScanner = new Scanner(System.in);
        int n = inputScanner.nextInt();
        for(int i = 0; i < n; i++)
        {
          Readable r = null;
          try 
          {
            r = (Readable) classType.newInstance();
          } 
          catch (Exception e) 
          {
            e.printStackTrace();
          }
          r.read(inputScanner);
          v.add((T)r);
        }
        return v.toArray(vectorHint);
      }

  public static int[] readArrayOfIntegers()
  {
    Scanner inputScanner = new Scanner(System.in);
    int n = inputScanner.nextInt();
    int[] v = new int[n];
    for(int i = 0; i < n; i++)
    {
      v[i] = inputScanner.nextInt();
    }
    return v;
  }
}
