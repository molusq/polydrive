def val(N, CACHE={}):
  return N>0 and CACHE.setdefault(N, not all(val((N-1)//k) for k in (2,3,7)))

print("First" if val(int(input())) else "Second", "player")