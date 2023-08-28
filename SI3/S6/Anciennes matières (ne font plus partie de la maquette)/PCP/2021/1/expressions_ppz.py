from fractions import Fraction as F

OP = {'+': lambda x, y: y+x,
      '-': lambda x, y: y-x,
      '*': lambda x, y: y*x,
      '/': lambda x, y: y/x}


def eval_expression(l):
  stack = []
  try:
    for c in l:
      if c in OP:
        stack.append(OP[c](stack.pop(), stack.pop()))
      else:
        stack.append(F(c))
    return stack[0] if len(stack) == 1 else None
  except:
    return None


def main():
  A, B = input().split(), input().split()
  print(eval_expression(A) == eval_expression(B))


main()
