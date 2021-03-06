#include <iostream>
#include <vector>
#include <string>
#include <cstring>
#include <algorithm>
#include "VectorIO.h"
using namespace std;
void greedy_format(unsigned int l, std::vector<std::string>& v)
{
  std::cout << "Greedy format:" << std::endl;
  unsigned int cost = 0;
  cout << l<<endl;
  /* TODO: Calculati costul formatarii Greedy si afisati textul formatat
   * folosind aceasta strategie. Afisati cate "l" caractere pe o linie, urmate de
   * un caracter "|" inainte de terminatorul de linie. */
  int k = 0;
  for (int i = 0; i < v.size(); ++ i)
  {
  	if (v[i].size() + k > l)
  	{
  		k-=1;
  		cout<<endl;
  		cost += (l-k)*(l-k);
  		k = 0;
  		cout<<cost<<endl;
  	}
  	cout <<v[i]<<" ";
  	k+=v[i].size()+1;
  }
  --k;
  cost+=(l-k)*(l-k);
  /* Afisam costul. */
  std::cout << "TOTAL COST: " << cost << std::endl;
}

void tex_format(unsigned int l, std::vector<std::string>& v)
{
  std::cout << "Tex format:" << std::endl;

  /* TODO: Calculati costul formatarii Tex si afisati textul formatat
   * folosind aceasta strategie. Afisati cate "l" caractere pe o linie, urmate de
   * un caracter "|" inainte de terminatorul de linie. */
  vector < int > a (v.size()+1, 1<<30);
  vector < int > sum (v.size()+1, 0);
  for (int i = 0; i < v.size(); ++ i)
  	sum[i+1]=sum[i]+v[i].size()+1;
  a[0] = 0;
  
  for (int i = 1; i <= v.size(); ++ i)
   	for (int j = 0; j < i; ++ j)
  		if (sum[i-1]-sum[j]+v[i-1].size() <= l)
  			a[i] = min(a[i],a[j]+ (int)(l-(sum[i-1]-sum[j]+(int)v[i-1].size()))*(int)(l-(sum[i-1]-sum[j]+(int)v[i-1].size())));
  int r = 1<<30;
  int n = v.size();
  for (int i = n; i >= 0; --i)
  	r = min(r, a[i] + (int)(l-sum[n]+sum[i])*(int)(l-sum[n]+sum[i]));
  /* Afisam costul total. */
  cout <<sum<<endl;
  cout << a<<endl;
  std::cout << "TOTAL COST: " << a[n] << std::endl;
}

int main()
{
  /* Declaram si citim lungimea unei linii si un vector de cuvinte. */
  unsigned int l;
  std::vector<std::string> word;
  std::cin >> l >> word;

  /* Verificam sa nu avem cuvinte mai lungi de o linie. */
  for (unsigned int i = 0; i < word.size(); ++i) {
    if (word[i].length() > l) {
      std::cerr << "Imposibil. Anumite cuvinte sunt mai lungi de o linie!"
          << std::endl;
      return 0;
    }
  }

  /* Afisam impartirea greedy. Se vor afisa L caractere pe o linie, urmate de un
   * caracter pipe ('|') la final. */
  greedy_format(l, word);
  std::cout << std::endl;

  /* Afisam impartirea folosind functia de cost din TeX. Se vor afisa L
   * caractere pe o linie, urmate de un caracter pipe ('|') la final.*/
  tex_format(l, word);

  return 0;
}

