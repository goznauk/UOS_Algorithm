package com.goznauk;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by goznauk on 2014. 11. 9..
 */
public class NaverMovie {
    public static final boolean isMovie = true;
    public static final boolean isActor = false;

    public static ArrayList<Info> getActorsFromMovie(int code, HashSet<Integer> codeSet) {

        ArrayList<Info> actors = new ArrayList<Info>();

        String targetSourcePath = "http://movie.naver.com/movie/bi/mi/detail.nhn";

        try {
            String urlParameters = "code=" + code; //파라메타값
            URL url = new URL(targetSourcePath);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //헤더 선언
            urlConnection.setRequestMethod("POST");
            //urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            urlConnection.setUseCaches(false);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            PrintWriter pw = new PrintWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "utf-8"));
            pw.write(urlParameters);
            pw.flush();
            pw.close();

            //Get Response
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String source = "";
            String line = "";
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            source = sb.toString();

            Document doc = Jsoup.parse(source);
            Elements rows = doc.select("a[title].k_name");

            for (Element row : rows) {
                Info i = new Info(Integer.parseInt(row.attr("href").substring(28)), row.attr("title"), isActor);
                codeSet.add(i.getCode());
                actors.add(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return actors;
    }

    public static ArrayList<Info> getMoviesFromActor(int code, HashSet<Integer> codeSet) {
        Boolean hasNextPage = true;
        int nextPage = 1;

        ArrayList<Info> movies = new ArrayList<Info>();

        String targetSourcePath = "http://movie.naver.com/movie/bi/pi/filmoMission.nhn?";

        while(hasNextPage) {
            try {
                String urlParameters = "peopleCode=" + code + "&page=" + nextPage; //파라메타값
                URL url = new URL(targetSourcePath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                //헤더 선언
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

                urlConnection.setUseCaches(false);
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                PrintWriter pw = new PrintWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "utf-8"));
                pw.write(urlParameters);
                pw.flush();
                pw.close();

                //Get Response
                InputStream is = urlConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String source = "";
                String line = "";
                StringBuilder sb = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                source = sb.toString();

                nextPage++;

                Document doc = Jsoup.parse(source);
                Element nextP = doc.getElementById("pagerTagAnchor" + nextPage);

                if(nextP == null) {
                    hasNextPage = false;
                }

                Elements rows = doc.select(".pilmo_thumb a");

                for (Element row : rows) {
                    Info i = new Info(Integer.parseInt(row.attr("href").substring(28)), row.select("img").attr("alt"), isMovie);
                    codeSet.add(i.getCode());
                    movies.add(i);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return movies;
    }
}