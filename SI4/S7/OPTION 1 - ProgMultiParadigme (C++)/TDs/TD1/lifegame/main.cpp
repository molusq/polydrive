#include <iostream>
#include <vector>
#include <cstdlib>
#include <array>

using std::rand;
using std::vector;
using std::cout;
using std::endl;
using std::array;

const int size = 10;

void print_cells(array<array<bool, size>, size> *pt_cells) {
    for (unsigned i = 0; i < size; i++) {
        for (unsigned j = 0; j < size; j++) {
            cout << pt_cells->at(i)[j];
        }
        cout << endl;
    }
    cout << endl;
}

bool compute_surrounding_dead(array<array<bool, size>, size> *pt_cells, unsigned i, unsigned j) {
    int surrounding = 0;
    for (int a = -1; a < 2; a++) {
        for (int b = -1; b < 2; b++) {
            if (a != 0 && b != 0 && (pt_cells->at(i + a)).at(j + b)) {
                surrounding++;
            }
            if (surrounding > 3) {
                return false;
            }
        }
    }
    return true;
}

bool compute_surrounding_alive(array<array<bool, size>, size> *pt_cells, unsigned i, unsigned j) {
    int surrounding = 0;
    for (int a = -1; a < 2; a++) {
        for (int b = -1; b < 2; b++) {
            if (a != 0 && b != 0 && pt_cells->at(i + a)[j + b]) {
                surrounding++;
            }
            if (surrounding > 3) {
                return false;
            }
        }
    }
    return surrounding >= 2;
}

void compute_cells(array<array<bool, size>, size> *pt_cells) {
    array<array<bool, size>, size> cells = *pt_cells;
    for (unsigned i = 1; i < size - 1; i++) {
        for (unsigned j = 1; j < size - 1; j++) {
            if (cells.at(i)[j]) {
                pt_cells->at(i)[j] = compute_surrounding_alive(pt_cells, i, j);
            } else {
                pt_cells->at(i)[j] = compute_surrounding_dead(pt_cells, i, j);
            }
        }
    }
}

int main() {
    srand(time(NULL));

    array<array<bool, size>, size> cells{};

    for (unsigned i = 0; i < cells.size(); i++) {
        for (unsigned j = 0; j < size; j++) {
            cells[i][j] = rand() % 2 == 0;
        }
    }

    print_cells(&cells);

    for (int i = 0; i < size; i++) {
        compute_cells(&cells);
        print_cells(&cells);
    }

    return 0;
}