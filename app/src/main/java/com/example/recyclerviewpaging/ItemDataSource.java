package com.example.recyclerviewpaging;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, StackApiRespnse.ResultsBean> {

    private static final int PAGE_NO = 1;
    private static final String API_KEY = "434fcadef5103207fecca9176385a533";


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, StackApiRespnse.ResultsBean> callback) {

        RetrofitClient.getInstance()
                .getApi()
                .getPopularMovies(API_KEY,PAGE_NO)
                .enqueue(new Callback<StackApiRespnse>() {
                    @Override
                    public void onResponse(Call<StackApiRespnse> call, Response<StackApiRespnse> response) {
                        if (response.body() != null){
                            callback.onResult(response.body().getResults(),null,PAGE_NO + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<StackApiRespnse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, StackApiRespnse.ResultsBean> callback) {

        RetrofitClient.getInstance()
                .getApi()
                .getPopularMovies(API_KEY,params.key)
                .enqueue(new Callback<StackApiRespnse>() {
                    @Override
                    public void onResponse(Call<StackApiRespnse> call, Response<StackApiRespnse> response) {
                        Integer key = (params.key > 1) ? params.key -1 : null;
                        if (response.body() != null){
                            callback.onResult(response.body().getResults(),key);
                        }
                    }

                    @Override
                    public void onFailure(Call<StackApiRespnse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, StackApiRespnse.ResultsBean> callback) {

        RetrofitClient.getInstance()
                .getApi()
                .getPopularMovies(API_KEY,params.key)
                .enqueue(new Callback<StackApiRespnse>() {
                    @Override
                    public void onResponse(Call<StackApiRespnse> call, Response<StackApiRespnse> response) {
                        Integer key = (params.key < 992) ? params.key +1 : null;
                        if (response.body() != null){
                            callback.onResult(response.body().getResults(),key);
                        }
                    }

                    @Override
                    public void onFailure(Call<StackApiRespnse> call, Throwable t) {

                        Log.i("Item", String.valueOf(t));
                        Log.i("Item", String.valueOf(t));
                        Log.i("Item", String.valueOf(t));

                    }
                });
    }
}
