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
import java.util.List;


public class MultiAdapter<Base> extends RecyclerView.Adapter<MultiViewHolder> {
    private static final int VIEW_TYPE_NOT_FOUND = -1;

    private int mViewTypes = 0;
    private ArrayMap<Class, ViewTypeListener> mClassMap = null;
    private SparseArray<Class<? extends MultiViewHolder>> mHolders;
    private SparseIntArray mLayouts;
    private OnItemClickListener<Base> mInternalClickListener;
    protected List<Base> mItems;
    private boolean mEnableDefaultViewHolder = true;
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
        if(viewType == VIEW_TYPE_NOT_FOUND) {
            if(mEnableDefaultViewHolder) {
                StringBuffer sb = new StringBuffer();
                for(int i = 0; i < mHolders.size(); i++) {
                    int key = mHolders.keyAt(i);
                    Class<? extends MultiViewHolder> cls = mHolders.get(key);
                    sb.append(String.format("key %d class %s \n", key, cls.getSimpleName()));
                }
                throw new RuntimeException(String.format("cannot find viewType %d", viewType));
            } else {
                return defaultViewHolder(parent.getContext());
            }
        }

        Class<? extends MultiViewHolder> vhClass = mHolders.get(viewType);
        Integer layout = mLayouts.get(viewType);
        try {
            View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);

            MultiViewHolder vh = (MultiViewHolder) vhClass.getConstructor(new Class[]{View.class}).newInstance(v);
            vh.setOnItemClickListener(new MultiViewHolder.OnViewHolderClickListener() {
                @Override
                public void onClick(int position) {
                    mInternalClickListener.onItemClick(getItem(position), position);
                }
            });
            return vh;
        } catch (Exception ex) {
            if(mEnableDefaultViewHolder) {
                String lastWord = null;
                if (vhClass == null){
                    lastWord = String.format("cannot find ViewHolder for viewType:%d.", viewType);
                } else if(layout == null) {
                    lastWord = String.format("cannot find layout for viewType:%d.", viewType);
                }
                ex.printStackTrace();

                if (lastWord != null) {
                    RuntimeException exception = new RuntimeException(lastWord, ex);
                    throw exception;
                } else {
                    throw new RuntimeException(ex);
                }
            } else {
                return defaultViewHolder(parent.getContext());
            }
        }
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
        ViewTypeListener vt = mClassMap.get(cls);
        if (vt == null) {
            if(mEnableDefaultViewHolder) {
                throw new RuntimeException(
                        String.format("class: %s cannot found in Adapter. Is it added via add(class, ViewHolder, res, ClickListener) into this Adapter?", cls.getSimpleName()));
            } else {
                return VIEW_TYPE_NOT_FOUND;
            }
        } else {
            return vt.viewType;
        }
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

    public void enableDefaultViewHolder(boolean enable) {
        mEnableDefaultViewHolder = enable;
    }

    private static class ViewTypeListener {
        int viewType;
        OnItemClickListener onClickListener;
        ViewTypeListener(int viewType, OnItemClickListener l) {
            this.viewType = viewType;
            this.onClickListener = l;
        }
    }

    private MultiViewHolder<Base> defaultViewHolder(Context c) {
        return new MultiViewHolder<Base>(new View(c)) {
            @Override
            public void onBindViewHolder(Base element, int position) {

            }
        };
    }
}
