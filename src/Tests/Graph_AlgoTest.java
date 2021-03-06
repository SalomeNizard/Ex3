import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.graph;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class Graph_AlgoTest {
//    public static Graph_Algo g;

    public graph_algorithms create_graph() {
        graph graphs = new DGraph();
        Graph_Algo g = new Graph_Algo(graphs);
        NodeData n1 = new NodeData(1);
        NodeData n2 = new NodeData(2);
        NodeData n3 = new NodeData(3);
        NodeData n4 = new NodeData(4);
        NodeData n5 = new NodeData(5);
        NodeData n6 = new NodeData(6);
        NodeData n7 = new NodeData(7);
        graphs.addNode(n1);
        graphs.addNode(n2);
        graphs.addNode(n3);
        graphs.addNode(n4);
        graphs.addNode(n5);
        graphs.addNode(n6);
        graphs.addNode(n7);
        graphs.connect(2, 1, 4);
        graphs.connect(1, 3, 3);
        graphs.connect(1, 5, 20);
        graphs.connect(3, 2, 6);
        graphs.connect(2, 4, 5);
        graphs.connect(4, 6, 2);
        graphs.connect(6, 7, 3);
        graphs.connect(7, 5, 5);
        graphs.connect(3, 4, 11);
        graphs.connect(4, 5, 10);
        graphs.connect(5, 3, 8);
        return g;
    }

    @Test
    public void save_init() throws IOException {
        graph_algorithms gr = create_graph();
        gr.save("file.txt");
        graph_algorithms g2 = new Graph_Algo();
        graph_algorithms gra = new Graph_Algo();
        gra.init("file.txt");
        assertEquals(gr.shortestPath(1,7).toString() , gra.shortestPath(1,7).toString());
    }

    @Test
    public void isConnected() {
        graph_algorithms gr = create_graph();
        assertEquals(true , gr.isConnected());
    }

    @Test
    public void shortestPathDist() {
        graph_algorithms gr = create_graph();
        assertEquals(19 , gr.shortestPathDist(1 , 7) , 0.0000001);
        assertEquals(5 , gr.shortestPathDist(4 , 7) , 0.0000001);
        assertEquals(14 , gr.shortestPathDist(1 , 4) , 0.0000001);
        assertEquals(23 , gr.shortestPathDist(7 , 1) , 0.0000001);
    }

    @Test
    public void shortestPath() {
        graph_algorithms gr = create_graph();
        assertEquals("[key: 1, edges: [src: 1 dest: 3, src: 1 dest: 5], key: 3, edges: [src: 3 dest: 2, src: 3 dest: 4], key: 4, edges: [src: 4 dest: 5, src: 4 dest: 6], key: 6, edges: [src: 6 dest: 7], key: 7, edges: [src: 7 dest: 5]]" , gr.shortestPath(1,7).toString());
        assertEquals("[key: 4, edges: [src: 4 dest: 5, src: 4 dest: 6], key: 6, edges: [src: 6 dest: 7], key: 7, edges: [src: 7 dest: 5]]" , gr.shortestPath(4,7).toString());
        assertEquals("[key: 1, edges: [src: 1 dest: 3, src: 1 dest: 5], key: 3, edges: [src: 3 dest: 2, src: 3 dest: 4], key: 4, edges: [src: 4 dest: 5, src: 4 dest: 6]]" , gr.shortestPath(1,4).toString());
        assertEquals("[key: 7, edges: [src: 7 dest: 5], key: 5, edges: [src: 5 dest: 3], key: 3, edges: [src: 3 dest: 2, src: 3 dest: 4], key: 2, edges: [src: 2 dest: 1, src: 2 dest: 4], key: 1, edges: [src: 1 dest: 3, src: 1 dest: 5]]" , gr.shortestPath(7,1).toString());
    }

    @Test
    public void TSP() {
        graph_algorithms gr = create_graph();
        List<Integer> target = new LinkedList<>();
        target.add(1);
        target.add(2);
        target.add(3);
        target.add(4);
        target.add(5);
        assertEquals("[key: 1, edges: [src: 1 dest: 3, src: 1 dest: 5], key: 3, edges: [src: 3 dest: 2, src: 3 dest: 4], key: 2, edges: [src: 2 dest: 1, src: 2 dest: 4], key: 4, edges: [src: 4 dest: 5, src: 4 dest: 6], key: 5, edges: [src: 5 dest: 3]]" , gr.TSP(target).toString());
        List<Integer> target1 = new LinkedList<>();
        target1.add(6);
        target1.add(2);
        target1.add(4);
        target1.add(1);
        target1.add(7);
        assertEquals("[key: 6, edges: [src: 6 dest: 7], key: 7, edges: [src: 7 dest: 5], key: 5, edges: [src: 5 dest: 3], key: 3, edges: [src: 3 dest: 2, src: 3 dest: 4], key: 2, edges: [src: 2 dest: 1, src: 2 dest: 4], key: 4, edges: [src: 4 dest: 5, src: 4 dest: 6], key: 5, edges: [src: 5 dest: 3], key: 3, edges: [src: 3 dest: 2, src: 3 dest: 4], key: 2, edges: [src: 2 dest: 1, src: 2 dest: 4], key: 1, edges: [src: 1 dest: 3, src: 1 dest: 5]]" , gr.TSP(target1).toString());
    }

    @Test
    public void copy() {
        graph_algorithms gr = create_graph();
        graph g1 = gr.copy();
        assertEquals("[key: 1, edges: [src: 1 dest: 3, src: 1 dest: 5], key: 2, edges: [src: 2 dest: 1, src: 2 dest: 4], key: 3, edges: [src: 3 dest: 2, src: 3 dest: 4], key: 4, edges: [src: 4 dest: 5, src: 4 dest: 6], key: 5, edges: [src: 5 dest: 3], key: 6, edges: [src: 6 dest: 7], key: 7, edges: [src: 7 dest: 5]]" , g1.getV().toString());
        assertEquals("[src: 1 dest: 3, src: 1 dest: 5]" , g1.getE(1).toString());
        assertEquals("[src: 2 dest: 1, src: 2 dest: 4]" , g1.getE(2).toString());
        assertEquals("[src: 3 dest: 2, src: 3 dest: 4]" , g1.getE(3).toString());
        assertEquals("[src: 4 dest: 5, src: 4 dest: 6]" , g1.getE(4).toString());
        assertEquals("[src: 5 dest: 3]" , g1.getE(5).toString());
        assertEquals("[src: 6 dest: 7]" , g1.getE(6).toString());
        assertEquals("[src: 7 dest: 5]" , g1.getE(7).toString());
    }
}