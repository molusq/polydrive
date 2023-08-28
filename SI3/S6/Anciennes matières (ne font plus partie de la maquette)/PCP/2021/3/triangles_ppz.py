def main():
  H, W = map(int, input().split())
  T = [input() for _ in range(H)]
  R = [[None]*W for _ in range(H)]
  RES = 0
  for j in range(W):
    R[0][j] = int(T[0][j] == '.')
    RES = max(RES, R[0][j])
  for i in range(H):
    R[i][0] = int(T[i][0] == '.')
    RES = max(RES, R[i][0])
    if W > 1:
      R[i][1] = int(T[i][1] == '.')
      RES = max(RES, R[i][1])
  for i in range(1, H):
    for j in range(2, W):
      R[i][j] = 1+min(R[i][j-1], R[i][j-2], R[i-1][j-1]) if T[i][j] == '.' else 0
      RES = max(RES, R[i][j])
  print(RES)


main()