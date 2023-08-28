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

void print_cells(array<bool, size> *pt_cells) {
    for (unsigned j = 0; j < pt_cells->size(); j++) {
        cout << (*pt_cells)[j];
    }
    cout << endl;
}

void compute_cells(array<bool, size> *pt_cells) {
    array<bool, size> cells = *pt_cells;
    for (unsigned i = 1; i < pt_cells->size() - 1; i++) {
        pt_cells->at(i) = cells.at(i - 1) && cells.at(i + 1);
    }
}

void compute_cells_full(array<bool, size> *pt_cells) {
    array<bool, size> cells = *pt_cells;
    pt_cells->at(0) = cells.at(size - 1) && cells.at(1);
    pt_cells->at(size - 1) = cells.at(size - 2) && cells.at(0);
    for (unsigned i = 1; i < pt_cells->size() - 1; i++) {
        pt_cells->at(i) = cells.at(i - 1) && cells.at(i + 1);
    }
}

void test() {
    array<bool, size> test1{false, false, false, false, false, false, false, false, false, false};
    compute_cells_full(&test1);
    for (unsigned i = 0; i < size; i++) {
        assert(!test1.at(i));
    }
    cout << "test 1 passed" << endl;

    array<bool, size> test2{true, true, true, true, true, true, true, true, true, true};
    for (unsigned i = 0; i < size; i++) {
        assert(test2.at(i));
    }
    cout << "test 2 passed" << endl;


    array<bool, size> test3{true, false, true, true, false, true, true, false, true, false};
    assert(!test3.at(0));
    assert(test3.at(2));
    assert(!test3.at(4));
    assert(!test3.at(6));
    assert(!test3.at(9));
    cout << "test 3 passed" << endl;
}

int main() {
    srand(time(NULL));

    test();

    array<bool, size> cells{};
    for (unsigned i = 0; i < cells.size(); i++) {
        cells[i] = rand() % 2 == 0;
    }
    print_cells(&cells);
    for (int i = 0; i < size; i++) {
        compute_cells_full(&cells);
        print_cells(&cells);
    }

    return 0;
}