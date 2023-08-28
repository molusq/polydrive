# TP4

## Part 1

TO SORT: n, sqrt(n), n^(1.5), n^2, nlog(n)
SORTED: sqrt(n), n, nlog(n), n^(1.5), n^2

TO SORT: nlog(log(n)), nlog²(n), 2/n, 2^n, 2^(n/2), 37, n²log(n), n^3
SORTED: 37, 2/n, nlog(log(n)), nlog²(n), n²log(n), n^3, 2^(n/2), 2^n

## Part 2

### 1)

O(n)

### 2)

O(n²)

### 3)

O(n^3)

### 4)

O(n²)

### 5)

O(n^4)

### 6)

O(?)

## Part 3

### a)

2.5 ms

### b)

3.4 ms

### c)

12.5 ms

### d)

62.5 ms

## Part 4

### 1)

T1(n) + T2(n) € O(f(n)) is true because if T1(n) € O(f(n)) and T2(n) € O(f(n)) then the greater value between T1 and T2 is kept and this greater is still part of O(f(n)).

### 2)

T1(n) € O(T2(n)) is false because we don't know if T1(n) > T2(n) or if T1(n) < T2(n)
