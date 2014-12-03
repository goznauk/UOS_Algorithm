package com.goznauk;

import java.util.ArrayList;

/**
 * Created by goznauk on 2014. 11. 10..
 */
public class Nodeold {
    private int depth;

    private Info info;

    private int code;
    private Nodeold parent;
    private ArrayList<Nodeold> adjacentNodeolds;



    public Nodeold(int depth, Info info) {
        this(depth, info, null);
    }

    public Nodeold(int depth, Info info, Nodeold parent) {
        this.depth = depth;
        this.info = info;
        this.parent = parent;
        adjacentNodeolds = new ArrayList<Nodeold>();
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

    public ArrayList<Nodeold> getAdjacentNodeolds() {
        return adjacentNodeolds;
    }

    public void addAdjacentNode(Nodeold adjacentNodeold) {
        adjacentNodeolds.add(adjacentNodeold);
    }

    public void addAdjacentNodes(ArrayList<Info> infos) {
        for(Info i : infos) {
            adjacentNodeolds.add(new Nodeold(depth + 1, i, this));
        }
    }

    public void print() {
        if(depth == 0) {
            System.out.println(info.toString());
            for(Nodeold nodeold : adjacentNodeolds) {
                nodeold.print();
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

        for(Nodeold nodeold : adjacentNodeolds) {
            nodeold.print();
        }
    }

    public Nodeold getParent() {
        return parent;
    }

    public void setParent(Nodeold parent) {
        this.parent = parent;
    }

    public int getCode() {
        return info.getCode();
    }

}
