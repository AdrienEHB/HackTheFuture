package be.ehb.adrienschautteet.hackthefuture;

import java.util.List;

import retrofit.http.GET;

/**
 * Created by adrienschautteet on 11/12/14.
 */
public interface GitHubService {

    @GET("cities/v1/citycollection")
    List<City> cities();
}
