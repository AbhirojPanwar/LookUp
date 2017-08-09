package io.github.abhiroj.lookup.data;

import java.util.List;

import io.github.abhiroj.lookup.model.Word;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by abhiroj on 3/8/17.
 */

public interface APIService {

   @GET("entries")
   Call<Word> getWord(@Query("headword") String word);

}
