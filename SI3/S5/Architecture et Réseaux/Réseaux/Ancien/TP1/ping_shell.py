from mininet.net import Mininet
from mininet.topo import Topo
from mininet.node import Node
from mininet.cli import CLI
from mininet.link import TCLink
from mininet.log import setLogLevel
from mininet.node import OVSController

class Test_Topo(Topo):
	def __init__(self):
		"Ping"
		Topo.__init__(self)
		h1 = self.addNode('h1')
		h2 = self.addNode('h2')
		s1 = self.addSwitch('s1')
		
		self.addLink(s1,h1)
		self.addLink(s1,h2)
		
topos = {
	'toptest':(lambda: Test_Topo())
}

def topTest():
	topo = Test_Topo()
	net = Mininet(topo=topo, link=TCLink, controller=OVSController) 
	net.start()
	h2 = net.get('h2')
	h2int1 = h2.intf('h2-eth0')
	h1 = net.get('h1')
	print h1.cmd('ping -c 3 %s' % h2.IP(h2int1))
	print h1.cmd('bash /media/sf_Cours/Architecture_reseaux/TP1/hello.sh')
	CLI(net);
	net.stop();
	
if __name__=="__main__":
	setLogLevel('info')
	topTest()
