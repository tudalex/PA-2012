import java.util.Arrays;
import java.util.Scanner;

public class Lab2p3 
{
  static int maxPlannedTasks(Task[] v)
  {
    /* TODO: Calculati numarul maxim de task-uri care nu se suprapun. */
    return 0;
  }

  public static void main(String[] args)
  {
    /* Declaram si citim vectorul de task-uri. */
    Task[] v = VectorUtil.readArrayOfReadables(new Task[0], Task.class);

    /* Afisam numarul maxim de task-uri pe care le poate indeplini robotul. */
    System.out.println("Nr maxim de task-uri care nu se suprapun: " + maxPlannedTasks(v));
  }
}

class Task implements Readable, Comparable<Task>
{
  int start;
  int finish;

  public void read(Scanner scanner)
  {
    start = scanner.nextInt();
    finish = scanner.nextInt();
  }

  @Override
      public int compareTo(Task other) 
      {
        if(this.finish < other.finish) return -1;
        return 1;
      }
}
