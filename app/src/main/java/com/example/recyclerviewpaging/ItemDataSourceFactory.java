package com.example.recyclerviewpaging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, StackApiRespnse.ResultsBean>> itemlivedatasource = new MutableLiveData<>();


    @NonNull
    @Override
    public DataSource create() {

        ItemDataSource itemDataSource = new ItemDataSource();
        itemlivedatasource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, StackApiRespnse.ResultsBean>> getItemlivedatasource() {
        return itemlivedatasource;
    }
}
