package goznauk;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by goznauk on 2014. 12. 3..
 */
public class DataSetter {
    public static void setActors(int first, int last) {
        DAO dao = new DAO();
        String adj = "";
        ArrayList<Integer> arrayList;

        for(int i = first; i <= last; i++) {
            arrayList = NaverMovie.getMoviesFromActor(i);
            System.out.println("code : " + i);
            if(arrayList == null) { continue; }
            adj = new Gson().toJson(arrayList);
            System.out.println(adj);
            dao.insert(Main.isActor, i, adj);
        }
    }

    public static void setMovies(int first, int last) {
        DAO dao = new DAO();
        String adj = "";
        ArrayList<Integer> arrayList;

        for(int i = first; i <= last; i++) {
            arrayList = NaverMovie.getActorsFromMovie(i);
            System.out.println("code : " + i);
            if(arrayList == null) { continue; }
            adj = new Gson().toJson(arrayList);
            System.out.println(adj);
            dao.insert(Main.isMovie, i, adj);
        }
    }
}
