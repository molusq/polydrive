def main():
  N = int(input())
  res = input()
  for _ in range(N-1):
    name = input()
    if len(name) < len(res) or (len(name) == len(res) and name < res):
      res = name
  print(res)


main()