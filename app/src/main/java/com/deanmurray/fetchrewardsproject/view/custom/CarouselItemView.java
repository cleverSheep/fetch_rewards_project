package com.deanmurray.fetchrewardsproject.view.custom;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.deanmurray.fetchrewardsproject.R;

@EpoxyModelClass(layout = R.layout.carousel_item)
public abstract class CarouselItemView extends EpoxyModelWithHolder<CarouselItemView.ItemHolder> {

    @EpoxyAttribute
    String item_id;
    @EpoxyAttribute
    String item_name;

    @Override
    public void bind(@NonNull ItemHolder holder) {
        holder.itemId.setText(item_id);
        holder.itemName.setText(item_name);
    }

    static class ItemHolder extends EpoxyHolder {

        TextView itemId;
        TextView itemName;

        @Override
        protected void bindView(@NonNull View itemView) {
            itemId = itemView.findViewById(R.id.id);
            itemName = itemView.findViewById(R.id.name);
        }
    }
}
