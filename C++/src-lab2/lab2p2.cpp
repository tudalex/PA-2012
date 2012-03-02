#include <iostream>
#include <vector>

#define CEIL(a, b) ((a) / (b) + ((a) % (b) ? 1 : 0))

/* Modelam o fractie ca fiind o pereche (numarator, numitor). */
typedef std::pair<long long, long long> Fractie;

std::vector<Fractie> descompunere_fractii_egiptene(long long x, long long y)
{
  std::vector<Fractie> v;

  /* TODO: Puneti in v un sir de fractii care sa reprezinte descompunerea in
   * fractii egiptene a lui x/y. */

  return v;
}

int main()
{
  /* Citim x si y. */
  long long x, y;
  std::cin >> x >> y;

  /* Calculam descompunerea in fractii egiptene. */
  std::vector<Fractie> v;
  v = descompunere_fractii_egiptene(x, y);

  /* Si o afisam. */
  std::cout << x << "/" << y << " = ";
  for (unsigned int i = 0; i < v.size(); ++i) {
    std::cout << (i > 0 ? " + " : "") << v[i].first << "/" << v[i].second;
  }
  std::cout << std::endl;

  return 0;
}

