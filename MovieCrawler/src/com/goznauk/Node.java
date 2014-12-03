package com.goznauk;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by goznauk on 2014. 11. 10..
 */
public class Node {
    private int depth;
    private Info info;
    private Node parent;
    private ArrayList<Node> adjacentNodes;

    public Node(int depth, Info info) {
        this(depth, info, null);
    }

    public Node(int depth, Info info, Node parent) {
        this.depth = depth;
        this.info = info;
        this.parent = parent;
        adjacentNodes = new ArrayList<Node>();
    }



    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public ArrayList<Node> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void addAdjacentNode(Node adjacentNode) {
        adjacentNodes.add(adjacentNode);
    }

    public void addAdjacentNodes(ArrayList<Info> infos) {
        for(Info i : infos) {
            adjacentNodes.add(new Node(depth + 1, i, this));
        }
    }

    public void print() {
        if(depth == 0) {
            System.out.println(info.toString());
            for(Node node : adjacentNodes) {
                node.print();
            }
            return;
        }

        String s = "";
        for(int i = 1; i < depth; i++) {
            s += "  ";
        }

        s += "|-";
        s += info.toString();

        System.out.println(s);

        for(Node node : adjacentNodes) {
            node.print();
        }
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getCode() {
        return info.getCode();
    }

}
