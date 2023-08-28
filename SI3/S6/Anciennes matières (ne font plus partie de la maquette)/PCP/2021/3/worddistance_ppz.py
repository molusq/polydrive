def distancet(s1, s2, DEL=3, INS=3, REP=2):
	''' dynamic programming of edit distance with linear memory usage'''
	tabi = [i*DEL for i in range(len(s1)+1)]
	tabn = [0]*(len(s1)+1)
	for j in range(len(s2)):
		tabn[0] = INS*(j+1)
		for k in range(len(s1)):
			tabn[k+1]=(min(DEL+tabn[k],INS+tabi[k+1], (REP if s1[k]!=s2[j] else 0)+tabi[k]))
		tabi,tabn = tabn,tabi
	return tabi[-1]

def main():
    SA = input()
    SB = input()
    print(distancet(SA,SB))

main()