import socket
import sys
import time
HOST = sys.argv[1]
PORT = 5000 # The remote port
s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM) 
f = open('/home/user/Bureau/video1.mpg', 'rb')
str1 = f.read(7975)
while (str1):
	s.sendto(str1,(HOST,PORT))
	time.sleep(0.1)
	str1 = f.read(7975)
#data, (HOST,PORT) = s.recvfrom(1024)
#print "Received %s" %(data)
print "end"
s.close()
f.close()