import socket
import sys
HOST = sys.argv[1]
PORT = 5000 # The remote port
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
s.connect((HOST, PORT))
val = "Hello World"
s.sendall(val)
data = s.recv(1024)
print "Received %s" %(data)
s.close()