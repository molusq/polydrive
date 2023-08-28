BORDER = -3
WATER = -2
WET = -1
LAND = 0

NEIGHBORHOOD = [(i, j) for i in (-1, 0, 1) for j in (-1, 0, 1) if (i, j) != (0, 0)]
FNGBD = [(-1, 0), (0, -1), (1, 0), (0, 1)]


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
  island = 1
  for i in range(1, H+1):
    for j in range(1, W+1):
      if T[i][j] == LAND:
        stack = [(i, j)]
        T[i][j] = island
        while stack:
          x, y = stack.pop()
          for dx, dy in FNGBD:
            if T[x+dx][y+dy] == LAND:
              T[x+dx][y+dy] = island
              stack.append((x+dx, y+dy))
        island += 1
  # for r in T:
  #   print(''.join(f'{c:3}' for c in r))
  print(island-1)


main()