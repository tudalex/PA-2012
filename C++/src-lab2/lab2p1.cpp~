
#include <iostream>
#include <vector>
#include <algorithm>

#include "VectorIO.h"
#include "PairIO.h"
using namespace std;

/* Un material este o pereche (greutate, valoare). */
typedef std::pair<int, double> Material;
 int cmpf(const Material& a, const Material& b)
{
	if (a.second == b.second)
		return a.first > b.first;
	return a.second > b.second;
}
double val_max(int t, std::vector<Material>& v)
{
  /* TODO: Valoarea maxima care se poate transporta. */
  double r = 0;
  sort(v.begin(), v.end(), cmpf);
  for (int i = 0; i < v.size(); ++ i)
  {
  	int k = min(t, v[i].first);
  	r+= k * v[i].second;
  	t-= k;
  }
  return r;
}

int main()
{
  /* Declaram capacitatea camionului si un vector care sa retina tipurile
   * de material sub forma de perechi (greuate, valoare) si citim datele
   * de intrare.
   */
  int t;
  std::vector<Material> v;
  std::cin >> t >> v;

  /* Afisam valoarea maxima transportabila de catre camion. */
  std::cout << "Valoarea maxima a unui transport: "
      << val_max(t, v) << std::endl;

  return 0;
}

