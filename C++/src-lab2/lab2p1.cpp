#include <iostream>
#include <vector>
#include <algorithm>

#include "VectorIO.h"
#include "PairIO.h"

/* Un material este o pereche (greutate, valoare). */
typedef std::pair<int, double> Material;

double val_max(int t, std::vector<Material>& v)
{
  /* TODO: Valoarea maxima care se poate transporta. */
  return 0;
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

