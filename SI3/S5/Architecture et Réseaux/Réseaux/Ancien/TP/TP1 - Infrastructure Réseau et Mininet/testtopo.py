from mininet.net import Mininet
from mininet.topo import Topo
from mininet.node import Node
from mininet.cli import CLI
from mininet.link import TCLink
from mininet.log import setLogLevel
from mininet.node import Controller, RemoteController, OVSController

class Test_Topo(Topo):
    def __init__(self):

        "Create P2P topology"
        # Initialize topology
        Topo.__init__(self)
        
        # Add hosts and switches
        h1 = self.addNode('h1')
        h2 = self.addNode('h2')
        s1 = self.addSwitch('s1')

        # Add links
        self.addLink(h1, s1, bw=10)
        self.addLink(h2, s1)

topos = {
    'toptest': (lambda: Test_Topo())
}

def topTest():
    topo = Test_Topo()
    net = Mininet(controller=RemoteController, topo=topo, link=TCLink)
    net.start()
    h1 = net.get('h1')
    print h1.cmd('ping -c 3 10.0.0.2')
    CLI(net) ; # sans cette ligne, on ne verrait jamais le CLI
    net.stop() ; # ne pas oublier de detruire le reseau

if __name__ == '__main__':
    setLogLevel('info')
    topTest()