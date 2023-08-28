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
        # Add switches
        s1 = self.addSwitch('s1')
        # Add routers
        R1 = self.addNode('R1')
        R2 = self.addNode('R2')
        R3 = self.addNode('R3')
        R4 = self.addNode('R4')
        # Add hosts
        h1 = self.addNode('h1')
        h2 = self.addNode('h2')
        h41 = self.addNode('h41')
        h42 = self.addNode('h42')
        # Add links
        self.addLink(R1,R2)
        self.addLink(R2,R3)
        self.addLink(R3,R1)
        self.addLink(R3,R4)
        self.addLink(R4,s1)
        self.addLink(R1,h1)
        self.addLink(R2,h2)
        self.addLink(s1,h41)
        self.addLink(s1,h42)

topos = {
    'testrout': (lambda: TestRout())
}

def netconf(net):
    h1 = net.get('h1')
    h2 = net.get('h2')
    h41 = net.get('h41')
    h42 = net.get('h42')

    # clearing IP addresses
    h1.cmd ('ip a flush dev h1-eth0')
    h2.cmd ('ip a flush dev h2-eth0')
    h41.cmd('ip a flush dev h41-eth0')
    h42.cmd('ip a flush dev h42-eth0')

    R = [];
    for i in range(4):
        R.append(net.get("R" + str(i+1)))
        # clearing all IP addresses
        R[i].cmd('ip a flush dev R'+str(i+1)+'-eth0')
        # next lines disable Linux networking security features
        # so we can ping between routers (for debugging purposes only)
        R[i].cmd('sysctl -w net.ipv4.conf.all.rp_filter=0')
        R[i].cmd('sysctl -w net.ipv4.conf.default.rp_filter=0')
        R[i].cmd('sysctl -w net.ipv4.conf.R'+str(i+1)+'-eth0.rp_filter=0')
        R[i].cmd('sysctl -w net.ipv4.conf.R'+str(i+1)+'-eth1.rp_filter=0')
        R[i].cmd('sysctl -w net.ipv4.conf.R'+str(i+1)+'-eth2.rp_filter=0')

def constTopo():
    topo = TestRout()
    net = Mininet(controller=None, topo=topo, link=TCLink, switch=LinuxBridge)
    net.start()
    netconf(net)
    CLI(net)
    net.stop()

if __name__ == '__main__':
    setLogLevel('info')
    constTopo()