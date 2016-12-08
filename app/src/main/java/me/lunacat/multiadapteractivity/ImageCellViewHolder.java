package me.lunacat.multiadapteractivity;

import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import me.lunacat.multiadapter.MultiViewHolder;

public class ImageCellViewHolder extends MultiViewHolder<ImageCell> {
    private ImageView mImageView;
    public ImageCellViewHolder(View itemView) {
        super(itemView);
        mImageView = (ImageView) itemView.findViewById(R.id.item_image_image);
    }

    @Override
    public void onBindViewHolder(ImageCell element, int position) {
        Picasso.with(itemView.getContext())
                .load(element.getImageUrl())
                .centerCrop().resize(360, 360)
                .into(mImageView);
    }
}
