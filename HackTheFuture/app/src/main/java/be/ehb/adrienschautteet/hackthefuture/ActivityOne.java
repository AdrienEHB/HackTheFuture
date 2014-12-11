package be.ehb.adrienschautteet.hackthefuture;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;

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

                JSONArray items = new JSONArray();

                for (City city : cities.items) {

                    long id = dataFacade.insertCity(city.id, city.name, city.zipcode, city.province, city.alertCode, city.kind);
                    if (!(id < 0)) {
                        Log.v(this.getClass().toString(), "Successfully added a city to the database");
                    }

                    //JSONObject object = writeJSON(city.id, city.name, city.zipcode, city.province, city.alertCode, city.kind);
                    //items.put(object);
                }

                /*try {
                    Log.e(getClass().getName(), String.valueOf(items.getJSONObject(5)));
                    Log.e(getClass().getName(), String.valueOf(items.getJSONObject(5).getString("alertcode")));
                    StringWriter writer = new StringWriter();
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
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

    /*public JSONObject writeJSON(long id, String name, int zipcode, String province, String alertCode, String kind ) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(("id"), id);
            jsonObject.put(("name"), name);
            jsonObject.put(("zipcode"), zipcode);
            jsonObject.put(("province"), province);
            jsonObject.put(("alertcode"), alertCode);
            jsonObject.put(("kind"), kind);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }*/
}
