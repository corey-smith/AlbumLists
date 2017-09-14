package com.albums.api;

import com.albums.model.AlbumsSearchResponse;
import com.albums.model.ArtistAlbumsSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Supported API endpoints
 */
public interface APIInterface {
    @GET(".")
    Call<AlbumsSearchResponse> searchAlbums(@Query("method") String searchMethod, @Query("album") String albumName, @Query("api_key") String apiKey, @Query("format") String format, @Query("limit") String limit);
    @GET(".")
    Call<ArtistAlbumsSearchResponse> searchArtistAlbums(@Query("method") String searchMethod, @Query("artist") String artistName, @Query("api_key") String apiKey, @Query("format") String format, @Query("limit") String limit);
}
