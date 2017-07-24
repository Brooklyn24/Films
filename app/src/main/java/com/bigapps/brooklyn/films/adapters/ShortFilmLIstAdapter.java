package com.bigapps.brooklyn.films.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bigapps.brooklyn.films.R;
import com.bigapps.brooklyn.films.model.entities.Film;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Brooklyn on 07-Jul-17.
 */

public class ShortFilmLIstAdapter extends RecyclerView.Adapter<ShortFilmLIstAdapter.ViewHolder> {

    private List<Film> list;
    private Context context;
    private FilmClickListener clickListener;

    public ShortFilmLIstAdapter(List<Film> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.filmImage) ImageView filmImage;
        @BindView(R.id.filmNameTxt) TextView filmNameTxt;
        @BindView(R.id.progressBar) ProgressBar progressBar;
        View rootView;

        public ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public void setFilmClickListener(FilmClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public ShortFilmLIstAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.short_film_list_item, parent, false);
        return new ShortFilmLIstAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShortFilmLIstAdapter.ViewHolder holder, int position) {
        holder.filmNameTxt.setText(list.get(position).getTitle());
        holder.progressBar.setVisibility(View.VISIBLE);
        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w154/" + list.get(position).getPosterPath())
                .into(holder.filmImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError() {
                        holder.progressBar.setVisibility(View.INVISIBLE);
                    }
                });

        holder.rootView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onFilmClicked(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
