f=lambda n,a,w:f(n-1,[s[:w]+a[i-w]+s[-w:] if w<=i<3*w else s+s for i,s in enumerate(2*a)],w*2)if n else a
import sys
sys.stdout.buffer.write("\n".join(f(int(input())-1,["┌┐","└┘"],1)).encode())