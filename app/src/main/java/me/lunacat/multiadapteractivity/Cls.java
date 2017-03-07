package me.lunacat.multiadapteractivity;


import android.view.View;

import me.lunacat.multiadapter.MultiViewHolder;

public class Cls {
    private int cls = 20;
    class VH extends MultiViewHolder<String> {
        public VH(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindViewHolder(String element, int position) {

        }
    }
}
