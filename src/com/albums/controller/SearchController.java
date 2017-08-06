package com.albums.controller;

import java.util.List;
import com.albumlists.R;
import com.albums.SearchDialog;
import com.albums.api.API;
import com.albums.model.Album;
import android.widget.EditText;

/**
 * Controller class for SearchDialog
 * This should handle most processing logic while SearchDialog handles UI logic
 */
public class SearchController {
    SearchDialog searchDialog;

    public SearchController(SearchDialog searchDialog) {
        this.searchDialog = searchDialog;
    }

    /**
     * Perform actual search, should be executed on clicking the search button from the dialog
     */
    public void search() {
        EditText searchField = (EditText) searchDialog.findViewById(R.id.search_text);
        String searchValue = searchField.getText().toString();
        if (searchValue.length() > 0) {
            searchDialog.showPleaseWait();
            API.searchAlbums(this, searchValue);
        }
    }

    /**
     * Could make this more generic, but right now this will only support a List of Albums
     * @param resultSet - result list of Album objects
     */
    public void processSearchResponse(List<Album> resultSet) {
        if (searchDialog.isShowing() && resultSet != null) {
            System.out.println(resultSet.size());
        } else if (resultSet == null) {
            searchDialog.showErrorDialog();
        }
        searchDialog.hidePleaseWait();
    }
}
