package com.dejure.mvvm.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.dejure.mvvm.Adapter.AdapterTvShow;
import com.dejure.mvvm.Interface.OnItemClick;
import com.dejure.mvvm.Model.ModelTvShow;
import com.dejure.mvvm.R;
import com.dejure.mvvm.ResponseApi.ResponseTvShow;
import com.dejure.mvvm.Retrofit.ApiClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<ResponseTvShow> responseTvShowList;
    private AdapterTvShow adapterTvShow;

    private RecyclerView tvShowsRecyclerView;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvShowsRecyclerView = findViewById(R.id.tvShowsRecyclerView);
        adapterTvShow = new AdapterTvShow(MainActivity.this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);


        loadTopListOfShows();

    }

    private void loadTopListOfShows() {


        responseTvShowList = new ArrayList<>();
        progressDialog.show();

        ApiClient.getApiServices().getListOfTvShows(1)
                .enqueue(new Callback<ModelTvShow>() {
                    @Override
                    public void onResponse(Call<ModelTvShow> call, Response<ModelTvShow> response) {
                        responseTvShowList = response.body().getTvShows();
                        adapterTvShow.setModelTvShowList(responseTvShowList);
                        tvShowsRecyclerView.setAdapter(adapterTvShow);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<ModelTvShow> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Failed To load", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OnItemClick event) {
        if(event.isClick()){
            String cou = event.getCountry();
            String stat = event.getStatus();

            Toast.makeText(this, ""+cou, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, ""+stat, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}