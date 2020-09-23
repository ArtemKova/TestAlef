package com.ka.testalef;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;


import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> lList = new ArrayList();
    Bitmap selectedImage;
    URL url;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ParseTask().execute();

        Intent galleryIntent = new Intent(MainActivity.this, GalleryActivity.class);
//
        startActivity(galleryIntent);


    }

    public class ParseTask extends AsyncTask<Void, Void, String> {

        private static final String TAG = "JSON";
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        JSONArray jsonArrayMessages = new JSONArray();
        String jsonResult = "";

        @Override

        protected String doInBackground(Void... params) {
            // получаем данные с сервера
            try {
                URL url = new URL("http://dev-tasks.alef.im/task-m-001/list.php");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder builder = new StringBuilder();


                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                jsonResult = builder.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonResult;
        }

        @Override
        public void onPostExecute(String json) {
            super.onPostExecute(json);
            // выводим целиком полученную json-строку
            Log.d(TAG, json);
            // int x = 0;
            JSONArray dataJsonObject;
            Uri catURI;
            try {

                dataJsonObject = new JSONArray(json);
//                LatLng[] point_new = new LatLng[dataJsonObject.length()];
//                JSONArray cats = dataJsonObject.getJSONArray(0);
                int a = json.length();
                char z;
                char k;
                String sz = ",";
                z = sz.charAt(0);
                String sk = "]";
                k = sk.charAt(0);
                int b = 2;
                int c;
                json = json + " ";

                for (int i = 0; i < a; i++) {
//
                    if (json.charAt(i) == (z) || (json.charAt(i) == k)) {

                        c = i - 1;
                        String j = json.substring(b, c);
                        if (json.charAt(i) != k) {
                            b = i + 2;
                        }
//
//
                        lList.add(j);
//


                    }
//
                    i = i++;
                }



            } catch (JSONException e) {
                e.printStackTrace();
//            }
            }

                PictureModels.setPhoto(lList);


        }



    }


}


