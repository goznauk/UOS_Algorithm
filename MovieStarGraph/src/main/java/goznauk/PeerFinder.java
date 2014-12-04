package goznauk;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by goznauk on 2014. 12. 3..
 */
public class PeerFinder {
    public static final boolean isMovie = true;
    public static final boolean isActor = false;

    public static Node find(int iCode, int fCode, int limitDepth) {
        Node iNode, fNode;
        ArrayList<Node> nextNodes = new ArrayList<Node>();
        iNode = new Node(0, iCode, null);
        nextNodes.add(iNode);

        for(int i = 0; i < limitDepth; i++) {
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

        return iNode;
    }



    // RETURN Node when target found, NULL at default
    public static Node find(Node actorNode, int fCode) {
        int[] movieCodes, actorCodes;

        movieCodes = Node.convertJSONtoIntArray(DAO.getPeers(isActor, actorNode.getCode()));
        for(int movieCode : movieCodes) {
            actorNode.addAdjacentNode(new Node(actorNode.getDepth() + 1, movieCode, actorNode));
        }

        for(Node movie : actorNode.getAdjacentNodes()) {
            actorCodes = Node.convertJSONtoIntArray(DAO.getPeers(isMovie, movie.getCode()));
            for(int actorCode : actorCodes) {
                Node actor = new Node(actorNode.getDepth() + 2, actorCode, movie);
                movie.addAdjacentNode(actor);

                //System.out.println(actorCode + " added");
                if(actorCode == fCode) {
                    return actor;
                }
            }
        }

        return null;
    }



}