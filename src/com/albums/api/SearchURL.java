package com.albums.api;

/**
 * Enum for search types, holding a base URL and placeholder for variable values
 * Since this is the only(?) API being interfaced with initially, this is just an enum
 * May need to make this into a more complex building process as more end points are used
 */
public enum SearchURL {

	SEARCH("http://ws.audioscrobbler.com/2.0/?method=album.search&album={album_name}&api_key={api_key}&format=json");

	private final String value;
	
	private SearchURL(final String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
