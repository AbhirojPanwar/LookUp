package io.github.abhiroj.lookup.Service;

import io.github.abhiroj.lookup.data.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abhiroj on 9/8/17.
 */

public class ApiClient {

    public static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return  retrofit;
    }

}
