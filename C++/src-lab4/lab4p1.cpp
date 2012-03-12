#include <iostream>
#include <fstream>
#include <cstdio>
#include <cstring>
#include <queue>
#include <sys/time.h>
#include <map>

#include "VectorIO.h"
#include "PairIO.h"
#include "MapIO.h"

#include "Rebus.h"

/* Vom modela o tabela de variable ca fiind un map de la o coordonata (i, j)
 * la o multime de cuvinte care ar putea ipotetic sa fie introduse in Rebus
 * incepand cu prima litera de la (i, j), fie in pozitie orizontala (spre
 * dreapta), fie in pozitie verticala (spre in jos). */
typedef std::pair<int, int> Position;
typedef std::map<Position, std::vector<std::string> > Domains;

/* Numarul de intrari in recursivitate (pentru evaluarea performantelor). */
unsigned int recursions = 0;

/* Cuvintele disponibile in dictionar (ca vector de stringuri). */
std::vector<std::string> vocabular;

/* Obiectul Rebus in sine. Are doar cateva metode ajutatoare gata scrise,
 * verificati documentatia pentru detalii. */
Rebus rebus;

/* Functie care umple cele doua tabele de restrictii, orizontala sau verticala
 * cu cuvinte care ar putea, ipotetic, sa fie completate in Rebus. */
void build_word_sets(Domains& horizontal, Domains& vertical)
{
  /* Si completam. */
  for (unsigned int i = 0; i < rebus.rows; ++i) {
    for (unsigned int j = 0; j < rebus.columns; ++j) {
      if (rebus.is_empty(i, j) &&
          (j == 0 || rebus.is_empty(i, j - 1) == false)) {
        /* TODO: Puneti in horizontal, la pozitia (i, j), toate cuvintele care
         * ar putea ipotetic sa fie completate incepand de acolo spre dreapta. */
      }
      if (rebus.is_empty(i, j) &&
          (i == 0 || rebus.is_empty(i - 1, j) == false)) {
        /* TODO: Puneti in vertical, la pozitia (i, j), toate cuvintele care ar
         * putea ipotetic sa fie completate incepand de acolo in jos. */
      }
    }
  }
}

/* Functie care verifica daca avem blocare. */
bool verifica_inainte(int row,
                      int col,
                      std::string& s,
                      Domains& horizontal,
                      Domains& vertical,
                      std::vector<std::string>& prefixes) {
  /* Reducem domeniul orizontal la s - din moment ce tocmai am completat "s"
   * incepand de la (row, col) spre dreapta, atunci domeniul se colapseaza la
   * acest string. */
  horizontal[Position(row, col)].clear();
  horizontal[Position(row, col)].push_back(s);

  /* TODO: Parcurgem prefixele nou modificate si stergem din domeniul vertical tot ce
   * nu se potriveste cu prefixele. Daca la un moment dat, vreo unul dintre
   * domenii ajunge sa fie domeniul vid, atunci intoarce false. */
  for (unsigned int i = 0; i < s.length(); ++i) {
  }

  return true;
}

/* Functie care propaga constrangerile dupa completarea unui string s orizontal
 * incepand de la pozitia (row, col) din rebus. */
bool propagate_constraints(Domains& horizontal, Domains& vertical)
{
  /* Cel mai simplu de implementat este AC1, deoarece ne lipseste o reprezentare
   * determinista a domeniilor care se intersecteaza, iar cautarea suplimentara
   * ar aduce un cost de performanta destul de mare. Se poate implementa,
   * desigur, si AC3 */

  /* TODO: Trebuie sa parcurgeti cele doua multimi de domenii, orizontal si
   * vertical, si:
   *
   * a) Sa determinati care dintre ele se intersecteaza (de exemplu, un domeniu
   * orizontal care incepe de la (1, 2) si cel vertical care incepe de la (0, 2)
   * au in comun o litera in pozitia (1, 2).
   *
   * b) Pentru acele domenii care se intersecteaza, trebuie sa eliminati avele
   * posibilitati de cuvinte care nu au nici un corespondent posibil, de exemplu
   * daca cuvintele din domeniul orizontal sunt ('ANA', 'ARE' si 'MERE'), si
   * cele din domeniul vertical sunt ('RAM', si 'ART'), atunci se poate elimina
   * cuvantul 'MERE' din domeniul orizontal pentru ca nu exista nici o
   * restrictie care il acopere (nu exista nici un cuvant vertical cu 'M' in a
   * doua pozitie).
   *
   * Obs: In functie de algoritmul ales, veti restrange domeniile in diferite
   * moduri: simetric, doar pe cel vertical, iterativ sau printr-un singur
   * "pass".
   */
  return true;
}

void backtracking(
    int row,
    int col,
    Domains& horizontal,
    Domains& vertical,
    std::vector<std::string>& verticalPrefixes)
{
  static bool found_solution = false;

  /* Marcam faptul ca am mai efectuat o intrare in recursivitate. */
  recursions++;

  /* Daca e solutie, afiseaz-o si iesi. */
  if (rebus.is_done()){
    std::cout << rebus;
    found_solution = true;
    return;
  } else if (row == rebus.rows) {
    return;
  }

  if (rebus.is_empty(row, col)) {
    /* Daca la (row, col) nu este completat, atunci incercam sa completam
     * orizontal unul dintre stringurile din domeniu pentru (row, col). */
    std::vector<std::string>& possibilities = horizontal[Position(row, col)];

    /* TODO: Incercati pe rand toate variantele de a completa de la (row, col)
     * in dreapta. Puteti folosi functia putString din Rebus.h */

  } else {
    /* Cand treci peste un '*', sterge prefixul care se forma vertical pentru
     * coloana respectiva. */
    if (rebus.get(row, col) == '*') {
      verticalPrefixes[col] = std::string();
    }

    /* TODO: Fiind deja completat, treci mai departe la completarea cu
     * backtracking a urmatoarei celule de la dreapta (si daca suntem la
     * sfarsitul randului, a primei celule de pe urmatoarea linie). */
  }
}

int main()
{
  /* Declaram si citim un rebus. */
  std::ifstream rebus_file("src-lab4/Puzzle.rebus");
  rebus_file >> rebus;
  rebus_file.close();

  /* Citim dictionarul. */
  std::ifstream vocabulary_file("src-lab4/Vocabular.txt");
  vocabulary_file >> vocabular;
  vocabulary_file.close();

  /* Inregistram timpul de inceput. */
  timeval start_time;
  gettimeofday(&start_time, NULL);

  Domains horizontal, vertical;
  std::vector<std::string> prefixes(rebus.columns, std::string());
  build_word_sets(horizontal, vertical);
  backtracking(0, 0, horizontal, vertical, prefixes);

  /* Inregistram timpul final. */
  timeval end_time;
  gettimeofday(&end_time, NULL);

  /* Afisam diferenta. */
  int nsec = end_time.tv_sec-start_time.tv_sec;  
  int nmsec = (end_time.tv_usec-start_time.tv_usec)/(double)1000;
  if (nmsec < 0 && nsec){
    nsec--;
    nmsec += 1000;
  }
  std::cout << "Total recursive calls: " << recursions << std::endl
      << "Total time is: " << nsec << " sec " << nmsec << " msec"
      << std::endl;
  return 0;
}

