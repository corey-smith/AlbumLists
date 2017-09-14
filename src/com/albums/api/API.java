package com.albums.api;

import com.albums.model.AlbumsSearchResponse;
import com.albums.model.ArtistAlbumsSearchResponse;
import com.albums.ui.AppSettings;
import com.albums.ui.dialog.SearchDialog;
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
    private static String limit = "20";

    /**
     * Create retrofit object and main interface
     */
    public static void initialize() {
        apiKey = AppSettings.getBaseValuesMap().get("api_key");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(LAST_FM_API_URL).addConverterFactory(GsonConverterFactory.create()).build();
        apiInterface = retrofit.create(APIInterface.class);
    }

    /**
     * Search albums - main search against API, runs a separate search thread which calls back to the SearchController
     * @param searchController - SearchController object linked to the SearchDialog
     * @param searchText - Value to search on 
     */
    public static void searchAlbums(final SearchDialog searchDialog, String searchText) {
        if(searchDialog.getSearchType().toLowerCase().indexOf("album") > -1) {
            searchAlbumsByAlbums(searchText, searchDialog);
        } else {
            searchAlbumsByArtist(searchText, searchDialog);
        }
    }
    
    /**
     * Search by album name
     * TODO: This and the below method are duplicates but they had to be split up because of the way the generic and API calls are working. These should be genericized somehow.
     * @param searchText - Search value
     * @param searchDialog - Search Dialog being searched from
     */
    private static void searchAlbumsByAlbums(String searchText, final SearchDialog searchDialog) {
        Call<AlbumsSearchResponse> call = apiInterface.searchAlbums("album.search", searchText, apiKey, format, limit);
        call.enqueue(new Callback<AlbumsSearchResponse>() {
            @Override
            public void onFailure(Call<AlbumsSearchResponse> call, Throwable error) {
                Log.e("ALBUM SEARCH FAILURE", error.toString());
                searchDialog.processSearchResponse(null);
            }

            @Override
            public void onResponse(Call<AlbumsSearchResponse> call, Response<AlbumsSearchResponse> response) {
                AlbumsSearchResponse searchResponse = (AlbumsSearchResponse) response.body();
                searchDialog.processSearchResponse(searchResponse.getAlbums());
            }
        });
    }
    
    /**
     * Search by artist name
     * @param searchText - Search value
     * @param searchDialog - Search Dialog being searched from
     */
    private static void searchAlbumsByArtist(String searchText, final SearchDialog searchDialog) {
        Call<ArtistAlbumsSearchResponse> call = apiInterface.searchArtistAlbums("album.search", searchText, apiKey, format, limit);
        call.enqueue(new Callback<ArtistAlbumsSearchResponse>() {
            @Override
            public void onFailure(Call<ArtistAlbumsSearchResponse> call, Throwable error) {
                Log.e("ALBUM SEARCH FAILURE", error.toString());
                searchDialog.processSearchResponse(null);
            }

            @Override
            public void onResponse(Call<ArtistAlbumsSearchResponse> call, Response<ArtistAlbumsSearchResponse> response) {
                ArtistAlbumsSearchResponse searchResponse = (ArtistAlbumsSearchResponse) response.body();
                searchDialog.processSearchResponse(searchResponse.getAlbums());
            }
        });
    }
}
