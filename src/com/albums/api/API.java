package com.albums.api;

import com.albums.AppSettings;
import com.albums.controller.SearchController;
import com.albums.model.SearchResponse;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Static singleton class used to make calls through.
 * If more APIs were added, this would need to be abstracted out Since there's just one API so far, this is a really simple class that is basically just going to be the middleman between the app and API
 */
public class API {
    private static final String LAST_FM_API_URL = "http://ws.audioscrobbler.com/2.0/";
    private static String apiKey;
    private static APIInterface apiInterface;
    private static String format = "json";

    public static void initialize() {
        apiKey = AppSettings.getBaseValuesMap().get("api_key");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(LAST_FM_API_URL).addConverterFactory(GsonConverterFactory.create()).build();
        apiInterface = retrofit.create(APIInterface.class);
    }

    public static void searchAlbums(final SearchController searchController, String searchText) {
        Call<SearchResponse> call = apiInterface.searchAlbums("album.search", searchText, apiKey, format);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onFailure(Call<SearchResponse> call, Throwable error) {
                Log.e("ALBUM SEARCH FAILURE", error.toString());
                searchController.processSearchResponse(null);
            }

            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse searchResponse = (SearchResponse) response.body();
                searchController.processSearchResponse(searchResponse.getAlbums());
            }
        });
    }
}
