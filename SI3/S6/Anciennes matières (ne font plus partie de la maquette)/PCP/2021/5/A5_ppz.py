def main():
  N = int(input())
  maxx = maxy = -float('inf')
  minx = miny = -maxx
  for _ in range(N):
    x, y = map(int, input().split())
    maxx, maxy, minx, miny = max(maxx, x), max(maxy, y), min(minx, x), min(miny, y)
  print(maxx-minx, maxy-miny)


main()