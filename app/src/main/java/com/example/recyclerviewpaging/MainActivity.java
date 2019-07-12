package com.example.recyclerviewpaging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        final RecyclerviewAdapter adapter = new RecyclerviewAdapter(this);

        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<StackApiRespnse.ResultsBean>>() {
            @Override
            public void onChanged(PagedList<StackApiRespnse.ResultsBean> resultsBeans) {
                adapter.submitList(resultsBeans);
            }
        });

        recyclerView.setAdapter(adapter);
    }
}
