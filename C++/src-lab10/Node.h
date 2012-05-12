#ifndef _NODE_H_
#define _NODE_H_

#include <iostream>
#include <vector>

#define ABS(a) ((a) < 0 ? (-(a)) : (a))

class Node {
 public:
  static int N;

  Node() : distance_(0), parent_(NULL) { }

  friend std::ostream& operator<< (std::ostream& out, const Node& node);
  friend std::istream& operator>> (std::istream& in, Node& node);

  /** \brief Functie care verifica daca doua structuri de tip Node reprezinta
   * aceeasi stare. */
  bool has_same_state(const Node& other) const;

  /** \brief Functie care intoarce distanta parcursa de la starea initiala pana
   * la starea reprezentata de nod. */
  int distance() const { return distance_; }
  /** \brief Functie care inscrie distanta parcursa de la starea initiala pana
   * la starea reprezentata de nod in cadrul nodului. */
  void set_distance(int distance) { distance_ = distance; }

  /** \brief Functie care intoarce nodul parinte prin care s-a ajuns in starea
   * repezentata de nod. */
  const Node* parent() const { return parent_; }
  /** \brief Functie care inscrie nodul parinte prin care s-a ajuns in starea
   * reprezentata de nod. */
  void set_parent(const Node* parent) { parent_ = parent; }

  /** \brief Functie care intoarce numarul de piese care nu se afla la locul
   * pe care ar trebui sa il ocupe in starea finala. */
  int out_of_place(const Node& other) const;

  /** \brief Functie care intoarce estimarea distantei dintre 2 noduri ca suma
   * a diferentei pozitiilor. */
  int approx_distance(const Node& other) const;

  /** \brief Functie care expandeaza un nod si pune nodurile rezultate in lista
   * vecinilor primita ca parametru. */
  void expand(std::vector<Node*>& expanded) const;

  /** \brief Functie care afiseaza toti parintii nodului, in ordinea in care
   * acestia au fost parcursi. */
  void print_path() const;

 private:
  /* Returneaza locul in care se afla cifra x in grid. */
  std::pair<int, int> get_position(int x) const;

  /* Creaza un nou nod din nodul curent interschimband continutul celor 2
   * pozitii. */
  Node* create_node(int i, int j, int next_i, int next_j) const;

  /* Helper pentru afisare. Intoarce numarul de mutari. */ 
  static int print_partial_path(const Node* node);

  /* Starea pe care o reprezinta nodul. */
  std::vector<std::vector<int> > grid_;

  /* Distanta de la nodul sursa in explorare. */
  int distance_;

  /* Nodul parinte in explorare. NULL pentru nodul sursa. */
  const Node* parent_;
};

/* I/O operators. */
std::ostream& operator<< (std::ostream& out, const Node& node);
std::istream& operator>> (std::istream& in, Node& node);

#endif  /* _NODE_H_ */
