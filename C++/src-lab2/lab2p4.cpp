#include <iostream>
#include <vector>
#include <algorithm>

#include "VectorIO.h"
#include "PairIO.h"

#define UNDEF -1

/* Un material este o pereche (greutate, valoare). */
typedef std::pair<int, int> Mobila;

int val_max(int t, std::vector<Mobila>& v)
{
  /* TODO: Caclulati valoarea maxima transportabila de catre camionul de
   * capacitate t. */
  return 0;
}

int main()
{
  /* Declaram capacitatea camionului si un vector care sa retina tipurile de
   * mobila sub forma de perechi (greutate, valoare) si citim datele de intrare.
   */
  int t;
  std::vector<Mobila> v;
  std::cin >> t >> v;

  /* Afisam valoarea maxima transportabila de catre camion. */
  std::cout << "Valoarea maxima a mobilierului transportabil: "
            << val_max(t, v) << std::endl;

  return 0;
}

