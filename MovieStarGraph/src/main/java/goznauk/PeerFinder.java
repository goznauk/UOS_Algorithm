package goznauk;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by goznauk on 2014. 12. 3..
 */
public class PeerFinder {
    public static final boolean isMovie = true;
    public static final boolean isActor = false;

    public static Node find(int iCode, int fCode, int depthLimit) {
        Node iNode, fNode;
        ArrayList<Node> nextNodes = new ArrayList<Node>();
        iNode = new Node(iCode, null);
        nextNodes.add(iNode);

        for(int i = 0; i < depthLimit; i++) {
            System.out.println("===================== finding at depth " + (i+1));
            for(Node node : nextNodes) {
                fNode = find(node, fCode);
                if(fNode!=null) {
                    System.out.println("success at depth " + (i+1));
                    return fNode;
                }
            }
            nextNodes = iNode.getChilds(i);
        }
        return null;
    }



    // RETURN Node when target found, NULL at default
    public static Node find(Node actorNode, int fCode) {
        int[] movieCodes, actorCodes;

        movieCodes = Node.convertJSONtoIntArray(DAO.getPeers(isActor, actorNode.getCode()));

        try {
            for (int movieCode : movieCodes) {
                actorNode.addAdjacentNode(new Node(movieCode, actorNode));
            }
        } catch (NullPointerException e) {
            Main.failed.add(actorNode.getCode());
        }


        for(Node movie : actorNode.getAdjacentNodes()) {
            actorCodes = Node.convertJSONtoIntArray(DAO.getPeers(isMovie, movie.getCode()));
            try {
                for (int actorCode : actorCodes) {
                    Node actor = new Node(actorCode, movie);
                    movie.addAdjacentNode(actor);
                    if (actorCode == actorNode.getCode()) {
                        continue;
                    }

                    if (actorCode == fCode) {
                        return actor;
                    }
                }
            } catch (NullPointerException e) {
                Main.failed.add(movie.getCode()*-1);
            }
        }

        return null;
    }
}