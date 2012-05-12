public class NimGame {
  private int n;
  private int[] heaps;

  private void initHeaps() {
    for (int i = 0; i < n; i++)
      this.heaps[i] = 0;
    heaps[n] = 1;
  }

  public NimGame(int n) {
    this.n = n;
    this.heaps = new int[n + 1];
    initHeaps();
  }

  public int size() {
    return n;
  }

  public boolean gameOver() {
    for (int i = 3; i < heaps.length; i++) {
      if (heaps[i] > 0) {
        return false;
      }
    }
    return true;
  }

  public int get(int index) {
    return heaps[index];
  }

  public void split(int heap, int a, int b) {
    if (heap > 2 && heap <= n && heaps[heap] > 0 && a > 0 && b > 0
        && heap == a + b) {
      heaps[heap]--;
      heaps[a]++;
      heaps[b]++;
    }
  }

  public void unsplit(int heap, int a, int b) {
    if (heap > 2 && heap <= n && a > 0 && b > 0 && heaps[a] > 0
        && heaps[b] > 0 && heap == a + b) {
      heaps[heap]++;
      heaps[a]--;
      heaps[b]--;
    }
  }

  public void print() {
    System.out.println("Gramezile:");
    System.out.print("\t");
    for (int i = 1; i < 3; ++i) {
      for (int size = heaps[i]; size > 0; --size) {
        System.out.print(" (" + i + ")");
      }
    }
    for (int i = 3; i < heaps.length; ++i) {
      for (int size = heaps[i]; size > 0; --size) {
        System.out.print(" " + i);
      }
    }
    System.out.println();
  }
}
