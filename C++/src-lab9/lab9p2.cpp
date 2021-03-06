	#include <iostream>
#include <fstream>
#include <ctime>
#include <cstdlib>
#include <algorithm>
#include <vector>
#include <queue>
#include "Maze.h"
#include "DisjointSets.h"
const int vi[4] = { -1, 0, 1, 0 };
const int vj[4] = { 0, 1, 0, -1 };
using namespace std;
typedef pair < int, pair < int, int > > p;


void dig_out_maze(Maze& maze, int line, int column) {
  /* TODO: Sapati galerii prin maze astfel incat:
   * (a) orice celula dintr-o galerie sa aiba acces la exterior.
   * (b) galeriile sa fie "inguste" (adica sa nu se formeze camere de 2x2).
   */
  
  int n = maze.get_height();
  int m = maze.get_width();
  DisjointSets tree_set(n*m);
  vector < vector < int > > v(n, vector < int > (m));
  vector < vector < int > > id(n, vector < int > (m));
  int k = 0;
  for (int i = 0; i < n; ++i)
  	for (int j = 0; j < m; ++ j)
  	{
  		v[i][j] = rand()%1000;
			id[i][j] = k++;
		}
  
  std::priority_queue < p, vector < p >, greater <p> > q;
  q.push(make_pair(0,make_pair(line, column)));
  while (!q.empty())
  {
		int cost = q.top().first;
		int x = q.top().second.first;
		int y = q.top().second.second;
		q.pop();
		if (maze.is_walkable(x, y) || maze.is_corner(x,y))
			continue;
		//Verificare de cicluri
		int good = 1;
		for (int i = 0; i < 4;++i)
			for (int j = 0; j < 4; ++ j)
			if (i!=j)
			{
				int x1 = x+vi[i];
				int y1 = y+vj[i];
				int x2 = x+vi[j];
				int y2 = y+vj[j];
				if (!maze.is_walkable(x1,y1) || !maze.is_walkable(x2,y2))
					continue;
				if (tree_set.same_set(id[x1][y1], id[x2][y2]))
					good = 0;
				
			}
		
		if (!good)
			continue;
		maze.mark_solution_step(x,y);
			
			
		
		//cerr<<"am marcat "<<x<<" "<<y<<endl;
		
		for (int i= 0; i <4; ++ i)
		{
			int xx = x+vi[i];
			int yy = y+vj[i];
			
			if (xx >= 0 && yy>=0 && xx <n && yy < m ) 
			{
				if (maze.is_walkable(xx,yy))
					tree_set.merge_sets_of(id[x][y], id[xx][yy]);
				else if (!maze.is_corner(xx,yy))
					q.push(make_pair(v[xx][yy]+cost, make_pair(xx, yy)));
			}
		}
	}    
}

void dig_out_maze(Maze& maze)
{
  /* Initializam un generator de numere aleatoare. */
  srand(time(NULL));

  /* Oservatie: Indiferent de la ce algoritm porniti cu ideea, urmariti sa nu
   * stricati "colturile" din labirint.
   *
   *    ####@ @## #
   *    ##     @@ #
   *    #####@    #
   *    ###########
   *
   * Zidurile notate cu "@" nu trebuie daramate, ca sa nu creati camere 2x2 din
   * coridoare. Am vrea ca in final sa obtinem un labirint "sculptat" aleator,
   * cu coridoare de latime 1 si fara camere patratice.
   */

  /* Fie o celula random de pe perimetru de la care sa incepem, de exemplu
   * (0, 1). */
  dig_out_maze(maze, 0, 1);
}

int main()
{
  int width, height;

  /* Citeste dimensiunile labirintului si creaza o parcela de pamant de
   * dimensiunile respective. */
  std::cin >> width >> height;
  Maze maze(width, height);

  /* Sapa un labirint din respectiva parcela de pamant. */
  dig_out_maze(maze);

  /* Si afiseaza-l. */
  std::cout << "The resulting maze is:" << std::endl << maze;
  return 0;
}

