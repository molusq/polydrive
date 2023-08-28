from mininet.topo import Topo

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
        self.addLink(h1, s1)
        self.addLink(h2, s1)

topos = {
    'toptest': (lambda: Test_Topo())
}