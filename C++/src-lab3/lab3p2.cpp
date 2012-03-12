#include <iostream>
#include <vector>
#include <cstring>

#include "VectorIO.h"
#include "PairIO.h"
using namespace std;
/* Vom considera o matrice ca o pereche de intregi (nr_linii, nr_coloane). */
typedef std::pair<int, int> Matrice;
const int inf = 1000000;
int min_multiplications(std::vector<Matrice>& v)
{
  /* Tiparim efectiv lantul parantezat si intoarcem numarul minim de operatii de
   * inmultire elementare. */
  int n = v.size();
  vector < vector < int > > a(n, vector < int > (n, inf));
  for (int i = 1; i <= n; ++ i)
  {
  	for (int j = 0; j+i <= n; ++ j)
  	if (i > 1)
  	{
  		for (int k = j; k < i+j-1; ++ k)
  			a[j][i+j-1] = min (a[j][i+j-1], a[j][k] + a[k+1][i+j-1]+v[j].first*v[k].second*v[i+j-1].second);
  	}
  	else
  		a[j][j] = 0;
  	
  }		
  return a[0][n-1];
}

int main()
{
  /* Declaram si citim un vector de matrice de la consola. */
  std::vector<Matrice> v;
  std::cin >> v;

  /* Verificam intai ca lantul de matrice chiar se poate inmulti. */
  for (unsigned int i = 0; i < v.size() - 1; ++i) {
    if (v[i].second != v[i + 1].first) {
      std::cerr << "Fail! Nu se pot inmulti matricele " << i << " si "
          << (i + 1) << " de dimensiuni: " << v[i] << ", respectiv "
          << v[i + 1] << std::endl;
      return 0;
    }
  }

  /* Afisam numarul minim de operatii. */
  std::cout << "Numarul minim de operatii de inmultire elementare este: "
      << min_multiplications(v) << std::endl;

  return 0;
}

