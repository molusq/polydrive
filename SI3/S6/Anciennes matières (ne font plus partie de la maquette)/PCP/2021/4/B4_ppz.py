def main():
  N = int(input())
  R = sorted((lambda s: (N-int(s[0]), float(s[1]), i+1))(input().split()) for i in range(N))
  def test_wrong(i, j): return i >= 0 and j < N and ((R[i][0] == R[j][0] and R[i][1] != R[j][1]) or
      (R[i][0] < R[j][0] and R[i][1] >= R[j][1]))
  for i in range(N-1):
    if test_wrong(i, i+1):
      if test_wrong(i+1, i+2) or test_wrong(i-1, i+1): print(R[i+1][2])
      elif test_wrong(i, i+2): print(R[i][2])
      else: print(0)
      return
  print(0)
main()
