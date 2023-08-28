#!/usr/bin/env python
from mininet.topo import Topo
from mininet.net import Mininet
from mininet.node import Node, RemoteController
from mininet.link import TCLink, Intf
from mininet.util import dumpNodeConnections
from mininet.log import setLogLevel
from multiprocessing import Process,Queue
import os,sys, getopt
from mininet.cli import CLI
import time
from array import *
from functools import partial
from mininet.nodelib import LinuxBridge

class TestRout(Topo):
	"Double switch connecting a sender and a receiver"
	def __init__(self):
		Topo.__init__(self)
		h1 = self.addNode('h1')
		h2 = self.addNode('h2')
		h3 = self.addNode('h3')
		s1 = self.addSwitch('s1')
		R1 = self.addNode('R1')
		R2 = self.addNode('R2')
		self.addLink(h1,R1)
		self.addLink(R1,R2)
		self.addLink(R2,s1)
		self.addLink(h2,s1)
		self.addLink(h3,s1)

		# declaration des nodes, switches, routeurs (=nodes) et liens

topos = {
	'testrout': (lambda: TestRout())
}

def constTopo():
	topo = TestRout()
	net = Mininet(topo=topo, link=TCLink, switch=LinuxBridge)
	net.start()

	h1 = net.get('h1')
	h1eth0 = h1.intf("h1-eth0")
	h1.setIP("10.0.1.10", 24, h1eth0)

	h2 = net.get('h2')
	h2eth0 = h2.intf("h2-eth0")
	h2.setIP("10.0.3.10", 24, h2eth0)

	h3 = net.get('h3')
	h3eth0 = h3.intf("h3-eth0")
	h3.setIP("10.0.3.20", 24, h3eth0)

	R1 = net.get('R1')
	R1eth0 = R1.intf("R1-eth0")
	R1eth1 = R1.intf("R1-eth1")
	R1.setIP("10.0.1.1", 24, R1eth0)
	R1.setIP("10.0.2.1", 24, R1eth1)

	R2 = net.get('R2')
	R2eth0 = R2.intf("R2-eth0")
	R2eth1 = R2.intf("R2-eth1")
	R2.setIP("10.0.2.2", 24, R2eth0)
	R2.setIP("10.0.3.1", 24, R2eth1)

	h1.cmd('route add default gw 10.0.1.1')
	h2.cmd('route add default gw 10.0.3.1')
	h3.cmd('route add default gw 10.0.3.1')

	R1.cmd('sysctl -w net.ipv4.ip_forward=1')
	R2.cmd('sysctl -w net.ipv4.ip_forward=1')	
	R2.cmd('route add -net 10.0.1.0 netmask 255.255.255.0 gw 10.0.2.1')
	R1.cmd('route add -net 10.0.3.0 netmask 255.255.255.0 gw 10.0.2.2')
	
	CLI(net)
	net.stop()

if __name__ == '__main__':
	setLogLevel('info')
	constTopo()