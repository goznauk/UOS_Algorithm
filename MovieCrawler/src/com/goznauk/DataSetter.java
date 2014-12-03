package com.goznauk;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by goznauk on 2014. 12. 3..
 */
public class DataSetter {
    public static void setActors(int limit) {
        DAO dao = new DAO();
        String adj = "";
        ArrayList<Integer> arrayList;

        for(int i = 1; i < limit; i++) {
            arrayList = NaverMovie.getMoviesFromActor(i);
            System.out.println("code : " + i);
            if(arrayList == null) { continue; }
            adj = new Gson().toJson(arrayList);
            System.out.println(adj);
            dao.insert(Main.isActor, i, adj);
        }
    }

    public static void setMovies(int limit) {
        DAO dao = new DAO();
        String adj = "";
        ArrayList<Integer> arrayList;

        for(int i = 1; i < limit + 10000; i++) {
            arrayList = NaverMovie.getActorsFromMovie(i);
            System.out.println("code : " + i);
            if(arrayList == null) { continue; }
            adj = new Gson().toJson(arrayList);
            System.out.println(adj);
            dao.insert(Main.isMovie, i, adj);
        }
    }
}
