package tech.shreshth.oneword;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import tech.shreshth.oneword.model.News;

public interface ApiInterface {

    @GET("/api/v4/search")
    Call<News> getNews(@Query("q") String input,@Query("token") String token);
}
