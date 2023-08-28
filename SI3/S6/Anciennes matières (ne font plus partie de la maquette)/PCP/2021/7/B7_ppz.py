def main():
  S = input()
  D = {}
  res, best = -1, ''
  for i, c in enumerate(S):
    if c in D:
      score = i-D[c]-1
      if score > res:
        res, best = score, c
    D[c] = i
  print(best, res)


main()
