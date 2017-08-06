package com.albums.api;

import java.util.List;
import com.albums.AppSettings;
import com.albums.model.Album;
import com.albums.model.SearchResponse;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Static singleton class used to make calls through. If more APIs were added, this would need to be abstracted out Since there's just one API so far, this is a really simple class that is basically just going to be the middleman between the app and API
 */
public class API {
    private static final String LAST_FM_API_URL = "http://ws.audioscrobbler.com/2.0/";
    private static APIInterface apiInterface;

    public static void initialize() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(LAST_FM_API_URL).addConverterFactory(GsonConverterFactory.create()).build();
        apiInterface = retrofit.create(APIInterface.class);
        Call<SearchResponse> call = apiInterface.searchAlbums("album.search", "before+today", AppSettings.getBaseValuesMap().get("api_key"), "json");
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onFailure(Call<SearchResponse> call, Throwable error) {
                Log.e("FAILURE", error.toString());
            }

            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse searchResponse = (SearchResponse) response.body();
                List<Album> albums = searchResponse.getAlbums();
                Log.d("TEST RESULT", Integer.toString(albums.size()));
            }
        });
    }
}
