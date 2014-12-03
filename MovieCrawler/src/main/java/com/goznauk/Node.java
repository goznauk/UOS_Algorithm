package com.goznauk;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by goznauk on 2014. 12. 3..
 */
public class Node {
    private int depth;

    private int code;
    private Node parent;
    private ArrayList<Node> adjacentNodes;

    public Node(int depth, int code, Node parent, ArrayList<Node> adjacentNodes) {
        this.depth = depth;
        this.code = code;
        this.parent = parent;
        this.adjacentNodes = adjacentNodes;
    }

    public int getCode() {
        return code;
    }

    public Node getParent() {
        return parent;
    }

    public void addAdjacentNode(Node node) {
        adjacentNodes.add(node);
    }

    public static int[] convertJSONtoIntArray(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, int[].class);
    }
}
