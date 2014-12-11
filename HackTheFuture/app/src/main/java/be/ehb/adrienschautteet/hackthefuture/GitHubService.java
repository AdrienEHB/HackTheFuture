package be.ehb.adrienschautteet.hackthefuture;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by adrienschautteet on 11/12/14.
 */
public interface GitHubService {

    @GET("/cities/{city}")
    List<City> cities(@Path("name") String name);
}
