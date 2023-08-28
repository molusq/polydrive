#! /usr/bin/env python3

import socket
import struct
import sys
import time

DPORT= int(sys.argv[2])
DHOST = sys.argv[1]

try:
	global f
	f = None
	f = open("video1.mpg",'rb')
except IOError:
	print("Error while opening the file. Exiting...")
	exit(-1)

# we read the file and send it litle by little
# if you need to introduce a delay between two transmissions
# you can use
# time.sleep(2.5)
# the line above makes the program to sleep for 2.5 seconds

# closing the file
f.close()

