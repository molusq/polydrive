package tp7.tools;


import tp7.*;

import java.util.*;
import java.util.stream.StreamSupport;

/**
 * A class to find cycles in undirected and directed graphs
 */
public class GraphHelper {

    private Graph aGraph;


    /////////////// Cycle detection for undirected graphs

    public GraphHelper() {
        //TO IMPLEMENT
    }

    /**
     * Returns true if the graph 'G' is cyclic
     * false otherwise
     */
    public boolean hasCycle(UnDiGraph unDiGraph) {
        Set<Vertex> visited = new HashSet<>();

        for (Vertex v : unDiGraph.vertices()) {
            if (!visited.contains(v)) {
                List<Vertex> dfs = GraphTraversal.dfs(unDiGraph, v);
                Vertex previous = null;
                for (Vertex vertex : dfs) {
                    Vertex finalPrevious = previous;
                    List<Vertex> adjacents = StreamSupport.stream(unDiGraph.adjacents(vertex).spliterator(), false).toList();
                    for (Vertex adjacent : adjacents) {
                        if (adjacent != finalPrevious && visited.contains(adjacent) && adjacents.contains(finalPrevious)) {
                            return true;
                        }
                    }
                    visited.add(vertex);
                    previous = vertex;
                }


            }
        }


        return false;
    }

    /**
     * Returns true if a cycle was found by traversing
     * the graph, coming from vertex from and going by vertex u
     * Precondition: vertex 'from' is visited and vertex 'u' is
     * not visited
     */
    private boolean hasCycle(Vertex u, Vertex from) {
        return false;
    }

    /////////////// Cycle detection for directed graphs

    private enum Status {UN_DISCOVERED, IN_PROGRESS, COMPLETED} // status of vertex

    private boolean isCyclicUtil(Vertex v, Map<Vertex, Status> statusMap, DiGraph diGraph) {
        statusMap.put(v, Status.IN_PROGRESS);

        List<Vertex> children = StreamSupport.stream(diGraph.adjacents(v).spliterator(), false).toList();

        for (Vertex c : children)
            if (statusMap.get(c) == Status.UN_DISCOVERED) {
                if (isCyclicUtil(c, statusMap, diGraph))
                    return true;
            } else if (statusMap.get(c) == Status.IN_PROGRESS)
                return true;
        statusMap.put(v, Status.COMPLETED);

        return false;
    }

    /**
     * Returns true if the graph 'G' is cyclic
     * false otherwise
     */
    public boolean hasCycle(DiGraph diGraph) {
        Map<Vertex, Status> statusMap = new HashMap<>();
        for (Vertex v : diGraph.vertices()) {
            statusMap.put(v, Status.UN_DISCOVERED);
        }

        for (Vertex v : diGraph.vertices()) {
            if (isCyclicUtil(v, statusMap, diGraph)) {
                return true;
            }
        }

        return false;

    }

    /**
     * Returns true if a cycle was found by traversing
     * the graph from vertex u
     * Precondition: vertex 'u' is 'UnDiscovered'
     */
    private boolean hasCycle(Vertex u) {
        return false;
    }



    /* ------------------- Path finding ------------------- */

    /**
     * Returns a path as from vertex 'u' to vertex 'v' in the graph 'G'
     * as a list of vertices if such a path exists, the empty list otherwise
     */
    public List<Vertex> findPath(Graph graph, Vertex start, Vertex end) {
        Map<Vertex, Vertex> parentMap = new HashMap<>();
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(start);
        parentMap.put(start, null);
        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            if (current == end) {
                return rebuildPath(parentMap, start, end);
            }
            for (Vertex adjacent : graph.adjacents(current)) {
                if (!parentMap.containsKey(adjacent)) {
                    parentMap.put(adjacent, current);
                    queue.add(adjacent);
                }
            }
        }

        return new ArrayList<>();
    }

    private List<Vertex> rebuildPath(Map<Vertex, Vertex> parentMap, Vertex start, Vertex end) {
        List<Vertex> path = new ArrayList<>();
        Vertex current = end;
        while (current != start) {
            path.add(current);
            current = parentMap.get(current);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }

    /* ------------------- Root finding ------------------- */

    /**
     * Returns a root of the graph 'G' if
     * such root exists, null otherwise
     */
    public Optional<Vertex> findRoot(DiGraph diGraph) {
        for (Vertex v : diGraph.vertices()) {
            List<Vertex> dfs = GraphTraversal.dfs(diGraph, v);
            if (new HashSet<>(dfs).containsAll(StreamSupport.stream(diGraph.vertices().spliterator(), false).toList())) {
                return Optional.of(v);
            }
        }
        return Optional.empty();
    }


    public double computeDistanceOfPath(List<Vertex> path, AbstractGraph graph) {
        double distance = 0.0;
        for (int i = 0; i < path.size() - 1; i++) {
            distance += graph.findEdge(path.get(i), path.get(i + 1)).weight();
        }
        return distance;
    }

    ///////////////

}
