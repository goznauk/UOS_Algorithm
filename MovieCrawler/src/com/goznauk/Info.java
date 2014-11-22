package com.goznauk;

/**
 * Created by goznauk on 2014. 11. 9..
 */
public class Info {
    private int code;
    private String name;
    private boolean isMovie;

    public Info(int code, String name, boolean isMovie) {
        this.code = code;
        this.name = name;
        this.isMovie = isMovie;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        if(isMovie) {
            return "[MOVIE] " + code + " : " + name;
        }
        return "[ACTOR] " + code + " : " + name;
    }

    public boolean isMovie() {
        return isMovie;
    }
}
