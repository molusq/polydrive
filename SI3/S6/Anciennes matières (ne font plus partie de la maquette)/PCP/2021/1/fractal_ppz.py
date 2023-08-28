import sys

D = {"\u250C": "\u250C\u2510\u2514\u250C",
     "\u2510": "\u250C\u2510\u2510\u2518",
     "\u2514": "\u250C\u2514\u2514\u2518",
     "\u2518": "\u2518\u2510\u2514\u2518"}


def F(N):
  if N == 1:
    return ["\u250C\u2510", "\u2514\u2518"]
  R = F(N-1)
  RES = [[None]*len(R)*2 for _ in range(len(R)*2)]
  for i in range(len(R)):
    for j in range(len(R)):
      RES[2*i][2*j], RES[2*i][2*j+1], RES[2*i+1][2*j], RES[2*i+1][2*j+1] = D[R[i][j]]
  return RES


def main():
  N = int(input())
  for t in F(N):
    sys.stdout.buffer.write(''.join(t).encode())
    sys.stdout.buffer.write(b'\n')


main()
