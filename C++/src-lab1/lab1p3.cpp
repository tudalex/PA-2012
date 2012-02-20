#include <iostream>

#include "VectorIO.h"
#include <stdio.h>

#define MAX(a,b) ((a)<(b)?(b):(a))

void subsec(std::vector<int>& v,int l, int r, int &p, int &s, int &suma, int &sub)
{
	
	if (l == r)
	{
		suma = sub = p = s = v[l];
		fprintf(stderr, "l=%d r=%d\np=%d s=%d suma=%d sub=%d\n",l, r, p, s, suma, sub);
		return;
	}
	int m = (l+r)/2;
	int p1, p2, s1, s2, suma1, suma2, sub1, sub2;
	subsec(v,l, m, p1, s1, suma1, sub1);
	subsec(v,m+1, r, p2, s2, suma2, sub2);
	p = MAX(p2+suma1, p1);
	s = MAX(s1+suma2, s2);
	suma = suma1+suma2;
	sub = MAX(MAX(s1+p2,suma), MAX(sub1, sub2));
	fprintf(stderr, "l=%d r=%d\np=%d s=%d suma=%d sub=%d\n",l, r, p, s, suma, sub);
} 
int max_sum_subsequence(std::vector<int>& v)
{
  /* Functie care intoarce suma maxima a unei subsecvente din vectorul primit
   * ca parametru folosind o abordare de tipul Divide & Impera (se impune o
   * complexitate din clasa O(N)). */
  int p, s, suma, sub;
  subsec(v,0, v.size()-1,p, s, suma, sub);
  return MAX(MAX(sub,suma), MAX(p, s));
}

int main()
{
  /* Declaram si citim un vector de numere intregi. */
  std::vector<int> v;
  std::cin >> v;

	/* Afisam rezultatul. */
	std::cout << "Subsecventa de suma maxima din vector are suma = "
            << max_sum_subsequence(v) << std::endl;

	return 0;
}

