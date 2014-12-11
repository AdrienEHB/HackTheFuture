package be.ehb.adrienschautteet.hackthefuture;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import retrofit.RestAdapter;


public class ActivityOne extends Activity implements View.OnClickListener {

    private GitHubService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_one);

        RestAdapter restAdapter = new RestAdapter().Builder()
                .setEndPoint("https://apis-explorer.appspot.com/apis-explorer/?base=https://hackaton-backend.appspot.com/_ah/api#p/cities/v1/")
                .build();

        GitHubService service = restAdapter.create(GitHubService.class);

    }

    @Override
    public void onClick(View v) {
        List<City> repos = service.listRepos("octocat");
    }
}
