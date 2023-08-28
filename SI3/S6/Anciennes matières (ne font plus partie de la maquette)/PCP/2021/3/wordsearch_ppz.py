import re


def main():
  H, W = map(int, input().split())
  T = input()
  M = [input() for _ in range(H)]
  RT = T[::-1]
  
  RE = f'(?={T}|{T[::-1]})' if T!=RT else f'(?={T})'
  RE = re.compile(RE)
  res = sum(len(RE.findall(row)) for row in M)
  res += sum(len(RE.findall(''.join(M[i][j] for i in range(H)))) for j in range(W))
  if T==RT:
    res = res*2
  print(res)


main()
