THRESHOLD = 5
def main():
  N = int(input())
  L, STACK = sorted(tuple(map(int, input().split()))+(i,) for i in range(N)), [(0,N)]
  for a,b in STACK:
    if b-a>= THRESHOLD:
      m = (a+b)//2
      STACK.extend([(a,m), (m,b)])
  mini, ai, bi = (L[0][0]-L[1][0])**2+(L[0][1]-L[1][1])**2, min(L[0][2],L[1][2]), max(L[0][2], L[1][2])
  for a,b in reversed(STACK):
    if b-a < THRESHOLD:
      for i in range(a, b-1):
        for j in range(i+1, b):
          dx = (L[i][0]-L[j][0])**2
          if dx > mini : break
          d = dx+(L[i][1]-L[j][1])**2
          i1, i2 = min((L[i][2], L[j][2]), (L[j][2], L[i][2]))
          if (d, i1, i2) < (mini, ai, bi): mini, ai, bi = d, i1, i2
    else:
      m = (a+b)//2
      Delta, strip, d = (L[m-1][0]+L[m][0])//2, [], m-1
      while d >= a and (Delta-L[d][0])**2 <= mini:
        strip.append(L[d])
        d -= 1
      d = m
      while d < b and (Delta-L[d][0])**2 <= mini:
        strip.append(L[d])
        d += 1
      strip.sort(key=lambda x: x[1])
      for i in range(len(strip)-1):
        x, y, i1 = strip[i]
        for j in range(i+1, min(i+8, len(strip))):
          x2, y2, i2 = strip[j]
          dy = (y2-y)**2
          if dy > mini: break
          d = (x2-x)**2 + dy
          if i2<i1 : i2,i1=i1,i2
          if (d, i1, i2) < (mini, ai, bi): mini, ai, bi = d, i1, i2
  print(mini, ai, bi)

main()