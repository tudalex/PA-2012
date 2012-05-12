#include <iostream>

#include "Nim.h"

#define UNDEF -1


int negaMax(NimGameConf& game, int depth) {
  /* Daca s-a terminat jocul, atunci scorul este 0. */
  if (game.gameOver()) {
    return 0;
  }
  int scor = -1, x, k;
  
  for (int i = 3; i <= game.size(); ++ i)
  		if (game[i] > 0 )
	  	for (int k = 1; k <= i/2; ++ k)
	  		{
	  			game.split(i, k, i-k);
	  			//std::cerr<<"i: "<<i<<" Depth: "<<depth<<std::endl<<game<<std::endl;
	  			int ans = 1-negaMax(game, depth+1);
	  			if (ans)
	  			{
	  				if (depth!=0)
	  					game.unsplit(i, k, i-k);
	  				return 1;
	  			}
	  			game.unsplit(i, k, i-k);
	  		} 
  /* TODO: Altfel, fa o miscare pe tabla astfel incat sa castigi. Stergeti linia
   * de mai jos cand rezolvati, ea inseamna ca nu se face nici o miscare, si ca
   * AI-ul spune ca a pierdut deja. */
  if (depth == 0)
  	game.split(game.size(), game.size()-1, 1); 
  //return 0;
  return 0;
}

void computerPlayer(NimGameConf& game) {
  std::cout << game << "Computer starts thinking... ";
  negaMax(game, 0);
  std::cout << "done." << std::endl;
}

void humanPlayer(NimGameConf& game) {
  std::cout << game << "Alege o gramada de impartit, precum si dimensiunile"
      << " in care vrei sa spargi gramada: ";
  int heap, a, b;
  do {
    /* Citim o miscare de la consola. */
    std::cin >> heap >> a >> b;
    if (game[heap] == 0) {
      /* Verificam sa existe o gramada de atatea pietricele. */
      std::cout << "Error! Nu exista nici o gramada de " << heap
          << " pietricele. Try again: ";
    } else if (heap != a + b || a <= 0 || b <= 0) {
      /* Verificam ca miscarea sa fie valida. */
      std::cout << "Error! O gramada de " << heap << " pietricele nu se "
          << "poate imparti in " << a << " + " << b << " pietricele."
          << std::endl << "Try again: ";
    } else if (heap >= 0 && heap <= 2) {
      /* Alta verificare de miscare valida. */
      std::cout << "Error! Nu se poate imparti o gramada de " << heap
          << " pietricele in gramezi mai mici. Try again: ";
    } else {
      /* Efectuam miscarea si iesim din functie. */
      game.split(heap, a, b);
      return;
    }
  } while(1);
}

int main()
{
  /* Citim un numar initial de pietricele si pornim un joc. */
  int n;
  std::cout << "Game size: ";
  std::cin >> n;
  NimGameConf game(n);

  do {
    /* First player takes a move. */
    computerPlayer(game);
    std::cout << std::endl;
    if (game.gameOver()) {
      std::cout << std::endl << "Player 1 wins!" << std::endl;
      break;
    }

    /* Second player takes a move. */
    humanPlayer(game);
    std::cout << std::endl;
    if (game.gameOver()) {
      std::cout << std::endl << "Player 2 wins!" << std::endl;
      break;
    }
  } while(1);

  return 0;
}

