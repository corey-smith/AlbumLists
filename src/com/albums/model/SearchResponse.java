package com.albums.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

/**
 * Results object for an album.search request to Last.fm This is bloated and has a lot of inner classes so that Gson can map the response to an object
 * These correspond to the Last.FM search API response
 */
@SuppressWarnings("unused")
public class SearchResponse {
    private Results results;

    public List<Album> getAlbums() {
        return this.results.getAlbums();
    }

    class Results {
        @SerializedName("opensearch:Query")
        private OpenSearchQuery opensearchQuery;
        @SerializedName("opensearch:startIndex")
        private String opensearchStartIndex;
        @SerializedName("opensearch:itemsPerPage")
        private String opensearchItemsPerPage;
        @SerializedName("albummatches")
        private AlbumsWrapper albumsWrapper;

        public List<Album> getAlbums() {
            return this.albumsWrapper.getAlbums();
        }

        class OpenSearchQuery {
            @SerializedName("#text")
            private String text;
            private String role;
            private String searchTerms;
            private String startPage;
        }

        private class AlbumsWrapper {
            @SerializedName("album")
            private List<Album> albums = null;

            public List<Album> getAlbums() {
                return this.albums;
            }
        }
    }
}
