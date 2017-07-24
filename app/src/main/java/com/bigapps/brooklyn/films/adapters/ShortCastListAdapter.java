package com.bigapps.brooklyn.films.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigapps.brooklyn.films.R;
import com.bigapps.brooklyn.films.model.entities.Cast;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Brooklyn on 06-Jul-17.
 */

public class ShortCastListAdapter extends RecyclerView.Adapter<ShortCastListAdapter.ViewHolder> {

    private List<Cast> list;
    private Context context;
    private CastClickListener clickListener;

    public ShortCastListAdapter(List<Cast> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.castNameTxt) TextView castNameTxt;
        @BindView(R.id.castPhotoImg) ImageView castPhotoImg;
        View rootView;

        public ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public void setCastClickListener(CastClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public ShortCastListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cast_short_list_item, parent, false);
        return new ShortCastListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShortCastListAdapter.ViewHolder holder, int position) {

        holder.castNameTxt.setText(list.get(position).getName());
        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w92/" + list.get(position).getProfilePath())
                .into(holder.castPhotoImg);

        holder.rootView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.setCastClicked(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface CastClickListener {
        void setCastClicked(int id);
    }
}
