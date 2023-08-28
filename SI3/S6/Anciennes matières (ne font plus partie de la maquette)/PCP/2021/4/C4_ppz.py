def inverse(v,P):
  return pow(v,P-2,P)


def main():
  _, C, P = map(int, input().split())
  T = list(map(int, input().split()))
  SS = [tuple(map(int, input().split())) for _ in range(C)]
  U, c, p = [(1,0)], 0, 1
  for k in T:
    if k == 0:
      U.append((0, None))
      c += 1
      p = 1
    else:
      p = (p*k) % P
      U.append((p, c))
  i=1
  for a, b in SS:
    i+=1
    if a == b:
      print(T[a] % P)
    else:
      v1, c1 = U[a+1]
      v2, c2 = U[b+1]
      if c1!=c2 or c1 is None:
        print(0)
      else:
        print((T[a]*v2*inverse(v1,P))%P)

main()