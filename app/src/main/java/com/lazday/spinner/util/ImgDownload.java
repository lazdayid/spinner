package com.lazday.spinner.util;

import android.widget.ImageView;

import com.lazday.spinner.R;
import com.squareup.picasso.Picasso;

public class ImgDownload {
    public static void picasso(ImageView imageView, String string){
        Picasso.get().load("http://services.hanselandpetal.com/photos/" + string)
                .placeholder(R.drawable.no_preview).into(imageView);
    }
}
