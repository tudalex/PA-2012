import java.util.Vector;

public class Lab5p1 {
  public static void main(String[] args) {
    XOGame game = new XOGame(new SmartComputerAI(), new Human());
    game.runGame();
  }
}

class ScoreBoardPair {
  XOBoard board;
  int score;

  public ScoreBoardPair(int score, XOBoard board) {
    this.score = score;
    this.board = board;
  }
}

class SmartComputerAI implements IsAbleToPlay {
  private static final int INF = 10;

  private XOPlayer opponent(XOPlayer player) {
    if (player == XOPlayer.PlayerO)
      return XOPlayer.PlayerX;
    else
      return XOPlayer.PlayerO;
  }

  /**
   * Puteti folosi functia negaMax pentru a implementa un AI pe baza de
   * negaMAX.
   * 
   * Functia primeste ca parametri:
   * 
   * player = Jucatorul care trebuie sa mute in continuare (identitatea
   * calculatorului care gandeste cu aceasta functie). Valorile posibile sunt
   * { XOBoard::PlayerX, XOBoard::PlayerO }
   * 
   * board = Tabla pe care o vede jucatorul care trebuie sa mute in
   * continuare.
   * 
   * alpha = Inseamna ca player a gasit deja o cale prin care pot sa termin
   * jocul cu un scor cel putin egal cu alpha.
   * 
   * beta = Inseamna ca OPPONENT(player) a gasit o cale prin care sa-l forteze
   * pe player sa termine jocul cu un scor cel mult egal cu beta (cu alte
   * cuvinte daca player gaseste o modalitate sa castige mai mult de beta, cel
   * mai probabil analizeaza un scenariu nerealist in care a presupus ca
   * OPPONENT(player) a fost prost la un moment dat si a facut o greseala.
   */
  private ScoreBoardPair negaMax(XOPlayer player, XOBoard board, int alpha,
                                 int beta) {
    /* Daca s-a terminat jocul, scorul este cel raportat. */
    if (board.game_over()) {
      int myScore = board.get_score(player)
          - board.get_score(opponent(player));
      return new ScoreBoardPair(myScore, null);
    }

    /* Generam lista de expansiuni ale tablei (toate mutarile viitoare). */
    Vector<XOBoard> expansions = new Vector<XOBoard>();
    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 3; ++j) {
        if (board.is_empty(i, j)) {
          board.put(player, i, j);
          expansions.add(board.tryClone());
          board.erase(i, j);
        }
      }
    }

    /* Verificam care este mutarea cea mai inteleapta. */
    XOBoard nextMove = null;
    for (int i = 0; i < expansions.size(); ++i) {
      /*
       * Fiindca urmatorul nivel de negaMax este privit din partea
       * oponentului, cand apelez functia trebuie sa neg pe alfa si sa i-l
       * servesc drept beta pentru ca asta inseamna ca il "avertizez" ca
       * nu sunt fraier si ca deja stiu un mod prin care el nu poate sa
       * faca mai mult decat -alpha.
       * 
       * Pe de alta parte, desi eu il limitez pe el superior, din punct de
       * vedere inferior nu am nici un motiv sa-l limitez, asa ca ii voi
       * servi un alpha egal cu -INF (nu stiu cat de prost poate el sa
       * joace, n-am cum sa-mi dau seama).
       */

      /* Acum ne gandim cum s-ar descurca el in situatia asta. */
      ScoreBoardPair outcome = negaMax(opponent(player), expansions
                                       .elementAt(i).tryClone(), -INF, -alpha);

      /* Vedem ce scor a reusit sa scoata el in conditiile date. */
      int myScore = -outcome.score;

      /* Analizam jocul din perspectiva taierii alfa-beta. */
      if (myScore > beta) {
        /*
         * Inseamna ca asta e un scenariu in care el ar fi facut o
         * greseala. Noi stim ca el nu e prost, asa ca din moment ce a
         * gasit deja mai sus in arbore o modalitate prin care sa ma
         * faca sa termin jocul cu cel mult beta, n-o sa joace in asa
         * fel incat sa ma puna pe mine in situatia asta de acum.
         * Aplicam deci, taierea beta.
         */
        return new ScoreBoardPair(beta, nextMove);
      } else if (myScore > alpha) {
        /*
         * Inseamna ca tocmai am gasit o miscare prin care eu sa castig
         * la sigur mai mult decat stiam inainte ca pot sa castig (daca
         * vreti, un fel de plan "la sigur" mai bun).
         */
        alpha = myScore;
        nextMove = expansions.elementAt(i);
      }
    }

    /* Raportam mutarea aleasa ca fiind cea mai buna. */
    return new ScoreBoardPair(alpha, nextMove);
  }

  public XOBoard move(XOPlayer player, XOBoard board) {
    System.out.println("Computer is thinking...");
    return negaMax(player, board, -INF, +INF).board;
  }
}
