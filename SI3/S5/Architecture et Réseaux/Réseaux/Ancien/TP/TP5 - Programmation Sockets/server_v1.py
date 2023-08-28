import socket 
HOST = '' # any available interf 
PORT = 5000 # Arbitrary non-priv port 
s = socket.socket(socket.AF_INET,socket.SOCK_DGRAM) 
s.bind((HOST, PORT)) 
data, (HOST,PORT) = s.recvfrom(10000) 
#s.sendto(data, (HOST,PORT))
s.close()