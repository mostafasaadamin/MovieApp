package com.example.movieapp.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Moviemodel {
    int page,total_results,total_pages;
    public Moviemodel(){}
    ArrayList<MovieData> results;
    public Moviemodel(int page, int total_results, int total_pages, ArrayList<MovieData> results) {
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<MovieData> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieData> results) {
        this.results = results;
    }
}
