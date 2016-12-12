package me.lunacat.multiadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class MultiViewHolder<T> extends RecyclerView.ViewHolder{
    private OnViewHolderClickListener mOnItemClickListener;
    public MultiViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClick(MultiViewHolder.this.getAdapterPosition());
            }
        });
    }

    public abstract void onBindViewHolder(T element, int position);

    public void setOnItemClickListener(OnViewHolderClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    interface OnViewHolderClickListener {
        void onClick(int position);
    }
}
