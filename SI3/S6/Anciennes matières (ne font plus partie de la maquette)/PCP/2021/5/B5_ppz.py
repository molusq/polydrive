def main():
	N = int(input())
	a,b = map(int, input().split())
	xi,yi=a,b
	RES = 0
	for _ in range(N-1):
		c,d = map(int, input().split())
		RES += a*d-b*c
		a,b=c,d
	RES += a*yi-b*xi
	print(abs(RES))


main()