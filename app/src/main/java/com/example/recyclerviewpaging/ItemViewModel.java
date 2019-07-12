package com.example.recyclerviewpaging;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

public class ItemViewModel extends ViewModel {

     LiveData<PagedList<StackApiRespnse.ResultsBean>> itemPagedList;
     LiveData<PageKeyedDataSource<Integer, StackApiRespnse.ResultsBean>> liveDataSource;

    public ItemViewModel(){
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        liveDataSource = itemDataSourceFactory.getItemlivedatasource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setPageSize(20)
                        .setPrefetchDistance(150)
                        .setEnablePlaceholders(true)
                        .build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory,config)).build();

    }
}
