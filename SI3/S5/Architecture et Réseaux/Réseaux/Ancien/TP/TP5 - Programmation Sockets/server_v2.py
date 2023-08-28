import socket
import os
import time
HOST = '' # any available interf 
PORT = 5000 # Arbitrary non-priv port 
s = socket.socket(socket.AF_INET,socket.SOCK_STREAM) 
s.bind((HOST, PORT)) 
s.listen(1)
while 1:
	conn, addr = s.accept()
	pid = os.fork()
	if pid == 0:
		while 1:
			data = conn.recv(1024)
			time.sleep(10)
			conn.sendall(data)

		conn.close()
		os._exit(0)
conn.close()
s.close()