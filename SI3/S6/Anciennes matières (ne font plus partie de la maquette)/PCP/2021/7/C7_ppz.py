def isqrt(x: int):
  if x < 0:
    raise ValueError('Square root is not defined for negative numbers.')
  if x == 0:
    return 0
  a, b = divmod(x.bit_length(), 2)
  n = 1 << (a + b)
  while True:
    y = (n + x // n) >> 1
    if y >= n:
      return n
    n = y

def main():
  N = int(input(),16)
  s = (isqrt(8 * N + 1) - 1) // 2
  x = N - (s * (s + 1)) // 2
  print(f'{x:X} {s-x:X}')

main()