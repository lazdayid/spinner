package com.lazday.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.lazday.spinner.api.ApiClient;
import com.lazday.spinner.api.ApiInterface;
import com.lazday.spinner.model.Flower;
import com.lazday.spinner.util.ImgDownload;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface api;
    Spinner spinner;
    ImageView imageView;

    List<String> listName, listPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        imageView = findViewById(R.id.image);

        api = ApiClient.getClient().create(ApiInterface.class);
        getData();
    }

    private void getData(){
        Call<List<Flower>> flower = api.getFlower();
        flower.enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                Log.e("_logRes", response.body().toString());

                listName   = new ArrayList();
                listPhoto  = new ArrayList();

//                listName.add("Choose an Flower");
//                listPhoto.add("");

                List<Flower> flowerList = response.body();
                for (int i=0; i < flowerList.size(); i++){
                    Log.e( "_logName", flowerList.get(i).getName() );
                    listName.add( flowerList.get(i).getName() );
                    listPhoto.add( flowerList.get(i).getPhoto() );
                }

                ArrayAdapter<String> arrayAdapter =
                        new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listName);
                spinner.setAdapter(arrayAdapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.e("itemName", spinner.getItemAtPosition(position).toString());
                        Log.e("itemPhoto", listPhoto.get(position));

                        ImgDownload.picasso(imageView, listPhoto.get(position));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable t) {
                Log.e("_logErr", t.toString());
            }
        });
    }
}
