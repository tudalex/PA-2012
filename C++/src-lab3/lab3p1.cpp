#include <iostream>
#include <string>
#include <vector>
#include <cstring>

#include "BooleanExpression.h"
using namespace std;
int count_modes(const std::vector<BooleanExpression::Lexem>& expr)
{
  /* TODO: Numarati modurile in care se pot pune paranteze paranteze in
   * expresie astfel incat sa se obtina rezultatul "true".
   *
   * OBS: Este obligatoriu sa puneti in expresie un numar de paranteze egal
   * cu numarul de operatori prezenti (adica parantezarea sa fie completa).
   *
   * OBS: Asa cum scrie si in documentatie, Lexem este un tip de enumerare ce
   * poate avea valorile: 
   *
   * BooleanExpression::True, 
   * BooleanExpression::False, 
   * BooleanExpression::Or, 
   * BooleanExpression::Xor, 
   * BooleanExpression::And 
   *
   * (sunt constante numerice, valoare reala nu este importanta).
   */
  int n = expr.size();
  vector < vector < vector < int > > > a (n, vector < vector < int > > (n, vector < int > (2, 0)));
  for (int i = 0; i < expr.size(); ++ i)
  {
	if (expr[i] == BooleanExpression::True)
		a[i][i][1] = 1;
	if (expr[i] == BooleanExpression::False)
		a[i][i][0] = 1;
  }
  
  for (int i = 3; i <= n; i+=2)
  {
  		for (int j = 0; j+i <= n; ++ j)
  			for (int k = j; k< i+j; ++ k)
  			{
  				if( expr[k] != BooleanExpression::True && expr[k] != BooleanExpression::False)
  				for (int op1 = 0; op1 < 2; ++ op1)
  					for (int op2 = 0; op2 < 2; ++ op2)
  					{
  						switch (expr[k]){
  							case BooleanExpression::Or : a[j][i+j-1][op1|op2]+=a[j][k-1][op1] * a[k+1][i+j-1][op2]; break;
  							case BooleanExpression::Xor : a[j][i+j-1][(op1^op2)&1]+=a[j][k-1][op1] * a[k+1][i+j-1][op2]; break;
  							case BooleanExpression::And : a[j][i+j-1][op1&op2]+=a[j][k-1][op1] * a[k+1][i+j-1][op2]; break;
  						}
  					}
  			}
  }
  return a[0][n-1][1];
}

int main()
{
  /* Citim si interpretam o expresie de la tastatura */
  BooleanExpression booleanExpression;
  std::cin >> booleanExpression;

  /* Daca este corecta gramatical, afisam rezultatul */
  if (booleanExpression.is_valid()){
    std::cout << "Numarul de moduri in care se poate obtine rezultatul "
        << "\"true\" este: \n\t"
        << count_modes(booleanExpression.to_vector()) << "\n";
  }

  return 0;
}

