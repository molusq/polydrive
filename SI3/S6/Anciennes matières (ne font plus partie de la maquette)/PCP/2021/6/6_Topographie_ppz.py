from math import hypot
c=[tuple(map(int,input().split())) for _ in range(int(input()))]
print('KO' if any(abs(c[i][2]-c[j][2])<=hypot(c[i][0]-c[j][0],c[i][1]-c[j][1])<=c[i][2]+c[j][2] for i in range(len(c)) for j in range(i)) else 'OK')
