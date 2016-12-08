package me.lunacat.multiadapteractivity;

import android.view.View;
import android.widget.TextView;

import me.lunacat.multiadapter.MultiViewHolder;

public class TextCellViewHolder extends MultiViewHolder<TextCell>{
    private TextView mTextView;

    public TextCellViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.item_text_text);
    }

    @Override
    public void onBindViewHolder(TextCell element, int position) {
        mTextView.setText(element.getText());
    }
}
