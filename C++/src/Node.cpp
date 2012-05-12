#include "Node.h"

#include <cmath>

bool Node::has_same_state(const Node& other) const {
  return out_of_place(other) == 0;
}

int Node::out_of_place(const Node& other) const {
  int count = 0;
  for (int i = 0; i < Node::N; ++i) {
    for (int j = 0; j < Node::N; ++j) {
      if (grid_[i][j] != other.grid_[i][j]) {
        count++;
      }
    }  
  }
  return count;
}

int Node::approx_distance(const Node& other) const {
  int approx_distance = 0;
  for (int i = 0; i < Node::N; ++i) {
    for (int j = 0; j < Node::N; ++j) {
      const std::pair<int, int> pos = other.get_position(grid_[i][j]);
      approx_distance += ABS(i - pos.first) + ABS(j - pos.second);
    }
  }
  return approx_distance;
}

std::pair<int, int> Node::get_position(int x) const {
  for (int i = 0; i < Node::N; ++i) {
    for (int j = 0; j < Node::N; ++j) {
      if (grid_[i][j] == x) {
        return std::make_pair<int, int>(i , j);
      }
    }
  }
  return std::make_pair<int, int>(-1, -1);
}

Node* Node::create_node(int i, int j, int next_i, int next_j) const {
  Node* next = new Node(*this);
  next->set_parent(this);
  next->set_distance(distance() + 1);
  std::swap(next->grid_[i][j], next->grid_[next_i][next_j]);
  return next;
}

void Node::expand(std::vector<Node*>& expanded) const {
  const std::pair<int, int> empty_space = get_position(0);
  const int i = empty_space.first;
  const int j = empty_space.second;

  /* Exista maxim 4 vecini posibili: */
  /* 1. Patratelul de deasupra spatiului alb este interschimbat. */
  if (i > 0) {
    expanded.push_back(create_node(i, j, i - 1, j));
  }

  /* 2. Patratelul de dedesubtul spatiului alb este interschimbat. */
  if (i < Node::N - 1) {
    expanded.push_back(create_node(i, j, i + 1, j));
  }

  /* 3. Patratelul de la stanga spatiului alb este interschimbat. */
  if (j > 0) {
    expanded.push_back(create_node(i, j, i, j - 1));
  }

  /* 4. Patratelul de la dreapta spatiului alb este interschimbat. */
  if (j < Node::N - 1) {
    expanded.push_back(create_node(i, j, i, j + 1));
  }
}

void Node::print_path() const {
  int moves = Node::print_partial_path(this);
  std::cout << moves << " mutari" << std::endl;
}

int Node::print_partial_path(const Node* node) {
  if (node == NULL) {
    return 0;
  }
  const int moves = print_partial_path(node->parent());
  std::cout << *node << std::endl;
  return moves + 1;
}

/* I/O operators. */
std::ostream& operator<< (std::ostream& out, const Node& node) {
  for (int i = 0; i < Node::N; ++i) {
    for (int j = 0; j < Node::N; ++j) {
      std::cout << node.grid_[i][j] << " ";
    }
    std::cout << std::endl;
  }
  return out;
}

std::istream& operator>> (std::istream& in, Node& node) {
  node.grid_.resize(Node::N);
  for (int i = 0; i < Node::N; ++i) {
    node.grid_[i].resize(Node::N);
    for (int j = 0; j < Node::N; ++j) {
      in >> node.grid_[i][j];
    }
  }
  return in;
}

