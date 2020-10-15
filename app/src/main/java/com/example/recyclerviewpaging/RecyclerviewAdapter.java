package com.example.recyclerviewpaging;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class RecyclerviewAdapter extends PagedListAdapter<StackApiRespnse.ResultsBean, RecyclerviewAdapter.MyViewHolder> {

    private Context context;

    protected RecyclerviewAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerviewlayout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        StackApiRespnse.ResultsBean resultsBean = getItem(position);
        if (resultsBean != null) {

            Glide.with(context)
                    .load("http://image.tmdb.org/t/p/w342" + resultsBean.getPoster_path())
                    .placeholder(R.drawable.poster)
                    .centerCrop()
                    .into(holder.imageView);

            holder.title.setText(resultsBean.getTitle());
            holder.overview.setText(resultsBean.getOverview());
            holder.realse_date.setText(resultsBean.getRelease_date());


        } else {
            Toast.makeText(context, "No Data", Toast.LENGTH_LONG).show();
        }
    }

    private static DiffUtil.ItemCallback<StackApiRespnse.ResultsBean> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<StackApiRespnse.ResultsBean>() {
                @Override
                public boolean areItemsTheSame(@NonNull StackApiRespnse.ResultsBean oldItem, @NonNull StackApiRespnse.ResultsBean newItem) {
                    return oldItem.getTitle() == newItem.getTitle();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull StackApiRespnse.ResultsBean oldItem, @NonNull StackApiRespnse.ResultsBean newItem) {
                    return oldItem.equals(newItem);
                }
            };

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title,overview,realse_date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            overview = itemView.findViewById(R.id.overview);
            realse_date = itemView.findViewById(R.id.date);
        }
    }
}
