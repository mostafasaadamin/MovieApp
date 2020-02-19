package com.example.movieapp.Adapters;

import android.content.ClipData;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.movieapp.POJO.MovieData;

public class ItemDataSourceFactory extends DataSource.Factory<Integer, MovieData> {
    private MutableLiveData<PageKeyedDataSource<Integer, MovieData>> itemLiveDataSource = new MutableLiveData<>();
    @Override
    public DataSource<Integer, MovieData> create() {
        IteemDataSource iteemDataSource=new IteemDataSource();
        itemLiveDataSource.postValue(iteemDataSource);
        return iteemDataSource;
    }
    public MutableLiveData<PageKeyedDataSource<Integer, MovieData>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
