package com.maestros.bottlehoz.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.model.SliderModel;
import com.smarteist.autoimageslider.SliderViewAdapter;


import java.util.List;

public class SliderAdapterExample extends SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {

    private Context context;
    List<SliderModel> dataAdapters;

    public SliderAdapterExample(List<SliderModel> getDataAdapter, Context context) {
        this.context = context;
        this.dataAdapters = getDataAdapter;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
      final SliderModel dataAdapterOBJ = dataAdapters.get(position);

      try {
            Glide.with(viewHolder.itemView)
                      .load(dataAdapterOBJ.getPath()+dataAdapterOBJ.getImageBanner())
                      .into(viewHolder.imageSlider);
        } catch (Exception e) {
            e.printStackTrace();
        }


     /* viewHolder.txtName.setText(dataAdapterOBJ.getTitle());
      viewHolder.txtDetails.setText(dataAdapterOBJ.getDiscription());*/
    /* if (position==0){
         Glide.with(context)
                 .load(R.drawable.banner)
                 .into(viewHolder.imageSlider);

     }else if (position==1){
         Glide.with(context)
                 .load(R.drawable.banner)
                 .into(viewHolder.imageSlider);
     }
     else if (position==2){
         Glide.with(context)
                 .load(R.drawable.banner)
                 .into(viewHolder.imageSlider);
     }*/
      /*  if(position==0){
            Picasso.get().load(R.drawable.gym).into(viewHolder.imageViewBackground);
        }
*/



    }

    /* viewHolder.textViewDescription.setText(dataAdapterOBJ.getName());
      Picasso.get().load(R.drawable.fishing).into(viewHolder.imageViewBackground);*/




    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return dataAdapters.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageSlider;
        TextView txtDetails,txtName;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageSlider = itemView.findViewById(R.id.imageSlider);
            txtDetails = itemView.findViewById(R.id.txtDetails);
            txtName = itemView.findViewById(R.id.txtName);
            this.itemView = itemView;
        }
    }


}