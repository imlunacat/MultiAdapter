package me.lunacat.multiadapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MultiAdapter<Base> extends RecyclerView.Adapter<MultiViewHolder> {
    private int mViewTypes = 0;
    private ArrayMap<Class, ViewTypeListener> mClassMap = null;
    private SparseArray<Class<? extends MultiViewHolder>> mHolders;
    private SparseIntArray mLayouts;
    private OnItemClickListener<Base> mInternalClickListener;
    protected List<Base> mItems;
    public MultiAdapter(){
        mItems = new ArrayList<Base>();

        mHolders = new SparseArray<>();
        mLayouts = new SparseIntArray();
        mClassMap = new ArrayMap<>();

        mInternalClickListener = new OnItemClickListener<Base>() {
            @Override
            public void onItemClick(Base item, int position) {
                OnItemClickListener externalListener = mClassMap.get(item.getClass()).onClickListener;
                if(externalListener!= null) {
                    externalListener.onItemClick(item, position);
                }
            }
        };
    }

    @Override
    public MultiViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Class<? extends MultiViewHolder> vhClass = mHolders.get(viewType);
        Integer layout = mLayouts.get(viewType);
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);

        MultiViewHolder vh = null;
        try {
            vh = (MultiViewHolder) vhClass.getConstructor(new Class[]{View.class}).newInstance(v);
            vh.setOnItemClickListener(new MultiViewHolder.OnViewHolderClickListener() {
                @Override
                public void onClick(int position) {
                    mInternalClickListener.onItemClick(getItem(position), position);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(MultiViewHolder holder, int position) {
        try {
            holder.onBindViewHolder(mItems.get(position), position);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public int getItemCount() {
        return mItems==null?0:mItems.size();
    }

    public Base getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        Class cls = mItems.get(position).getClass();
        return mClassMap.get(cls).viewType;
    }

    public void addAll(List<Base> items) {
        mItems.clear();
        mItems = items;
    }

    public <T> void add(Class<T> cls,
                        Class<? extends MultiViewHolder<T>> vh,
                        @LayoutRes int layoutRes,
                        OnItemClickListener<T> onClickListener){
        ViewTypeListener t = new ViewTypeListener(mViewTypes, onClickListener);
        mClassMap.put(cls, t);
        mLayouts.put(mViewTypes, layoutRes);
        mHolders.append(mViewTypes, vh);

        mViewTypes++;
    }

    private static class ViewTypeListener {
        int viewType;
        OnItemClickListener onClickListener;
        ViewTypeListener(int viewType, OnItemClickListener l) {
            this.viewType = viewType;
            this.onClickListener = l;
        }
    }
}
