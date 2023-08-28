N=int(input())
for i in range(N) : print('.'*abs(N//2-i)+'*'*(N-2*abs(N//2-i))+'.'*abs(N//2-i))
