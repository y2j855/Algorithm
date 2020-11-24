package com.tony.basis.class10;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Tony.Chen
 * Create Time : 2020/11/24 18:04
 * Description:
 */
class GraphTest {
    int[][] matrix = {{7, 0, 4}, {4, 1, 3}, {3, 2, 3}, {8, 2, 4}};
    Graph graph = GraphGenerator.generatorGraph(matrix);;

    @Test
    void getNumOfVertex() {

        assertEquals(5, graph.getNumOfVertex());
    }

    @Test
    void insertVex() {
        assertEquals(true, graph.insertVex(5));
        assertEquals(6, graph.getNumOfVertex());

    }

    @Test
    void deleteVex() {
        graph.insertVex(5);
        assertEquals(true, graph.deleteVex(5));
        assertEquals(false, graph.deleteVex(4));
        assertEquals(5, graph.getNumOfVertex());
    }

    @Test
    void indexOfVex() {
    }

    @Test
    void valueOfVex() {
        assertEquals(3, graph.valueOfVex(3));
    }

    @Test
    void insertEdge() {
        assertEquals(true, graph.insertEdge(5, 6, 20));
        assertEquals(7, graph.getNumOfVertex());
        assertEquals(true, graph.insertEdge(4, 7, 11));
        assertEquals(false, graph.insertEdge(0, 4, 99));
        assertEquals(8, graph.getNumOfVertex());
    }

    @Test
    void deleteEdge() {
        Vertex from = graph.nodes.get(0);
        Vertex to = graph.nodes.get(4);
        assertEquals(true, graph.deleteEdge(0, 4));
        assertEquals(0, from.out);
        assertEquals(1, to.in);
        assertEquals(false, graph.deleteEdge(5, 7));
    }

    @Test
    void getEdge() {
        assertEquals(3, graph.getEdge(2, 3));
        assertEquals(0, graph.getEdge(4, 0));
    }

    @Test
    void depthFirstSearch() {
    }

    @Test
    void breadFirstSearch() {
    }

    @Test
    void dijkstra() {
    }
}