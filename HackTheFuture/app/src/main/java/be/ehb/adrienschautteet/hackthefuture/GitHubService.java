package be.ehb.adrienschautteet.hackthefuture;


import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by adrienschautteet on 11/12/14.
 */
public interface GitHubService {

    @GET("/cities/v1/citycollection")
    void cities(Callback<Cities> loadData);
}
