BORDER = -3
WATER = -2
WET = -1
LAND = 0

NEIGHBORHOOD = [(i, j) for i in (-1, 0, 1) for j in (-1, 0, 1) if (i, j) != (0, 0)]


def main():
  H, W = map(int, input().split())
  T = [[BORDER]*(W+2)]
  for _ in range(H):
    T.append([BORDER])
    for c in input():
      T[-1].append(WATER if c == '#' else LAND)
    T[-1].append(BORDER)
  T.append(T[0])
  for i in range(1, H+1):
    for j in range(1, W+1):
      if T[i][j] == WATER:
        for x, y in NEIGHBORHOOD:
          if T[i+x][j+y] == LAND:
            T[i+x][j+y] = WET
  #for r in T:
  #  print(''.join(f'{c:3}' for c in r))
  print(sum(sum(c == LAND for c in row) for row in T))


main()
