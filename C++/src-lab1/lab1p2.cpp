#include <iostream>
#include <stdlib.h>
#include "VectorIO.h"
#include <ctime>
#include <cstdio>
int part(std::vector<int>& v, int k, int l, int r);
int find_kth_smallest(std::vector<int>& v, int k)
{
  /* TODO: functie care returneaza al k-lea element in ordine crescatoare din
   * vectorul primit ca parametru. */
   
  return part(v, k-1, 0, v.size()-1);
}
int part(std::vector<int>& v, int k, int l, int r)
{
	fprintf(stderr, "k=%d l=%d r=%d\n",k, l, r);
	if (l == r)
		return v[r];
	int i = l , ii = 0;
	int j = r , ji = -1;
	while ( i < j)
	{
		if(v[i] > v[j])
		{
			int t = v[i];
			v[i] = v[j];
			v[j] = t;
			t = ii;
			ii = ji;
			ji = t;
			ii *=-1;
			ji *=-1;
		}
		i+=ii; j+=ji;
	}
	if (k > i)
		return part(v, k, i+1, r);
	else
		return part(v, k, l, i);
	
	
}
int main()
{
  /* Declaram si citim un vector de numere intregi de la tastatura. */
  std::vector<int> v;
  std::cin >> v;
  srand(time(NULL));
  /* Afisam elementul care se afla pe pozitia mediana in vectorul sortat. */
  std::cout << "Elementul median din multime este: "
            << find_kth_smallest(v, v.size()/2) << std::endl;

	return 0;
}

