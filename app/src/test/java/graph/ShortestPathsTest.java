package graph;

import static org.junit.Assert.*;
import org.junit.FixMethodOrder;

import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.net.URL;
import java.io.FileNotFoundException;

import java.util.LinkedList;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShortestPathsTest {

    /* Performs the necessary gradle-related incantation to get the
       filename of a graph text file in the src/test/resources directory at
       test time.*/
    private String getGraphResource(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        return resource.getPath();
    }

    /* Returns the Graph loaded from the file with filename fn located in
     * src/test/resources at test time. */
    private Graph loadBasicGraph(String fn) {
        Graph result = null;
        String filePath = getGraphResource(fn);
        try {
          result = ShortestPaths.parseGraph("basic", filePath);
        } catch (FileNotFoundException e) {
          fail("Could not find graph " + fn);
        }
        return result;
    }

    /** Dummy test case demonstrating syntax to create a graph from scratch.
     * Write your own tests below. */
    @Test
    public void test00Nothing() {
        Graph g = new Graph();
        Node a = g.getNode("A");
        Node b = g.getNode("B");
        g.addEdge(a, b, 1);

        // sample assertion statements:
        assertTrue(true);
        assertEquals(2+2, 4);
    }

    /** Minimal test case to check the path from A to B in Simple0.txt */
    @Test
    public void test01Simple0() {
        Graph g = loadBasicGraph("Simple0.txt");
        g.report();
        ShortestPaths sp = new ShortestPaths();
        Node a = g.getNode("A");
        sp.compute(a);
        Node b = g.getNode("B");
        LinkedList<Node> abPath = sp.shortestPath(b);
        assertEquals(abPath.size(), 2);
        assertEquals(abPath.getFirst(), a);
        assertEquals(abPath.getLast(),  b);
        assertEquals(sp.shortestPathLength(b), 1.0, 1e-6);
    }

    // test case for shortest path length;
    @Test
    public void test01Simple1() {
        Graph g = loadBasicGraph("Simple1.txt");
        g.report();
        ShortestPaths sp = new ShortestPaths();
        Node s = g.getNode("S");
        Node a = g.getNode("A");
        Node c = g.getNode("C");
        Node b = g.getNode("B");
        sp.compute(s);

        assertEquals(sp.shortestPathLength(a), 8.0, 1e-6);
    }

    // test case for shortest path length to origin
    @Test
    public void test02Simple1() {
        Graph g = loadBasicGraph("Simple1.txt");
        g.report();
        ShortestPaths sp = new ShortestPaths();
        Node s = g.getNode("S");
        sp.compute(s);

        assertEquals(sp.shortestPathLength(s), 0.0, 1e-6);
    }
    // test case for finding distance between s & a
    @Test
    public void test03Simple1() {
        Graph g = loadBasicGraph("Simple1.txt");
        g.report();
        ShortestPaths sp = new ShortestPaths();
        Node s = g.getNode("S");
        sp.compute(s);
        Node a = g.getNode("A");
        LinkedList<Node> saPath = sp.shortestPath(a);
        assertEquals(saPath.size(), 3);
        assertEquals(saPath.getFirst(), s);
        assertEquals(saPath.getLast(), a);
        assertEquals(sp.shortestPathLength(a), 8.0, 1e-6);
    }

    // test case for finding distance between b & s
    @Test
    public void test04Simple1() {
        Graph g = loadBasicGraph("Simple1.txt");
        g.report();
        ShortestPaths sp = new ShortestPaths();
        Node b = g.getNode("B");
        sp.compute(b);
        Node s = g.getNode("S");
        LinkedList<Node> bsPath = sp.shortestPath(s);
        assertEquals(bsPath.size(), 3);
        assertEquals(bsPath.getFirst(), b);
        assertEquals(bsPath.getLast(), s);
        assertEquals(sp.shortestPathLength(s), 5.0, 1e-6);
    }

    // test case for finding path starting at a to get to g
    @Test
    public void test01Simple2() {
        Graph gr = loadBasicGraph("Simple2.txt");
        gr.report();
        ShortestPaths sp = new ShortestPaths();
        Node a = gr.getNode("A");
        sp.compute(a);
        Node g = gr.getNode("G");
        LinkedList<Node> agPath = sp.shortestPath(g);
        assertEquals(agPath.size(), 6);
        assertEquals(agPath.getFirst(), a);
        assertEquals(agPath.getLast(), g);
        assertEquals(sp.shortestPathLength(g), 8.0, 1e-6);
    }

    // test case for finding path that doesn't exist
    @Test
    public void test02Simple2() {
        Graph gr = loadBasicGraph("Simple2.txt");
        gr.report();
        ShortestPaths sp = new ShortestPaths();
        Node h = gr.getNode("H");
        Node a = gr.getNode("A");
        Node d = gr.getNode("D");
        sp.compute(h);
        assertEquals(sp.shortestPathLength(a), Double.POSITIVE_INFINITY, 1e-6);
    }
    /* Pro tip: unless you include @Test on the line above your method header,
     * gradle test will not run it! This gets me every time. */
}
