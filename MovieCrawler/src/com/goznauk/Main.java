package com.goznauk;

public class Main {
    public static final boolean isMovie = true;
    public static final boolean isActor = false;

    public static void main(String[] args) {
    //  FriendFinder friendFinder = new FriendFinder(13916, 108226); // 최진안 : 백설희, 다나 - google dev
    //  FriendFinder friendFinder = new FriendFinder(119301, 43015); // 신상걸 : 권영팔, 김희수
    //  FriendFinder friendFinder = new FriendFinder(16834, 356524); // 이호창 : 황남, 민진웅 - aws
    //  FriendFinder friendFinder = new FriendFinder(324964, 57659); // 최훈존 : 김소영, 정덕순
    //  FriendFinder friendFinder = new FriendFinder(41324, 134826); // 박영재 : 김삼화, 김수현
    //  FriendFinder friendFinder = new FriendFinder(311452, 64596); // 백상호 : 김이안, 이문수
    //    FriendFinder friendFinder = new FriendFinder(246775, 295069); // 김종남 : 노유난, 지상혁 - not in find(4)
    //    friendFinder.find(4);
    //    friendFinder.printAll();

    //    DataSetter.setMovies(100);



        System.out.println(DAO.getPeers(isActor, 11));
    }
}
