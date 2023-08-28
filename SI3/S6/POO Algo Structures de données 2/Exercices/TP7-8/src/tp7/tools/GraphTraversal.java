package tp7.tools;


import tp7.AbstractGraph;
import tp7.Vertex;

import java.util.*;

public class GraphTraversal {

    /* -------------------- DFS ---------------------- */

    /**
     * Returns the list of vertices of 'graph' in DFS order
     *
     * @param graph
     * @param start
     * @return
     */
    public static List<Vertex> dfs(AbstractGraph graph, Vertex start) {
        List<Vertex> list = new ArrayList<>();
        return dfsUtil(graph, start, list);
    }

    public static List<Vertex> dfsUtil(AbstractGraph graph, Vertex start, List<Vertex> list) {
        // check if visited before
        if (!list.contains(start)) {
            // add to list
            list.add(start);

            // add all adjacents to queue
            for (Vertex n : graph.adjacents(start)) {
                dfsUtil(graph, n, list);
            }
        }
        return list;
    }



    /* -------------------- BFS ---------------------- */

    /**
     * Returns the list of vertices of 'graph' in BFS order
     *
     * @param graph
     * @param start
     * @return
     */
    public static List<Vertex> bfs(AbstractGraph graph, Vertex start) {
        List<Vertex> list = new ArrayList<>();
        Queue<Vertex> queue = new LinkedList<>();
        // add start to queue
        queue.add(start);
        while (!queue.isEmpty()) {
            // get first element
            Vertex v = queue.poll();
            // check if visited before
            if (!list.contains(v)) {
                // add to list
                list.add(v);

                // add all adjacents to queue
                for (Vertex n : graph.adjacents(v)) {
                    queue.add(n);
                }
            }
        }
        return list;
    }


    /* -------------------- Dijkstra ---------------------- */

    public static List<Vertex> dijkstra(AbstractGraph graph, Vertex start, Vertex end) {
        Map<Vertex, Vertex> parents = new HashMap<>();
        Map<Vertex, Integer> distances = new HashMap<>();

        for (Vertex v : graph.vertices()) {
            parents.put(v, null);
            distances.put(v, Integer.MAX_VALUE);
        }

        distances.put(start, 0);

        PriorityQueue<Vertex> queue = new PriorityQueue<>(graph.nbVertices(), new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return distances.get(o1) - distances.get(o2);
            }
        });

        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();

            for (Vertex v : graph.adjacents(current)) {
                int newDistance = ((int) (distances.get(current) + graph.findEdge(current, v).weight()));
                if (newDistance < distances.get(v)) {
                    distances.put(v, newDistance);
                    parents.put(v, current);
                    queue.add(v);
                }
            }
        }

        List<Vertex> path = new ArrayList<>();
        Vertex current = end;
        while (current != null) {
            path.add(current);
            current = parents.get(current);
        }

        Collections.reverse(path);
        return path;
    }
}
