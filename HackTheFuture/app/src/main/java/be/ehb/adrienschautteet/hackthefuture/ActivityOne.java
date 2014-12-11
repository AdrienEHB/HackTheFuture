package be.ehb.adrienschautteet.hackthefuture;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ActivityOne extends Activity implements View.OnClickListener {

    private DataFacade dataFacade;
    private GitHubService service;
    private Button loadDataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_one);

        dataFacade = new DataFacade(this);
        loadDataBtn = (Button) findViewById(R.id.loadDataBtn);
        loadDataBtn.setOnClickListener(this);


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://hackaton-backend.appspot.com/_ah/api")
                .build();

        service = restAdapter.create(GitHubService.class);

        service.cities(new Callback<Cities>() {

            @Override
            public void success(Cities cities, Response response) {

                for (City city : cities.items) {

                    long id = dataFacade.insertCity(city.id, city.name, city.zipcode, city.province, city.alertCode, city.kind);
                    if (!(id < 0)) {
                        Log.v(this.getClass().toString(), "Successfully added a city to the database");
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(getClass().getName(), error.getMessage());

            }
        });
    }

    @Override
    public void onClick(View v) {
        ArrayList<CityFromDb> cities = dataFacade.getCities();

        Log.e(getClass().getName(), String.valueOf(cities.get(55)));

    }
}
