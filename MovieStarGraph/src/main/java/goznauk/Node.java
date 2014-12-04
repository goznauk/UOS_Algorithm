package goznauk;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by goznauk on 2014. 12. 3..
 */
public class Node {
    private int code;
    private Node parent;
    private ArrayList<Node> adjacentNodes;

    public Node(int code, Node parent) {
        this.code = code;
        this.parent = parent;
        adjacentNodes = new ArrayList<Node>();
    }

    public int getCode() {
        return code;
    }

    public Node getParent() {
        return parent;
    }

    public ArrayList<Node> getAdjacentNodes() { return adjacentNodes; }

    public void addAdjacentNode(Node node) {
        adjacentNodes.add(node);
    }

    public static int[] convertJSONtoIntArray(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, int[].class);
    }

    public static String getParentsString(Node node) {
        String parentsString = node.code + "  ";
        if(node.parent == null) {
            return parentsString;
        } else {
            return parentsString + getParentsString(node.parent);
        }
    }

    public ArrayList<Node> getChilds(int depth) {
        ArrayList<Node> firstList = new ArrayList<Node>();
        firstList.add(this);
        return getChilds(firstList, 0, depth);
    }

    private ArrayList<Node> getChilds(ArrayList<Node> parents, int count, int depth) {
        ArrayList<Node> childs = new ArrayList<Node>();

        for(Node parent : parents) {
            for (Node movie : parent.adjacentNodes) {
                for (Node actor : movie.adjacentNodes) {
                    childs.add(actor);
                }
            }
        }

        if(count==depth) {
            return childs;
        } else {
            return getChilds(childs, count + 1, depth);
        }
    }

}
