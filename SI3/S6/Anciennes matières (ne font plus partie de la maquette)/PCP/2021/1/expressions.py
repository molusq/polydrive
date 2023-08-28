from operator import *
from fractions import *
OPS = {"+":add,"-":sub,"*":mul,"/":lambda a,b:Fraction(a,b)}
MAX = 2**63-1
def int_overflow(val):
  if not -MAX-1 <= val <= MAX:
    val = (val + (MAX + 1)) % (2 * (MAX + 1)) - MAX - 1
  return val
def ev(expr):
	try:
		stack = []
		for op in expr.split(" "):
			of = OPS.get(op, None)
			if of is None:
				stack.append(int(op))
			else:
				if len(stack) < 2:
					return None
				op1 = stack.pop()
				stack.append(of(stack.pop(), op1))
		if len(stack) != 1:
			return None
		return stack[-1]
	except:
		return None
print(ev(input()) == ev(input()))
			