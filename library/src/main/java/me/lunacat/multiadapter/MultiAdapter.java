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
    private ArrayMap<Class, Integer> mClassMap = null;
    private SparseArray<Class<? extends MultiViewHolder>> mHolders;
    private SparseIntArray mLayouts;
    protected List<Base> mItems;
    public MultiAdapter(){
        mItems = new ArrayList<Base>();

        mHolders = new SparseArray<>();
        mLayouts = new SparseIntArray();
        mClassMap = new ArrayMap<>();
    }

    public MultiAdapter(List<Base> list){
        mItems = list;

        mHolders = new SparseArray<>();
        mLayouts = new SparseIntArray();
        mClassMap = new ArrayMap<>();
    }

    @Override
    public MultiViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Class<? extends MultiViewHolder> vhClass = mHolders.get(viewType);
        Integer layout = mLayouts.get(viewType);
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);

        MultiViewHolder vh = null;
        try {
            vh = (MultiViewHolder) vhClass.getConstructor(new Class[]{View.class}).newInstance(v);
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

    @Override
    public int getItemViewType(int position) {
        Class cls = mItems.get(position).getClass();
        return mClassMap.get(cls);
    }

    public Base getItem(int position) {
        return mItems.get(position);
    }

    public List<Base> getItems() {
        return mItems;
    }

    public void add(Base item) {
        mItems.add(item);
    }

    public void addAll(List<Base> items) {
        mItems.clear();
        mItems = items;
    }

    public void addAllAndNotify(List<Base> items) {
        addAll(items);
        notifyDataSetChanged();
    }

    public void cleanAll() {
        mItems.clear();
    }

    public void cleanAllAndNotify(){
        cleanAll();
        notifyDataSetChanged();
    }

    public <T> void add(Class<T> cls,
                        Class<? extends MultiViewHolder<T>> vh,
                        @LayoutRes int layoutRes){
        mClassMap.put(cls, mViewTypes);
        mLayouts.put(mViewTypes, layoutRes);
        mHolders.append(mViewTypes, vh);

        mViewTypes++;
    }
}
