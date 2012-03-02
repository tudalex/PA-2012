#include <iostream>
#include <vector>
#include <algorithm>

#include "VectorIO.h"
#include "PairIO.h"

/* Vom defini un task sub forma unei perechi (start, finish). */
typedef std::pair<int, int> Task;

bool compare_finish_time(const Task& left, const Task& right)
{
  return left.second < right.second;
}

int get_max(std::vector<Task>& v)
{
  /* TODO: Calculati numarul maxim de task-uri care nu se suprapun. */
  return 0;
}

int main()
{
  /* Declaram si citim vectorul de task-uri. */
  std::vector<Task> v;
  std::cin >> v;

  /* Afisam numarul maxim de task-uri pe care le poate indeplini robotul. */
  std::cout << "Nr maxim de task-uri care nu se suprapun: " << get_max(v)
            << std::endl;

  return 0;
}

