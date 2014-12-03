package com.goznauk;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by goznauk on 2014. 11. 10..
 */
public class FriendFinder {
 /*
    public static final boolean isMovie = true;
    public static final boolean isActor = false;

    private int iCode, fCode;
    private Nodeold iNodeold;
    private HashSet<Integer> codeSetForAnswer;
    private HashSet<Integer> actorCodeSetForNoRepeat;
    private HashSet<Integer> movieCodeSetForNoRepeat;

    public FriendFinder(int iCode, int fCode) {
        this.iCode = iCode;
        this.fCode = fCode;
        this.iNodeold = new Nodeold(0, new Info(iCode, "initial", isActor));
        codeSetForAnswer = new HashSet<Integer>();
        actorCodeSetForNoRepeat = new HashSet<Integer>();
        movieCodeSetForNoRepeat = new HashSet<Integer>();
    }

    public void find(int depth) {
        ArrayList<Nodeold> tmpNodeolds = new ArrayList<Nodeold>();
        tmpNodeolds.add(iNodeold);

        for(int i = 0; i < depth; i++) {
            System.out.println("Depth : " + i + " -------------");
            tmpNodeolds = findNext(tmpNodeolds);
            if(codeSetForAnswer.contains(fCode)) {
                System.out.println("at Depth " + (i+1) + " : Found");
                printPath();
                //iNode.print();
                return;
            }
        }
    }

    private void printPath() {


    }


    private ArrayList<Nodeold> findNext(ArrayList<Nodeold> recentNodeolds) {
        ArrayList<Nodeold> nextNodeolds = new ArrayList<Nodeold>();

        System.out.println("NEXT : " + recentNodeolds.size() + " actors");
        for(Nodeold nodeold : recentNodeolds) {
            //find node & add next node to list
            find(nodeold, nextNodeolds);
        }
        return nextNodeolds;
    }

    private void find(Nodeold actor, final ArrayList<Nodeold> nextNodeolds) {
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
        for(Nodeold movie : actor.getAdjacentNodeolds()) {
            final Nodeold n = movie;
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

                        for(Nodeold tmp : n.getAdjacentNodeolds()) {
                            nextNodeolds.add(tmp);
                        }

                        System.out.println("getActors from : " + n.getInfo().getName() + " ( " + lTime + "ms )");

                        if(codeSetForAnswer.contains(fCode)) {
                            System.out.println(fCode + " : Found");
                            iNodeold.print();
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

    public void printNode() {

    }

    public void printAll() {
        iNodeold.print();
    }
    */
}
