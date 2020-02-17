package com.example.movieapp.POJO;

import java.util.ArrayList;

public class Videos {
    int id;
    ArrayList<VideosModel> results;

    public Videos() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<VideosModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<VideosModel> results) {
        this.results = results;
    }

    public Videos(int id, ArrayList<VideosModel> results) {
        this.id = id;
        this.results = results;
    }
}
