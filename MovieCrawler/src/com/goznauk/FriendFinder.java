package com.goznauk;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by goznauk on 2014. 11. 10..
 */
public class FriendFinder {
    public static final boolean isMovie = true;
    public static final boolean isActor = false;

    private int iCode, fCode;
    private Node iNode;
    private HashSet<Integer> codeSetForAnswer;
    private HashSet<Integer> actorCodeSetForNoRepeat;
    private HashSet<Integer> movieCodeSetForNoRepeat;

    public FriendFinder(int iCode, int fCode) {
        this.iCode = iCode;
        this.fCode = fCode;
        this.iNode = new Node(0, new Info(iCode, "initial", isActor));
        codeSetForAnswer = new HashSet<Integer>();
        actorCodeSetForNoRepeat = new HashSet<Integer>();
        movieCodeSetForNoRepeat = new HashSet<Integer>();
    }

    public void find(int depth) {
        ArrayList<Node> tmpNodes = new ArrayList<Node>();
        tmpNodes.add(iNode);

        for(int i = 0; i < depth; i++) {
            System.out.println("Depth : " + i + " -------------");
            tmpNodes = findNext(tmpNodes);
            if(codeSetForAnswer.contains(fCode)) {
                System.out.println("at Depth " + (i+1) + " : Found");
                iNode.print();
                return;
            }
        }
    }

    private ArrayList<Node> findNext(ArrayList<Node> recentNodes) {
        ArrayList<Node> nextNodes = new ArrayList<Node>();

        System.out.println("NEXT : " + recentNodes.size() + " actors");
        for(Node node : recentNodes) {
            //find node & add next node to list
            find(node, nextNodes);
        }
        return nextNodes;
    }

    private void find(Node actor, final ArrayList<Node> nextNodes) {
        System.out.println("find() : " + actor.getInfo().toString());

        if(actor.getInfo().getCode() == fCode) {
            System.out.println("UNEXPECTED!!");
            System.exit(1);
        }

        if(actorCodeSetForNoRepeat.contains(actor.getInfo().getCode())) { return; }
        actorCodeSetForNoRepeat.add(actor.getInfo().getCode());

        // get Movies
        actor.addAdjacentNodes(NaverMovie.getMoviesFromActor(actor.getInfo().getCode(), null));
        System.out.println("get movies");


        // get Actors from EACH Movies
        for(Node movie : actor.getAdjacentNodes()) {
            final Node n = movie;
            try {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(movieCodeSetForNoRepeat.contains(n.getInfo().getCode())) { return; }
                        movieCodeSetForNoRepeat.add(n.getInfo().getCode());

                        long startTime = System.currentTimeMillis();

                        n.addAdjacentNodes(NaverMovie.getActorsFromMovie(n.getInfo().getCode(), codeSetForAnswer));

                        // End time
                        long endTime = System.currentTimeMillis();

                        // Total time
                        long lTime = endTime - startTime;

                        for(Node tmp : n.getAdjacentNodes()) {
                            nextNodes.add(tmp);
                        }

                        System.out.println("getActors from : " + n.getInfo().getName() + " ( " + lTime + "ms )");

                        if(codeSetForAnswer.contains(fCode)) {
                            System.out.println(fCode + " : Found");
                            iNode.print();
                            System.exit(2);
                        }
                    }
                });
                t.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void printAll() {
        iNode.print();
    }
}
