/**
 * Graph.java
 * @author Abdullah Mohammad
 * @author Chahid Bagdouri
 * CIS 22C, Lab 16
 */
import java.util.ArrayList;

public class Graph {
    private int vertices;
    private int edges;
    private ArrayList<LinkedList<Integer>> adj;
    private ArrayList<Character> color;
    private ArrayList<Integer> distance;
    private ArrayList<Integer> parent;
    private ArrayList<Integer> discoverTime;
    private ArrayList<Integer> finishTime;
    private static int time = 0;

    /** Constructors and Destructors */

    /**
     * initializes an empty graph, with n vertices and 0 edges
     *
     * @param numVtx the number of vertices in the graph
     * @precondition numVtx > 0
     * @throws IllegalArgumentException when numVtx <= 0
     */
    public Graph(int numVtx) throws IllegalArgumentException {
        if(numVtx <= 0) {
            throw new IllegalArgumentException("numVtx <= 0");
        }
        vertices = numVtx;
        edges = 0;

        adj = new ArrayList<LinkedList<Integer>>(vertices);
        color = new ArrayList<Character>(vertices);
        distance = new ArrayList<Integer>(vertices);
        parent = new ArrayList<Integer>(vertices);
        discoverTime = new ArrayList<Integer>(vertices);
        finishTime = new ArrayList<Integer>(vertices);

        for (int i = 0; i < vertices; i++) {
            adj.add(new LinkedList<Integer>());
            color.add('W');
            distance.add(-1);
            parent.add(0);
            discoverTime.add(-1);
            finishTime.add(-1);
        }
    }

    /*** Accessors ***/

    /**
     * Returns the number of edges in the graph
     *
     * @return the number of edges
     */
    public int getNumEdges() {
        return edges;
    }

    /**
     * Returns the number of vertices in the graph
     *
     * @return the number of vertices
     */
    public int getNumVertices() {
        return vertices;
    }

    /**
     * returns whether the graph is empty (no edges)
     *
     * @return whether the graph is empty
     */
    public boolean isEmpty() {
        return (edges == 0);
    }

    /**
     * Returns the value of the distance[v]
     *
     * @param v a vertex in the graph
     * @precondition 0 < v <= vertices
     * @return the distance of vertex v
     * @throws IndexOutOfBoundsException when v is out of bounds
     */
    public Integer getDistance(Integer v) throws IndexOutOfBoundsException {
        int one = 1;
        if((v <= 0) || (v > vertices)) {
            throw new IndexOutOfBoundsException("getDistance(): v <= 0 || v > vertices.");
        }
        return distance.get(v - one);
    }

    /**
     * Returns the value of the parent[v]
     *
     * @param v a vertex in the graph
     * @precondition 0 < v <= vertices
     * @return the parent of vertex v
     * @throws IndexOutOfBoundsException when v is out of bounds
     */
    public Integer getParent(Integer v) throws IndexOutOfBoundsException {
        int one = 1;
        if((v <= 0) || (v > vertices)) {
            throw new IndexOutOfBoundsException("getParent(): v <= 0 || v > vertices.");
        }
        return parent.get(v - one);
    }

    /**
     * Returns the value of the color[v]
     *
     * @param v a vertex in the graph
     * @precondition 0 < v <= vertices
     * @return the color of vertex v
     * @throws IndexOutOfBoundsException when v is out of bounds
     */
    public Character getColor(Integer v) throws IndexOutOfBoundsException {
        int one = 1;
        if((v <= 0) || (v > vertices)) {
            throw new IndexOutOfBoundsException("getColor(): v <= 0 || v > vertices.");
        }
        return color.get(v - one);
    }

    /**
     * Returns the value of the discoverTime[v]
     *
     * @param v a vertex in the graph
     * @precondition 0 < v <= vertices
     * @return the discover time of vertex v
     * @throws IndexOutOfBoundsException when v is out of bounds
     */
    public Integer getDiscoverTime(Integer v) throws IndexOutOfBoundsException {
        int one = 1;
        if((v <= 0) || (v > vertices)) {
            throw new IndexOutOfBoundsException("getDiscoverTime(): v <= 0 || v > vertices.");
        }
        return discoverTime.get(v - one);
    }

    /**
     * Returns the value of the finishTime[v]
     *
     * @param v a vertex in the graph
     * @precondition 0 < v <= vertices
     * @return the finish time of vertex v
     * @throws IndexOutOfBoundsException when v is out of bounds
     */
    public Integer getFinishTime(Integer v) throws IndexOutOfBoundsException {
        int one = 1;
        if((v <= 0) || (v > vertices)) {
            throw new IndexOutOfBoundsException("getFinishTime(): v <= 0 || v > vertices.");
        }
        return finishTime.get(v - one);
    }

    /**
     * Returns the LinkedList stored at index v
     *
     * @param v a vertex in the graph
     * @return the adjacency LinkedList at v
     * @precondition 0 < v <= vertices
     * @throws IndexOutOfBoundsException when v is out of bounds
     */
    public LinkedList<Integer> getAdjacencyList(Integer v) throws IndexOutOfBoundsException {
        int one = 1;
        if((v <= 0) || (v > vertices)) {
            throw new IndexOutOfBoundsException("getAdjacencyList(): v <= 0 || v > vertices.");
        }
        return adj.get(v - one);
    }

    /*** Manipulation Procedures ***/

    /**
     * Inserts vertex v into the adjacency list of vertex u (i.e. inserts v into
     * the list at index u) @precondition, 0 < u, v <= vertices
     *
     * @param u a vertex in the graph
     * @param v a vertex in the graph
     * @throws IndexOutOfBounds exception when u or v is out of bounds
     */
    public void addDirectedEdge(Integer u, Integer v) throws IndexOutOfBoundsException {
        int one = 1;
        if ((u <= 0) || (u > vertices) || ((v <= 0) || (v > vertices))) {
            throw new IndexOutOfBoundsException("addDirectedEdge(): u or v <= 0 || u or v > vertices.");
        }
        
        adj.get(u - one).addLast(v);
        
        edges++;
    }

    /**
     * Inserts vertex v into the adjacency list of vertex u (i.e. inserts v into
     * the list at index u) and inserts u into the adjacent vertex list of v.
     *
     * @param u a vertex in the graph
     * @param v a vertex in the graph
     * @precondition, 0 < u, v <= vertices
     * @throws IndexOutOfBoundsException when u or v is out of bounds
     */
    public void addUndirectedEdge(Integer u, Integer v) throws IndexOutOfBoundsException {
        int one = 1;
        if ((u <= 0) || (u > vertices) || ((v <= 0) || (v > vertices))) {
            throw new IndexOutOfBoundsException("addUndirectedEdge(): u or v <= 0 || u or v > vertices.");
        }

        adj.get(u - one).addLast(v);
        adj.get(v - one).addLast(u);

        edges++;
    }

    /*** Additional Operations ***/

    /**
     * Creates a String representation of the Graph Prints the adjacency list of
     * each vertex in the graph, vertex: <space separated list of adjacent
     * vertices>
     * @return a space separated list of adjacent vertices
     */
    @Override
    public String toString() {
        int one = 1;
        StringBuilder sb = new StringBuilder();
        
        if (vertices == 0) {
            return "";
        }

        for(int i = 0; i < vertices; i++) {
            sb.append((i + one) + ": " + adj.get(i).toString());
        }

        return sb.toString();
    }

    /**
     * Performs breath first search on this Graph give a source vertex
     *
     * @param source the starting vertex
     * @precondition source is a vertex in the graph
     * @throws IndexOutOfBoundsException when the source vertex is out of bounds
     *     of the graph
     */
    public void BFS(Integer source) throws IndexOutOfBoundsException {
        /*
        for all x in V(G)                
            color[x] = white             
            distance[x] = -1       
            parent[x] = Nil    
        */
        for (int i = 0; i < vertices; i++) {
            color.set(i, 'W');
            distance.set(i, -1);
            parent.set(i, 0);
        }


        /*
        color[s] = grey                 
        distance[s] = 0                  
        Enqueue(Q,s)
        */
        color.set(source - 1, 'G');
        distance.set(source - 1, 0);
        parent.set(source - 1, 0);

        
        /*
        while(Q is not empty)
            x = front of Q
            Dequeue(Q,x)
            for all y in adj[x]
                if color[y] == white
                    color[y] = grey
                    distance[y] = distance[x] + 1
                    parent[y] = x
                    Enqueue(Q, y)
            color[x] = black
        */
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.addLast(source);

        while (!queue.isEmpty()) {
            int x = queue.getFirst();
            queue.removeFirst();
            
            LinkedList<Integer> adjX = adj.get(x - 1);
            
            adjX.positionIterator();
            for (int i = 0; i < adjX.getLength(); i++) {
                int y = adjX.getIterator();

                if (getColor(y) == 'W') {
                    color.set(y - 1, 'G');
                    distance.set(y - 1, getDistance(x) + 1);
                    parent.set(y - 1, x);
                    queue.addLast(y);
                }
 
                adjX.advanceIterator();

            }
            color.set(x - 1, 'B');
        }
    }

    /**
     * Performs depth first search on this Graph in order of vertex lists
     */
    public void DFS() {
    }

    /**
     * Private recursive helper method for DFS
     *
     * @param vertex the vertex to visit
     */
    private void visit(int vertex) {
        
    }
}
