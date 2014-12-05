package goznauk;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by goznauk on 2014. 12. 3..
 */
public class Main {
    public static final boolean isMovie = true;
    public static final boolean isActor = false;
    // *-1 for movie
    public static HashSet<Integer> failed;


    public static void main(String[] args) {
        //  FriendFinder friendFinder = new FriendFinder(13916, 108226); // 최진안 : 백설희, 다나 - google dev
        //  FriendFinder friendFinder = new FriendFinder(119301, 43015); // 신상걸 : 권영팔, 김희수
        //  FriendFinder friendFinder = new FriendFinder(16834, 356524); // 이호창 : 황남, 민진웅 - aws
        //  FriendFinder friendFinder = new FriendFinder(324964, 57659); // 최훈존 : 김소영, 정덕순
        //  FriendFinder friendFinder = new FriendFinder(41324, 134826); // 박영재 : 김삼화, 김수현
        //  FriendFinder friendFinder = new FriendFinder(311452, 64596); // 백상호 : 김이안, 이문수
        //  FriendFinder friendFinder = new FriendFinder(246775, 295069); // 김종남 : 노유난, 지상혁 - not in find(4)


       // DataSetter.setMovies(10793, 10793);



        Node node;
        int iCode, fCode, depthLimit;


        Scanner scanner = new Scanner(System.in);
        System.out.print("Input iCode : ");
        iCode = scanner.nextInt();
        System.out.print("Input fCode : ");
        fCode = scanner.nextInt();
        System.out.print("Input depthLimit : ");
        depthLimit = scanner.nextInt();

        failed = new HashSet<Integer>();
        node = PeerFinder.find(iCode, fCode, depthLimit);

      //  node = PeerFinder.find(13916, 108226, 3); // 1
    //    node = PeerFinder.find(311452, 64596, 3); // 1


        if(node == null) {
            System.out.println("Path Doesn't Exist");
            return;
        }
        System.out.println(Node.getParentsString(node));

        System.out.print("Failed : ");
        for(Integer failcode : failed) {
            System.out.print(failcode + "  ");
        }

    }
}
