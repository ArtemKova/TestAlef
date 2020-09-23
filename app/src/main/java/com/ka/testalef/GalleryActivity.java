package com.ka.testalef;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        int colomn = 2;
        boolean tabletSize = getResources().getBoolean(R.bool.isTablet); //Определяет по размеру экранапланшет или телефон
        if (tabletSize) {
            colomn = 3;}
        else {colomn = 2;}

//

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, colomn);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_images);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        GalleryActivity.ImageGalleryAdapter adapter = new GalleryActivity.ImageGalleryAdapter(this, PictureModels.getSpacePhotos());
        recyclerView.setAdapter(adapter);

    }
    private class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder>  {

        @Override
        public ImageGalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View photoView = inflater.inflate(R.layout.list_sp, parent, false);
            return new MyViewHolder(photoView);
        }

        @Override
        public void onBindViewHolder(ImageGalleryAdapter.MyViewHolder holder, int position) {

            PictureModels pictureModels = mPictureModels[position];
            ImageView imageView = holder.mPhotoImageView;
            Glide.with(mContext)
                    .load(pictureModels.getUrl())
                    .placeholder(R.drawable.ic_cloud_off_red)
                    .into(imageView);
        }

        @Override
        public int getItemCount() {
            return (mPictureModels.length);
        }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public ImageView mPhotoImageView;

            public MyViewHolder(View itemView) {

                super(itemView);
                mPhotoImageView = (ImageView) itemView.findViewById(R.id.iv_photo);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {

                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION) {
                    PictureModels pictureModels = mPictureModels[position];
                    Intent intent = new Intent(mContext, PictureActivity.class);
                    intent.putExtra(PictureActivity.EXTRA_SPACE_PHOTO, pictureModels);
                    startActivity(intent);
                }
            }
        }

        private PictureModels[] mPictureModels;
        private Context mContext;

        public ImageGalleryAdapter(Context context, PictureModels[] pictureModels) {
            mContext = context;
            mPictureModels = pictureModels;
        }
}}
