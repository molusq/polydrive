package tp7.tools;

import tp7.GraphReader;
import tp7.UnDiGraph;
import tp7.Vertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A class to find connected components from an undirected graphs
 */
public class ConnectedComponents {

    private Map<Vertex, Integer> connectedComponents = new HashMap<>();
    private UnDiGraph unDiGraph; // the undirected graph

    private int connectedComponentNumber = 0;

    protected int getMaxConnectedComponentNumber() {
        return connectedComponentNumber;
    }


    public ConnectedComponents(UnDiGraph u1) {

        Set<Vertex> visited = new HashSet<>();

        for (Vertex v : u1.vertices()) {
            if (!visited.contains(v)) {
                connectedComponentNumber++;
                GraphTraversal.bfs(u1, v).forEach(vertex -> {
                            setNumber(vertex, connectedComponentNumber);
                            visited.add(vertex);
                        }
                );

            }


        }

    }

    /**
     * Returns the map of the connected components of 'G'
     * If cc is the returned map, then, for all vertices u and v,
     * cc.get(u) = cc.get(v) if and only if u and v are in the same
     * connected component
     */
    public Map<Vertex, Integer> find() {
        return connectedComponents;
    }


    private void setNumber(Vertex u, int number) {
        connectedComponents.put(u, number);
    }

    public static void main(String[] s) {


        UnDiGraph U1 = GraphReader.unDiGraph("A B C D C E D E E F G H G K H I H J H K I J");
        UnDiGraph U2 = GraphReader.unDiGraph("A D A E A J B C B F B G B I C F C G C H D E D F G H");

        ConnectedComponents ccFinder = new ConnectedComponents(U1);

        Map<Vertex, Integer> cc = ccFinder.find();
        System.out.println(cc);
        System.out.println("maxConnectedComponentNumber = " + ccFinder.connectedComponentNumber);

        ccFinder = new ConnectedComponents(U2);
        cc = ccFinder.find();
        System.out.println(cc);
        System.out.println("maxConnectedComponentNumber = " + ccFinder.connectedComponentNumber);
    }
}
