package com.albums.api;

import com.albums.model.SearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET(".")
    Call<SearchResponse> searchAlbums(@Query("method") String searchMethod, @Query("album") String albumName, @Query("api_key") String apiKey, @Query("format") String format);
}
