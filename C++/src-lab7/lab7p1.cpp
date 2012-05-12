#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <stack>
enum Color {
  WHITE,
  GRAY,
  BLACK
};

struct Node {
  unsigned int color;
  unsigned int component;
  unsigned int level;
  /* TODO: Adaugat aici tot ce vi se pare util. */
};

/* Matricea de adiacenta globala. */
namespace graph {

/* In C++, variabilele dintr-un namespace se pot accesa prefixand numele
 * namespace-ului numelui variabilelelor (asta ca sa nu fie globale si sa intre
 * in conflict cu alte variabile exportate). De exemplu:
 *
 * graph::size
 */
std::vector<std::vector<bool> > edge;
unsigned int size;
std::vector<Node> nodes;

};

/* Functie care face toate nodurile albe. */
void clearColor()
{
  for (unsigned int i = 0; i < graph::size; ++i) {
    graph::nodes[i].color = WHITE;
    graph::nodes[i].component = -1;
   // graph::nodes[i].level = 1<<30;
  }
}

void dfs(unsigned int node, unsigned int comp = -1
         /* TODO: Adaugati orice parametri vi se par utili/relevanti. */)
{
	graph::nodes[node].color=GRAY;
	for (int i = 0; i < graph::size; ++ i)
		if ( (graph::edge[node][i] || graph::edge[i][node]) && graph::nodes[i].color == WHITE)
		{
						
			dfs(i, comp);
		}
	graph::nodes[node].color=BLACK;
	if (comp!=-1)
		graph::nodes[node].component = comp; 
  /* TODO: Implementati un DFS (obs: Puteti implementa unul generic pe care sa
   * il refolositi la toate subpunctele. Va trebui insa sa dati parametri
   * speciali care sa tine cont daca se face DFS pe graful
   * neorientat/transpus) */
}

std::stack < int > q;
void dfs2(unsigned int node, unsigned int comp = -1
         /* TODO: Adaugati orice parametri vi se par utili/relevanti. */)
{
	graph::nodes[node].color=GRAY;
	for (int i = 0; i < graph::size; ++ i)
		if (i != node &&  graph::edge[node][i] && graph::nodes[i].color == WHITE)
		{
			
			dfs2(i, comp);
		}
	graph::nodes[node].color=BLACK;
	q.push(node);
	if (comp!=-1)
		graph::nodes[node].component = comp; 

}
void dfs3(unsigned int node, unsigned int comp = -1
         /* TODO: Adaugati orice parametri vi se par utili/relevanti. */)
{
	graph::nodes[node].color=GRAY;
	for (int i = 0; i < graph::size; ++ i)
		if (i != node &&  graph::edge[i][node] && graph::nodes[i].color == WHITE)
		{
			
			dfs3(i, comp);
		}
	graph::nodes[node].color=BLACK;
	
	if (comp!=-1)
		graph::nodes[node].component = comp; 
 
}

int dfs4(unsigned int node, unsigned int lvl = 0, int parent = -1
         /* TODO: Adaugati orice parametri vi se par utili/relevanti. */)
{
	int ct = 0;
	int k = lvl;
	int leaf = 0;
	graph::nodes[node].color=GRAY;
	graph::nodes[node].level = lvl;
	for (int i = 0; i < graph::size; ++ i)
		if (i != node && i!=parent && (graph::edge[node][i] || graph::edge[i][node]))
		{
			++leaf ;
			if ( graph::nodes[i].color == WHITE)
				k = std::min(k, dfs4(i, lvl+1, node));
			k = std::min((int)graph::nodes[i].level, k);
		}
	graph::nodes[node].color=BLACK;
	if (k >= lvl && leaf && !(parent == -1 && leaf<2))
		std::cout <<node<<" ";
	//std::cerr<<"D "<<node<<" "<<k<<" "<<lvl<<std::endl;
	return k; 
  /* TODO: Implementati un DFS (obs: Puteti implementa unul generic pe care sa
   * il refolositi la toate subpunctele. Va trebui insa sa dati parametri
   * speciali care sa tine cont daca se face DFS pe graful
   * neorientat/transpus) */
}

int dfs5(unsigned int node, unsigned int lvl = 0, int parent = -1
         /* TODO: Adaugati orice parametri vi se par utili/relevanti. */)
{
	int ct = 0;
	int k = lvl;
	int leaf = 0;
	graph::nodes[node].color=GRAY;
	graph::nodes[node].level = lvl;
	for (int i = 0; i < graph::size; ++ i)
		if (i != node && i!=parent && (graph::edge[node][i] || graph::edge[i][node]))
		{
			++leaf ;
			if ( graph::nodes[i].color == WHITE)
			{
				int o = dfs5(i, lvl+1, node);
				if (o > lvl)
					std::cout<<"Muchia "<<node<<" "<<i<<std::endl;
				k = std::min(k, o);
			}
			k = std::min((int)graph::nodes[i].level, k);
		}
	graph::nodes[node].color=BLACK;
	/*if (k >= lvl && leaf && !(parent == -1 && leaf<2))
		std::cout <<node<<" ";*/
	//std::cerr<<"D "<<node<<" "<<k<<" "<<lvl<<std::endl;
	return k; 
  /* TODO: Implementati un DFS (obs: Puteti implementa unul generic pe care sa
   * il refolositi la toate subpunctele. Va trebui insa sa dati parametri
   * speciali care sa tine cont daca se face DFS pe graful
   * neorientat/transpus) */
}
void paintComponents(std::vector<std::vector<unsigned int> >& components)
{

  /* TODO 1: Implementati aici detectarea componentelor din graful reunit cu graful
   * transpus (altfel spus, graful daca ar fi fost neorientat).
   *
   * Trebuie ca in vectorul de components sa puneti cate un vector pentru
   * fiecare componenta conexa detectata, acel vector continand ID-urile
   * nodurilor din componenta. */
   clearColor();
   int k = 0;
   for (int i = 0; i < graph::size; ++ i)
		if(graph::nodes[i].color==WHITE)
			dfs(i, k++);
   fprintf(stderr, "%d %d\n",graph::size, k);
   components.resize(k);
   for (int i = 0; i < graph::size; ++ i)  
   		components[graph::nodes[i].component].push_back(i);
}
void paintComponents2(std::vector<std::vector<unsigned int> >& components)
{

 
   clearColor();
   int k = 0;
   for (int i = 0; i < graph::size; ++ i)
		if(graph::nodes[i].color==WHITE)
			dfs2(i);
   fprintf(stderr, "%d %d\n",graph::size, k);
   clearColor();
   while (!q.empty())
   {
   		if (graph::nodes[q.top()].color == WHITE)
			dfs3(q.top(), k++);
		q.pop();
	}

   components.resize(k);
   for (int i = 0; i < graph::size; ++ i)  
   		components[graph::nodes[i].component].push_back(i);
}

/** Functie care incarca un graf dintr-un fisier daca se da numele fisierului.
 * Atentie, fisierul cu graful trebuie sa aiba sintaxa corecta. */
bool loadGraph(const char* fileName)
{
  std::ifstream in(fileName);
  if (!in.good()) {
    std::cout << "Error! Could not open graph file '" << fileName << "'."
              << std::endl;
    return false;
  }

  in >> graph::size;
  graph::edge = std::vector<std::vector<bool> >(
      graph::size, std::vector<bool>(graph::size, false));
  graph::nodes = std::vector<Node>(graph::size, Node());
  unsigned int edgeCount;
  in >> edgeCount;
  for (unsigned int i = 0; i < edgeCount; ++i) {
    unsigned int a, b;
    in >> a >> b;
    graph::edge[a][b] = true;
  }

  if (!in.good()) {
    std::cout << "Error. Bad graph file syntax." << std::endl;
    return false;
  }

  in.close();
  return true;
}

int main()
{
  /* Deschide fisierul de intrare si citeste graful. */
  if (!loadGraph("src-lab7/Graph.txt")) {
    return 0;
  }

  /* Calculam si afisam componentele conexe. */
  std::vector<std::vector<unsigned int> > components;
  paintComponents(components);
  std::cout << std::endl << "Componentele conexe ale grafului neorientat sunt: "
      << std::endl;
  for (unsigned int i = 0; i < components.size(); ++i) {
    std::cout << "Componenta " << i << ":";
    for (unsigned int j = 0; j < components[i].size(); ++j) {
      std::cout << " " << components[i][j];
    }
    std::cout << std::endl;
  }
  std::cout << std::endl;
  components.clear();
  /* TODO 2: Calculati si afisati componentele tare conexe. */
  paintComponents2(components);
  std::cout << std::endl << "Componentele tari conexe ale grafului orientat sunt: "
      << std::endl;
  for (unsigned int i = 0; i < components.size(); ++i) {
    std::cout << "Componenta " << i << ":";
    for (unsigned int j = 0; j < components[i].size(); ++j) {
      std::cout << " " << components[i][j];
    }
    std::cout << std::endl;
  }
  std::cout << std::endl;
  /* TODO 3: Calculati si afisati nodurile critice. */
  clearColor();
  std::cout <<"Noduri critice"<<std::endl;
  dfs4(0);
  std::cout <<std::endl;
  /* TODO 4: Calculati si afisati puntile. */
  clearColor();
  std::cout <<"Muchii critice"<<std::endl;
  dfs5(0);
  std::cout <<std::endl;
  /* TODO 5: Calculati si afisati o strategie de pavare. */

  return 0;
}
