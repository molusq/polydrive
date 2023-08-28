from mininet.topo import Topo
from mininet.net import Mininet
from mininet.node import Node
from mininet.link import TCLink
from mininet.log import setLogLevel
from mininet.cli import CLI
#from mininet.node import OVSController

class TestDel(Topo):
	"Double switch connecting a sender and a receiver"
	def __init__(self):
		Topo.__init__(self)
		s1 = self.addSwitch('s1')
		s2 = self.addSwitch('s2')
		h1 = self.addNode('h1')
		h2 = self.addNode('h2')
		h3 = self.addNode('h3')
		h4 = self.addNode('h4')
		self.addLink(s1, h1)
		self.addLink(s1, h2)
		self.addLink(s1, s2, bw=10)
		self.addLink(s2,h3)
		self.addLink(s2,h4)

topos = {
	'testdel': (lambda: TestDel())
}

def constTopo():
	topo = TestDel()
	net = Mininet(topo=topo, link=TCLink)
	net.start()
	h2 = net.get('h2')
	#h2int1 = h2.intf('h2-eth0')
	h1 = net.get('h1')
	h3 = net.get('h3')
	h4 = net.get('h4')
	#print h1.cmd('ping -c 3 %s' % h2.IP(h2int1))
	CLI(net)
	net.stop()

if __name__ == '__main__':
	setLogLevel('info')
	constTopo()
