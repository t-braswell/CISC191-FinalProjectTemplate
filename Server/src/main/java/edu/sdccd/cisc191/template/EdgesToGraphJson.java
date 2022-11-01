package edu.sdccd.cisc191.template;

import java.util.ArrayList;
import java.util.Hashtable;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EdgesToGraphJson {
    public static String edgeListToGraph(String[][] edgeList){
        ArrayList<GraphNode> graph= new ArrayList<GraphNode>();
        ArrayList<String> nodeList= new ArrayList<String>();
        Hashtable<String, GraphNode> nodeNameTable= new Hashtable<String,GraphNode>();
        //edgeList is an N x 2 list of all edges
        for (String[] nodePair : edgeList) {
            if (!nodeList.contains(nodePair[0])) {
                GraphNode newA = new GraphNode(nodePair[0]);
                graph.add(newA);
                nodeList.add(nodePair[0]);
                nodeNameTable.put(nodePair[0], newA);
            }
            if (!nodeList.contains(nodePair[1])) {
                GraphNode newB = new GraphNode(nodePair[1]);
                graph.add(newB);
                nodeList.add(nodePair[1]);
                nodeNameTable.put(nodePair[1], newB);
            }
            GraphNode a = nodeNameTable.get(nodePair[0]);
            GraphNode b = nodeNameTable.get(nodePair[1]);

            GraphNode.addEdge(a, b);


        }

        String graphJson=new String();
        ObjectMapper objectMapper= new ObjectMapper();
        try {
            graphJson = objectMapper.writeValueAsString(graph);
        }
        catch(JsonProcessingException jsonProcessingException){
            graphJson="An error occurred during serialization";
        }

        return graphJson;
    }
}
