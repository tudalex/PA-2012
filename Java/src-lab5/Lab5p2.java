import java.util.Scanner;

public class Lab5p2 {
  private static Scanner inputScanner = new Scanner(System.in);

  /*
   * In cazul acestui joc, scorurile sunt binare: 0/1 sau WIN/LOSE. Cu alte
   * cuvinte, nu exista un scor anume care sa fie castigat, ci doar intreg
   * jocul. Astfel, algoritmul de taiere alfa-beta se reduce la un caz
   * particular:
   * 
   * Daca oponentul pierde, atunci mutarea respectiva duce sigur la castig si
   * nu mai are sens sa expandam in continuare arborele (putem taia toti
   * subarborii care urmeaza in expansiune).
   * 
   * In continuare am dat o implementare de tipul nega-max care include si
   * prunning-ul alfa-beta.
   */
  static int negaMax(NimGame game, int depth) {
    /* Daca s-a terminat jocul, atunci scorul este 0. */
    if (game.gameOver()) {
      return 0;
    }

    /* Alegem o configuratie in care celalalt sa aiba solutie de pierdere. */
    for (int i = 3; i <= game.size(); ++i) {
      if (game.get(i) > 0) {
        /*
         * Daca se poate sparge gramada asta, atunci incercam sa o
         * spargem.
         */
        for (int j = 1; j <= i / 2; ++j) {
          /*
           * Aflam scorul pe care l-ar obtine inamicul dupa aceasta
           * spargere.
           */
          game.split(i, j, i - j);
          int opponentScore = negaMax(game, depth + 1);
          /*
           * Daca el obtine 0, inseamna ca noi am castigat si ne putem
           * opri.
           */
          if (opponentScore == 0) {
            /*
             * Optimizarea alfa-beta: deoarece am gasit o miscare
             * prin care jucatorul advers va pierde cu siguranta, nu
             * mai are sens sa cautam mai departe.
             * 
             * Mai mult, daca adancimea este 0, nu mai facem "undo"
             * miscarii, pentru ca de fapt asta este miscarea pe
             * care o efectueaza AI-ul. Daca in schimb adancimea
             * este > 0, atunci trebuie sa ii facem "undo" pentru ca
             * noi predam inapoi tabla de joc pe care sa se afle
             * doar urmatoarea mutare, nu mai multe.
             */
            if (depth > 0) {
              game.unsplit(i, j, i - j);
            }
            return 1;
          } else {
            /* Altfel, retragem miscarea si incercam cu altceva. */
            game.unsplit(i, j, i - j);
          }
        }
      }
    }
    /* Inseamna ca nu am gasit strategie sigura de castig. */
    if (depth == 0) {
      /*
       * Daca adancimea este 0, inseamna ca trebuie sa lasam tabla de joc
       * cu o mutare efectuata pe ea. Fiindca nu am gasit strategie sigura
       * de castig, putem alege orice mutare (prima posibila), pentru ca
       * oricum toate duc sigur la pierdere.
       */
      for (int i = 3; i <= game.size(); ++i) {
        if (game.get(i) > 0) {
          game.split(i, 1, i - 1);
          return 0;
        }
      }
      System.out.println("Trouble!");
      return -1; /* needed by javac */
    } else {
      /*
       * Adancimea nefiind 0, nu trebuie sa facem o miscare. Pur si simplu
       * raportam ca pe ramura asta de joc se pierde intotdeauna.
       */
      return 0;
    }
  }

  static void computerPlayer(NimGame game) {
    game.print();
    System.out.print("Computer starts thinking... ");
    negaMax(game, 0);
    System.out.println("done");
  }

  static void humanPlayer(NimGame game) {
    game.print();
    System.out.println("Alege o gramada de impartit, "
                       + "precum si dimensiunile in care vrei sa spargi gramada: ");
    int heap, a, b;
    do {
      /* Citim o miscare de la consola. */
      heap = inputScanner.nextInt();
      a = inputScanner.nextInt();
      b = inputScanner.nextInt();

      if (game.get(heap) == 0) {
        /* Verificam sa existe o gramada de atatea pietricele. */
        System.out.println("Eroare! Nu exista nici o gramada de "
                           + heap + " pietricele.");
      } else if (heap != a + b || a <= 0 || b <= 0) {
        /* Verificam ca miscarea sa fie valida. */
        System.out.println("Eroare! O gramada de " + heap
                           + " pietricele nu se " + "poate imparti in " + a
                           + " + " + b + " pietricele.");
      } else if (heap >= 0 && heap <= 2) {
        /* Alta verificare de miscare valida. */
        System.out.println("Eroare! Nu se poate imparti o gramada de "
                           + heap + " pietricele in gramezi mai mici.");
      } else {
        /* Efectuam miscarea si iesim din functie. */
        game.split(heap, a, b);
        return;
      }
      System.out.println("Incearca din nou: ");
    } while (true);
  }

  public static void main(String[] args) {
    /* Citim un numar initial de pietricele si pornim un joc. */
    System.out.println("Game size: ");

    int n = inputScanner.nextInt();
    NimGame game = new NimGame(n);

    do {
      /* First player takes a move. */
      computerPlayer(game);
      System.out.println();
      if (game.gameOver()) {
        System.out.println();
        System.out.println("Player 1 wins!");
        break;
      }

      /* Second player takes a move. */
      humanPlayer(game);
      System.out.println();
      if (game.gameOver()) {
        System.out.println();
        System.out.println("Player 2 wins!");
        break;
      }
    } while (true);
  }
}
