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
  sort (v.begin(), v.end(), compare_finish_time);
  int cur_end_time = 0, sol = 0;
  for (int i = 0; i < v.size(); ++ i)
  	if (v[i].first >= cur_end_time)
  	{
  		++sol;
  		cur_end_time = v[i].second;
  	}  
  return sol;
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

