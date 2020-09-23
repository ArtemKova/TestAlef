package com.ka.testalef;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class PictureModels implements Parcelable {

    private static int b;
    private static ArrayList<String> lList1;
    private String mUrl;
    private String mTitle;

    public PictureModels(String url) {
        mUrl = url;

    }

    protected PictureModels(Parcel in) {
        mUrl = in.readString();
//        mTitle = in.readString();
    }

    public static final Creator<PictureModels> CREATOR = new Creator<PictureModels>() {
        @Override
        public PictureModels createFromParcel(Parcel in) {
            return new PictureModels(in);
        }

        @Override
        public PictureModels[] newArray(int size) {
            return new PictureModels[size];
        }
    };

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }




    public static  PictureModels[] getSpacePhotos() {

        PictureModels[] a = new PictureModels[b];

        String [] lLlist = new String[b];
        for (int i=0;i<b;i++){lLlist[i]=lList1.get(i);}
        for (int i = 0; i<b;){
            a[i] = new PictureModels(lLlist[i]);
            i++;
        }



        return   a;
//                {

//
    }
    public static void setPhoto(ArrayList<String> lList){
        lList1 = lList;
         b = lList.size();
//         String [] lLlist = new String [b];
//         for (int i=0;i<b+1;){lLlist[i]=lList1.get(i);}
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUrl);
        parcel.writeString(mTitle);
    }

}
