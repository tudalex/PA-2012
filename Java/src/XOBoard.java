import java.util.Scanner;

public class XOBoard 
{
  private int movesX;
  private int movesO;
  private int moves;

  private final int[] winConfigs = {
    0x007, //< Prima linie
    0x038, //< A doua linie
    0x1C0, //< A treia linie
    0x049, //< Prima coloana
    0x092, //< A doua coloana
    0x124, //< A treia coloana
    0x054, //< Diagonala secundara
    0x111  //< Diagonala principala
  };

  private static int pack(int x, int y)
  {
    return 3 * x + y;
  }

  /** \brief Constructor fara parametri care instantiaza o tabla goala pe
   * care nu este marcat nimic. */
  public XOBoard()
  {
    movesX = movesO = moves = 0;
  }

  public XOBoard(int movesX, int movesO, int moves)
  {
    this.moves = moves;
    this.movesO = movesO;
    this.movesX = movesX;
  }



  /** \brief Metoda care verifica daca o celula este libera sau nu.
   * \param x Linia X de pe tabla (intre 0 si 2)
   * \param y Coloana Y de pe tabla (intre 0 si 2)
   * \return Functia intoarce <b>true</b> daca celula este libera sau
   * <b>false</b> daca celula este completata.
   */
  public boolean is_empty(int x, int y) 
  {
    return (moves & (1 << pack(x, y))) == 0;
  }

  /** \brief Metoda care intoarce caracterul de pe tabla
   * \param x Linia X de pe tabla (intre 0 si 2)
   * \param y Linia Y de pe tabla (intre 0 si 2)
   * \return Dupa caz, intoarce </b>'X'</b>, <b>'O'</b> sau <b>'_'</b>
   */
  public char get(int x, int y) 
  {
    if (x < 0 || x > 2 || y < 0 || y > 2) 
    {
      System.err.println("ERROR! The cell at (" + x + "," + y + 
                         ") does not exist!\n   Ignoring GET operation!");
      return '\0';
    } 
    else 
    {
      return (moves & (1 << pack(x,y))) == 0 ?
          '_' :
          ((movesX & (1 << pack(x,y))) == 0 ? 'O' : 'X');
    }
  }

  /** \brief Metoda care bifeaza o celula de pe tabla in numele unui jucator
   * \param player Jucatorul in numele caruia se bifeaza
   * (valori constante din clasa XOBoard)
   * \param x Linia X de pe tabla (intre 0 si 2)
   * \param y Linia Y de pe tabla (intre 0 si 2)
   */
  public void put(XOPlayer player, int x, int y) {
    if (x < 0 || x > 2 || y < 0 || y > 2) {
      System.err.println("ERROR! The cell at (" + x + "," + y + 
                         ") does not exist!\n   Ignoring PUT operation!");
    } 
    else 
    {
      moves |= (1 << (pack(x,y)));
      if (player == XOPlayer.PlayerX){
        movesX |= (1 << (pack(x,y)));
      } else {
        movesO |= (1 << (pack(x,y)));
      }
    }
  }

  /** \brief Metoda care sterge valoarea dintr-o celula de pe tabla.
   * \param x Linia X de pe tabla (intre 0 si 2)
   * \param y Linia Y de pe tabla (intre 0 si 2)
   */
  public void erase(int x, int y) 
  {
    if (x < 0 || x > 2 || y < 0 || y > 2) {
      System.err.println("ERROR! The cell at (" + x + "," + y + 
                         ") does not exist!\n   Ignoring ERASE operation!");
    } else {
      moves &= ~(1 << (pack(x,y)));
      movesX &= ~(1 << (pack(x,y)));
      movesO &= ~(1 << (pack(x,y)));
    }
  }

  /** \brief Metoda care spune daca tabla s-a terminat de completat sau nu 
   * \return Functia intoarce <b>true</b> daca tabla este completata la maxim
   * sau <b>false</b> altfel.
   */
  public boolean is_full() 
  {
    return (moves & 0x1FF) == 0x1FF;
  }

  /** \brief Metoda care intoarce scorul tablei din perspectiva jucatorului
   * dat ca parametru.
   * \param player Jucatorul din perspectiva caruia se calculeaza scorul.
   * \return Functia intoarce numarul de linii, coloane si diagonale complete
   * ale jucatorului dat ca parametru.
   */
  public int get_score(XOPlayer player) 
  {
    int myMoves  = (moves & (player == XOPlayer.PlayerX ? movesX : movesO ));
    int score = 0;
    for (int i = 0; i < 8; i++){
      if ((myMoves & winConfigs[i]) == winConfigs[i]) {
        score++;
      }
    }
    return score;
  }

  /** \brief Metoda care spune daca jocul s-a terminat. 
   * \return Functia intoarce <b>true</b> daca jocul s-a terminat sau
   * <b>false</b> daca se poate juca in continuare.
   */
  public boolean game_over() 
  {
    return is_full();
  }

  public void print()
  {
    for(int i = 0; i < 3; i++)
    {
      for(int j = 0; j < 3; j++)
      {
        System.out.print("" + this.get(i, j) + " ");
      }
      System.out.println();
    }
  }

  @Override
      public XOBoard clone() throws CloneNotSupportedException 
      {
        return new XOBoard(movesX, movesO, moves);
      }

  public XOBoard tryClone()
  {
    XOBoard newBoard = null;
    try {
      newBoard = this.clone();
    } catch (Exception e) { e.printStackTrace(); }
    return newBoard;
  }
}

enum XOPlayer
{
  PlayerX, 
  PlayerO
}

class XOGame
{
  private IsAbleToPlay playerX;
  private IsAbleToPlay playerO;

  public XOGame(IsAbleToPlay playerX, IsAbleToPlay playerO)
  {
    this.playerX = playerX;
    this.playerO = playerO;
  }

  public boolean stillPlayable(XOBoard board)
  {
    return !(board.is_full() || 
             board.get_score(XOPlayer.PlayerO) > 0 ||
             board.get_score(XOPlayer.PlayerX) > 0);
  }

  public void runGame()
  {
    System.out.println("Game started.");

    /* create empty board */
    XOBoard board = new XOBoard();

    do
    {
      board = playerX.move(XOPlayer.PlayerX, board);
      board.print();
      if(!stillPlayable(board)) break;
      board = playerO.move(XOPlayer.PlayerO, board);
      board.print();
      if(!stillPlayable(board)) break;
    }
    while(true);

    if(board.get_score(XOPlayer.PlayerX) > 0)
    {
      System.out.println("Player X wins.");
    }
    else if(board.get_score(XOPlayer.PlayerO) > 0)
    {
      System.out.println("Player O wins.");
    }
    else if(board.is_full())
    {
      System.out.println("Game over, nobody wins.");
    }
  }
}

interface IsAbleToPlay
{
  public XOBoard move(XOPlayer player, XOBoard board);
}

class DumbComputerAI implements IsAbleToPlay
{
  public XOBoard move(XOPlayer player, XOBoard board)
  {
    int x = 0, y = 0;
    System.out.println("Computer: ");
    for(int pos = 0; pos < 9; pos++)
    {
      x = pos / 3;
      y = pos % 3;
      if(board.is_empty(x, y))
      {
        break;
      }
    }

    XOBoard newBoard = null;
    try {
      newBoard = board.clone();
    } catch (Exception e) { e.printStackTrace(); }

    newBoard.put(player, x, y);
    return newBoard;
  }
}

class Human implements IsAbleToPlay
{
  public XOBoard move(XOPlayer player, XOBoard board)
  {
    int x, y;
    Scanner scanner = new Scanner(System.in);

    System.out.print("Your move: ");
    x = scanner.nextInt();
    y = scanner.nextInt();
    System.out.println();

    XOBoard newBoard = null;
    try {
      newBoard = board.clone();
    } catch (Exception e) { e.printStackTrace(); }

    newBoard.put(player, x, y);
    return newBoard;
  }
}
