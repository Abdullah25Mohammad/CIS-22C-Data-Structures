/**
 * LabProgram.java
 * CIS 22C, Lab 15
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class LabProgram {
    private final int SIZE = 10;

    public static void main(String[] args) {
        LabProgram lab = new LabProgram();

        msg("Calling testGraph()");
        if (lab.testGraph() == 0) {
            msg("-No errors found");
        }

        msg("Calling testGetNumVertices()");
        if (lab.testGetNumVertices() == 0) {
            msg("-No errors found");
        }

        msg("Calling testToString()");
        if (lab.testToString() == 0) {
            msg("-No errors found");
        }

        msg("Calling testAddUndirectedEdge()");
        if (lab.testAddUndirectedEdge() == 0) {
            msg("-No errors found");
        }

        msg("Calling testAddDirectedEdge()");
        if (lab.testAddDirectedEdge() == 0) {
            msg("-No errors found");
        }

        msg("Calling testGetNumEdges()");
        if (lab.testGetNumEdges() == 0) {
            msg("-No errors found");
        }

        msg("Calling testIsEmpty()");
        if (lab.testIsEmpty() == 0) {
            msg("-No errors found");
        }

        msg("Calling testGetDistance()");
        if (lab.testGetDistance() == 0) {
            msg("-No errors found");
        }

        msg("Calling testGetParent()");
        if (lab.testGetParent() == 0) {
            msg("-No errors found");
        }

        msg("Calling testGetColor()");
        if (lab.testGetColor() == 0) {
            msg("-No errors found");
        }

        msg("Calling testGetDiscoverTime()");
        if (lab.testGetDiscoverTime() == 0) {
            msg("-No errors found");
        }

        msg("Calling testGetFinishTime()");
        if (lab.testGetFinishTime() == 0) {
            msg("-No errors found");
        }

        msg("Calling testGetAdjacencyList()");
        if (lab.testGetAdjacencyList() == 0) {
            msg("-No errors found");
        }
    }

    private static void msg(String message) {
        System.out.println(message);
    }

    // Shows newlines in strings as \n
    private String showNewLines(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\n') {
                result.append("\\" + "n");
            } else {
                result.append(str.charAt(i));
            }
        }
        return result.toString();
    }

    private int testGraph() {
        int cntErr = 0;
        Graph grf = new Graph(10);
        //assertEquals(10, grf.getNumVertices());
        int numVtx = grf.getNumVertices();
        if (numVtx != 10) {
            msg("-getNumVertices() must return 10 not " + numVtx
                + " when Graph constructed with 10 vertices.");
            cntErr++;
        }
        //assertEquals(0, grf.getNumEdges());
        int numEdg = grf.getNumEdges();
        if (numEdg != 0) {
            msg("-getNumEdges() must return 0 not " + numEdg
                + " when Graph constructed.");
            cntErr++;
        }
        //assertEquals('W', grf.getColor(10));
        Character clr = grf.getColor(10);
        if (clr != 'W') {
            msg("-getColor(10) must return 'W' not '" + clr
                + "' when Graph constructed.");
            cntErr++;
        }
        //assertEquals(-1, grf.getDistance(10));
        int dist = grf.getDistance(10);
        if (dist != -1) {
            msg("-getDistance(10) must return -1 not " + dist
                + " when Graph constructed.");
            cntErr++;
        }
        //assertThrows(IllegalArgumentException.class, ()->{new Graph(0);});
        try {
            grf = new Graph(0);
            msg("-Must throw IllegalArgumentException when size is <= 0.");
            cntErr++;
        } catch(IllegalArgumentException e) {
            // thrown as expected
        }
        //assertThrows(IllegalArgumentException.class, ()->{new Graph(-1);});
        try {
            grf = new Graph(-1);
            msg("-Must throw IllegalArgumentException when size is <= 0.");
            cntErr++;
        } catch(IllegalArgumentException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testGetNumVertices() {
        int cntErr = 0, numVtx = 0;
        Graph grf = new Graph(10);
        //assertEquals(10, g.getNumVertices());
        numVtx = grf.getNumVertices();
        if (numVtx != 10) {
            msg("-getNumVertices() must return 10 not " + numVtx
                + " when Graph constructed with 10 vertices.");
            cntErr++;
        }
        grf = new Graph(5);
        //assertEquals(5, g.getNumVertices());
        numVtx = grf.getNumVertices();
        if (numVtx != 5) {
            msg("-getNumVertices() must return 5 not " + numVtx
                + " when Graph constructed with 5 vertices.");
            cntErr++;
        }
        return cntErr;
    }

    private int testToString() {
        int cntErr = 0;
        Graph grf = new Graph(4);
        String str = "";
        //assertEquals("1: \n" +
        //        "2: \n" +
        //        "3: \n" +
        //        "4: \n", grf.toString());
        str = grf.toString();
        if (!str.equals(
            "1: \n" +
            "2: \n" +
            "3: \n" +
            "4: \n")) {
            msg("-After new Graph(4), toString() must return:\n\"" +
            "1: \n" +
            "2: \n" +
            "3: \n" +
            "4: \n\" \nNOT:\n\"" + str + "\"");
        }
        grf.addUndirectedEdge(1, 2);
        grf.addUndirectedEdge(1, 3);
        grf.addUndirectedEdge(2, 4);
        grf.addUndirectedEdge(3, 4);
        //assertEquals("1: 2 3 \n" +
        //        "2: 1 4 \n" +
        //        "3: 1 4 \n" +
        //        "4: 2 3 \n", grf.toString());
        str = grf.toString();
        if (!str.equals(
            "1: 2 3 \n" +
            "2: 1 4 \n" +
            "3: 1 4 \n" +
            "4: 2 3 \n")) {
            msg("-After adding 4 undirected edges, toString() must return:\n\"" +
                "1: 2 3 \n" +
                "2: 1 4 \n" +
                "3: 1 4 \n" +
                "4: 2 3 \n\" \nNOT:\n\"" + str + "\"");
        }
        return cntErr;
    }

    private int testAddUndirectedEdge() {
        int cntErr = 0;
        Graph grf = new Graph(4);
        grf.addUndirectedEdge(1, 2);
        grf.addUndirectedEdge(1, 3);
        grf.addUndirectedEdge(2, 4);
        grf.addUndirectedEdge(3, 4);
        //assertEquals("1: 2 3 \n" +
        //        "2: 1 4 \n" +
        //        "3: 1 4 \n" +
        //        "4: 2 3 \n", grf.toString());
        String str = grf.toString();
        if (!str.equals(
            "1: 2 3 \n" +
            "2: 1 4 \n" +
            "3: 1 4 \n" +
            "4: 2 3 \n")) {
            msg("-After adding 4 undirected edges, toString() must return:\n\"" +
                "1: 2 3 \n" +
                "2: 1 4 \n" +
                "3: 1 4 \n" +
                "4: 2 3 \n\" \nNOT:\n\"" + str + "\"");
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{grf.addUndirectedEdge(5, 2);});
        try {
            grf.addUndirectedEdge(5, 2);
            msg("-addUndirectedEdge(5, 2) must throw IndexOutOfBoundsException"
                + " when u = 5 for a 4 node Graph.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{grf.addUndirectedEdge(0, 2);});
        try {
            grf.addUndirectedEdge(0, 2);
            msg("-addUndirectedEdge(0, 2) must throw IndexOutOfBoundsException"
                + " when u = 0 because Nodes start at 1.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{grf.addUndirectedEdge(2, 5);});
        try {
            grf.addUndirectedEdge(2, 5);
            msg("-addUndirectedEdge(2, 5) must throw IndexOutOfBoundsException"
                + " when v = 5 for a 4 node Graph.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{grf.addUndirectedEdge(2, 0);});
        try {
            grf.addUndirectedEdge(2, 0);
            msg("-addUndirectedEdge(0, 2) must throw IndexOutOfBoundsException"
                + " when v = 0 because Nodes start at 1.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }

        return cntErr;
    }

    private int testAddDirectedEdge() {
        int cntErr = 0;
        Graph grf = new Graph(4);
        grf.addDirectedEdge(1, 2);
        grf.addDirectedEdge(1, 3);
        grf.addDirectedEdge(2, 4);
        grf.addDirectedEdge(3, 4);
        //assertEquals("1: 2 3 \n" +
        //        "2: 4 \n" +
        //        "3: 4 \n" +
        //        "4: \n", grf.toString());
        String str = grf.toString();
        if (!str.equals(
            "1: 2 3 \n" +
            "2: 4 \n" +
            "3: 4 \n" +
            "4: \n")) {
            msg("-After adding 4 directed edges, toString() must return:\n\"" +
                "1: 2 3 \n" +
                "2: 4 \n" +
                "3: 4 \n" +
                "4: \n\" \nNOT:\n\"" + str + "\"");
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{grf.addDirectedEdge(5, 2);});
        try {
            grf.addDirectedEdge(5, 2);
            msg("-addDirectedEdge(5, 2) must throw IndexOutOfBoundsException"
                + " when u = 5 for a 4 node Graph.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{grf.addDirectedEdge(0, 2);});
        try {
            grf.addDirectedEdge(0, 2);
            msg("-addDirectedEdge(0, 2) must throw IndexOutOfBoundsException"
                + " when u = 0 because Nodes start at 1.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{grf.addDirectedEdge(2, 5);});
        try {
            grf.addDirectedEdge(2, 5);
            msg("-addDirectedEdge(2, 5) must throw IndexOutOfBoundsException"
                + " when v = 5 for a 4 node Graph.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{grf.addDirectedEdge(2, 0);});
        try {
            grf.addDirectedEdge(2, 0);
            msg("-addDirectedEdge(2, 0) must throw IndexOutOfBoundsException"
                + " when v = 0 because Nodes start at 1.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testGetNumEdges() {
        int cntErr = 0, numEdg = 0;
        Graph grf = new Graph(10);
        //assertEquals(0, grf.getNumEdges());
        numEdg = grf.getNumEdges();
        if (numEdg != 0) {
            msg("-getNumEdges() must return 0 not " + numEdg
                + " when Graph first constructed.");
            cntErr++;
        }
        grf.addDirectedEdge(1, 10);
        grf.addDirectedEdge(2,  4);
        //assertEquals(2, grf.getNumEdges());
        numEdg = grf.getNumEdges();
        if (numEdg != 2) {
            msg("-getNumEdges() must return 2 not " + numEdg
                + " after adding 2 directed edges to new Graph.");
            cntErr++;
        }
        grf = new Graph(5);
        grf.addUndirectedEdge(2, 3);
        grf.addUndirectedEdge(1, 4);
        grf.addUndirectedEdge(4, 5);
        //assertEquals(3, grf.getNumEdges());
        numEdg = grf.getNumEdges();
        if (numEdg != 3) {
            msg("-getNumEdges() must return 3 not " + numEdg
                + " after adding 3 undirected edges to new Graph.");
            cntErr++;
        }
        return cntErr;
    }

    private int testIsEmpty() {
        int cntErr = 0;
        Graph grf = new Graph(10);
        //assertTrue(grf.isEmpty());
        if (!grf.isEmpty()) {
            msg("-isEmpty() must return true after constructing new Graph.");
            cntErr++;
        }
        grf.addDirectedEdge(2, 4);
        //assertFalse(grf.isEmpty());
        if (grf.isEmpty()) {
            msg("-isEmpty() must return false after adding a directed edge.");
            cntErr++;
        }
        return cntErr;
    }

    private int testGetDistance() {
        int cntErr = 0, dist = 0;
        Graph grf = new Graph(4);
        grf.addUndirectedEdge(1, 2);
        grf.addUndirectedEdge(2, 3);
        grf.addUndirectedEdge(3, 4);
        //assertEquals(-1, grf.getDistance(3));
        dist = grf.getDistance(3);
        if (dist != -1) {
            msg("-getDistance(3) must return -1 not " + dist
                + " before calling a search method.");
            cntErr++;
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{grf.getDistance(5);});
        try {
            grf.getDistance(5);
            msg("-getDistance(5) must throw IndexOutOfBoundsException"
                + " for a Graph with 4 nodes.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{grf.getDistance(0);});
        try {
            grf.getDistance(0);
            msg("-getDistance(0) must throw IndexOutOfBoundsException"
                + " because node numbering starts with 1.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testGetParent() {
        int cntErr = 0, prnt = 0;
        Graph grf = new Graph(4);
        grf.addUndirectedEdge(1, 2);
        grf.addUndirectedEdge(2, 3);
        grf.addUndirectedEdge(3, 4);
        //assertEquals(0, grf.getParent(3));
        prnt = grf.getParent(3);
        if (prnt != 0) {
            msg("-getParent(3) must return 0 not " + prnt
                + " before calling a search method.");
            cntErr++;
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{grf.getParent(5);});
        try {
            grf.getParent(5);
            msg("-getParent(5) must throw IndexOutOfBoundsException"
                + " for a Graph with 4 nodes.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{grf.getParent(0);});
        try {
            grf.getParent(0);
            msg("-getParent(0) must throw IndexOutOfBoundsException"
                + " because node numbering starts with 1.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testGetColor() {
        int cntErr = 0;
        char clr = 0;
        Graph grf = new Graph(4);
        grf.addUndirectedEdge(1, 2);
        grf.addUndirectedEdge(2, 3);
        grf.addUndirectedEdge(3, 4);
        //assertEquals('W', grf.getColor(3));
        clr = grf.getColor(3);
        if (clr != 'W') {
            msg("-getColor(3) must return 'W' not '" + clr
                + "' before calling a search method.");
            cntErr++;
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{grf.getColor(5);});
        try {
            grf.getColor(5);
            msg("-getColor(5) must throw IndexOutOfBoundsException"
                + " for a Graph with 4 nodes.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{grf.getColor(0);});
        try {
            grf.getColor(0);
            msg("-getColor(0) must throw IndexOutOfBoundsException"
                + " because node numbering starts with 1.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testGetDiscoverTime() {
        int cntErr = 0, dt = 0;
        Graph grf = new Graph(5);
        grf.addDirectedEdge(1, 2);
        grf.addDirectedEdge(2, 3);
        grf.addDirectedEdge(3, 4);
        grf.addDirectedEdge(2, 5);
        //assertEquals(-1, grf.getDiscoverTime(3));
        dt = grf.getDiscoverTime(3);
        if (dt != -1) {
            msg("-getDiscoverTime(3) must return -1 not " + dt
                + " before calling a depth-first search method.");
            cntErr++;
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{grf.getDiscoverTime(6);});
        try {
            grf.getDiscoverTime(6);
            msg("-getDiscoverTime(6) must throw IndexOutOfBoundsException"
                + " for a Graph with 5 nodes.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{grf.getDiscoverTime(0);});
        try {
            grf.getDiscoverTime(0);
            msg("-getDiscoverTime(0) must throw IndexOutOfBoundsException"
                + " because node numbering starts with 1.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testGetFinishTime() {
        int cntErr = 0, ft = 0;
        Graph grf = new Graph(5);
        grf.addDirectedEdge(1, 2);
        grf.addDirectedEdge(2, 3);
        grf.addDirectedEdge(3, 4);
        grf.addDirectedEdge(2, 5);
        //assertEquals(-1, grf.getFinishTime(3));
        ft = grf.getFinishTime(3);
        if (ft != -1) {
            msg("-getFinishTime(3) must return -1 not " + ft
                + " before calling a depth-first search method.");
            cntErr++;
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{grf.getFinishTime(6);});
        try {
            grf.getFinishTime(6);
            msg("-getFinishTime(6) must throw IndexOutOfBoundsException"
                + " for a Graph with 5 nodes.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{grf.getFinishTime(0);});
        try {
            grf.getFinishTime(0);
            msg("-getFinishTime(0) must throw IndexOutOfBoundsException"
                + " because node numbering starts with 1.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testGetAdjacencyList() {
        int cntErr = 0;
        String str = "";
        Graph grf = new Graph(4);
        grf.addUndirectedEdge(1, 2);
        grf.addUndirectedEdge(1, 3);
        grf.addUndirectedEdge(2, 4);
        grf.addUndirectedEdge(3, 4);
        //assertEquals("2 3 \n", grf.getAdjacencyList(1).toString());
        str = grf.getAdjacencyList(1).toString();
        if (!str.equals("2 3 \n")) {
            msg("-getAdjacencyList(3) must return \"2 3 \\n\" not \""
                + showNewLines(str) + "\".");
            cntErr++;
        }
        //assertEquals("1 4 \n", grf.getAdjacencyList(2).toString());
        str = grf.getAdjacencyList(2).toString();
        if (!str.equals("1 4 \n")) {
            msg("-getAdjacencyList(3) must return \"1 4 \\n\" not \""
                + showNewLines(str) + "\".");
            cntErr++;
        }
        //assertEquals("1 4 \n", grf.getAdjacencyList(3).toString());
        str = grf.getAdjacencyList(3).toString();
        if (!str.equals("1 4 \n")) {
            msg("-getAdjacencyList(3) must return \"1 4 \\n\" not \""
                + showNewLines(str) + "\".");
            cntErr++;
        }
        //assertEquals("3 4 \n", grf.getAdjacencyList(4).toString());
        str = grf.getAdjacencyList(4).toString();
        if (!str.equals("2 3 \n")) {
            msg("-getAdjacencyList(4) must return \"2 3 \\n\" not \""
                + showNewLines(str) + "\".");
            cntErr++;
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{grf.getAdjacencyList(5);});
        try {
            grf.getAdjacencyList(5);
            msg("-getAdjacencyList(5) must throw IndexOutOfBoundsException"
                + " for a Graph with 4 nodes.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{grf.getAdjacencyList(0);});
        try {
            grf.getAdjacencyList(0);
            msg("-getAdjacencyList(0) must throw IndexOutOfBoundsException"
                + " because node numbering starts with 1.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        return cntErr;
    }
}
