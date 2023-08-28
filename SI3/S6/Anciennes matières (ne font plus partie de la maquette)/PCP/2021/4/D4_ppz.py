def binom(n,p):
    if p>n//2 : p=n-p
    if p<0 : return 0
    res = 1
    for i in range(p):
        res = (res*(n-i))//(i+1)
    return res

def main():
    S, K = map(int, input().split())
    C = list(map(int, input().split()))
    S = S-sum(C)
    print(binom(S+K-1,S))

main()