import java.util.Arrays;
import java.util.Scanner;

public class Lab2p3 
{
  static int maxPlannedTasks(Task[] v)
  {
    /* Vom sorta vectorul crescator dupa timpul de finish. */
    Arrays.sort(v);

    /* Scanam de la stanga la dreapta (in ordinea crescatoare a timpului de
     * finish si alegem in mod greedy acele task-uri care nu se suprapun cu
     * ultimul task ales.
     *
     * Aceasta solutie ne conduce la optim pentru ca nu ne intereseaza in nici un
     * mod durata totala de timp cat robotul este ocupat, ci doar numarul de
     * task-uri indeplinite. Cu alte cuvinte, daca avem de ales intre doua
     * task-uri care se suprapun (partial sau total), il vom alege intotdeauna pe
     * cel care se termina mai devreme deoarece ne lasa mai mult loc de
     * planificare pentru viitor (si deoarece oricare dintre cele doua task-uri
     * este la fel de bun).
     */
    int return_value = 1;
    int last_completed = v[0].finish;
    for (int i = 1; i < v.length; ++i) 
    {
      if (v[i].start >= last_completed) 
      {
        return_value++;
        last_completed = v[i].finish;
      }
    }

    return return_value;
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
