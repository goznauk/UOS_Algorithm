package com.goznauk;

public class Main {
    public static final boolean isMovie = true;
    public static final boolean isActor = false;

    public static void main(String[] args) {
        FriendFinder friendFinder = new FriendFinder(254992, 1748);
        //FriendFinder friendFinder = new FriendFinder(254992, 216937);
        friendFinder.find(3);
        friendFinder.printAll();
    }
}
