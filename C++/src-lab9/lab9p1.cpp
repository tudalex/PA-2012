#include <iostream>
#include <fstream>
#include <algorithm>
#include <cmath>
#include <vector>
#include <queue>
#include "GraphAdjMat.h"
#include "DisjointSets.h"

using namespace std;
typedef pair < int, pair < int, int > > p;
std::vector<Edge> prim(GraphAdjMat& graph, int seed)
{
  std::vector<Edge> returnValue;
  //return returnValue;

  /* Initial, nici unul dintre noduri in afara de frunza nu apartine de
   * arbore. */
  std::vector<bool> in_tree(graph.get_n(), false);
  in_tree[seed] = true;

  /* Cream un vector de distante si il initializam cu distantele fata de seed. */
  int dist[graph.get_n()];
  int parent[graph.get_n()];

  for (unsigned int i = 0; i < graph.get_n(); ++i) {
    if (i != seed) {
      dist[i] = graph.get_edge(seed, i);
      parent[i] = seed;
    } else {
      dist[i] = 0;
      parent[i] = GraphAdjMat::NONE;
    }
  }
  std::vector <bool> viz(graph.get_n(), 0);
  std::priority_queue < p, vector < p >, greater <p> > q;
  q.push(make_pair(0,make_pair(seed, seed)));
  while (!q.empty())
  {
		int cost = q.top().first;
		int nod = q.top().second.first;
		int parent = q.top().second.second;
		q.pop();
		if (viz[nod])
			continue;
		viz[nod] = 1;
		if (parent != nod)
			returnValue.push_back(Edge(parent, nod, graph.get_edge(parent, nod)));
		for (int i= 0; i < graph.get_n(); ++ i)
		{
			int k = graph.get_edge(nod, i);
			if (k != graph.NONE)
				q.push(make_pair(k+cost, make_pair(i, nod)));
			
		}
	}  
  
  /* TODO: Puneti in vectorul returnValue acele muchii care formeaza APM in urma
   * rularii algoritmului lui Prim din nodul sursa seed. */

  return returnValue;
}
bool cmp(const Edge a, const Edge b)
{
	return a.cost < b.cost;
}
std::vector<Edge> kruskall(GraphAdjMat& graph)
{
  std::vector<Edge> returnValue;
  std::vector<Edge> v= graph.get_edges();

  /* Presupunem initial ca fiecare nod in graf este in propriul sau arbore. */
  DisjointSets tree_set(graph.get_n());
	std::sort(v.rbegin(), v.rend(), cmp);
	for (int i = 0; i < v.size(); ++i)
	{
		if (!tree_set.same_set(v[i].v, v[i].u))
		{
			returnValue.push_back(v[i]);
			tree_set.merge_sets_of(v[i].v,v[i].u);
		}
	}
  /* TODO: Puneti in vectorul returnValue acele muchii care formeaza APM in urma
   * rularii algoritmului lui Kruskall. */

  return returnValue;
}

int main()
{
  /* Deschidem un fisier si citim din el un graf neorientat. */
  std::ifstream in("src-lab9/Graph.in");
  int n;
  in >> n;
  GraphAdjMat graph(n);
  in >> graph;

  std::cout << "Initial graph: " << std::endl << graph << std::endl;
  std::vector<Edge> edges;

  /* Calculam muchiile pe care le alege algoritmul lui Prim. */
  edges = prim(graph, 0);
  std::cout << "Algoritmul lui Prim construieste pornind la nodul 0"
      << " arborele format din urmatoarele muchii:" << std::endl;
  for (unsigned int i = 0; i < edges.size(); ++i) {
    std::cout << edges[i] << std::endl;
  }

  /* Calculam muchiile pe care le alege algoritmul lui Kruskall. */
  edges = kruskall(graph);
  std::cout << "Algoritmul lui Kruskall construieste o padure de arbori"
      << " formata din urmatoarele muchii:" << std::endl;
  for (unsigned int i = 0; i < edges.size(); ++i) {
    std::cout << edges[i] << std::endl;
  }

  return 0;
}

