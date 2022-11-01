package edu.sdccd.cisc191.template;

import java.util.ArrayList;
public class GraphNode {
    public String name;
    /*
    Note: storing adjNodes as GraphNodes resulted in an issue where nodes in .adjNodes had .adjNodes, each with
     a list of adjNodes...
     this caused infinitely recursive structures, which JSON cant turn to finite strings
     */
    public ArrayList<String> adjNodes= new ArrayList<String>();
    public GraphNode(String name){
        this.name=name;
    }

    public static void addEdge(GraphNode a, GraphNode b){
        a.adjNodes.add(b.name);
        b.adjNodes.add(a.name);
    }

}
