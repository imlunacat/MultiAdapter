package me.lunacat.multiadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class MultiViewHolder<T> extends RecyclerView.ViewHolder{
    public MultiViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBindViewHolder(T element, int position);
}
