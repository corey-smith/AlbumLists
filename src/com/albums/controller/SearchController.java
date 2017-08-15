package com.albums.controller;

import java.util.List;
import com.albums.api.API;
import com.albums.model.Album;
import com.albums.ui.dialog.SearchDialog;
import com.albums.ui.mb.ErrorMessageBox;
import com.albums.ui.mb.WaitMessageBox;
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
    public void search(EditText searchField) {
        String searchValue = searchField.getText().toString();
        if (searchValue.length() > 0) {
            searchDialog.toggleMessageBox(WaitMessageBox.class);
            API.searchAlbums(this, searchValue);
        }
    }

    /**
     * Could make this more generic, but right now this will only support a List of Albums
     * @param resultSet - result list of Album objects
     */
    public void processSearchResponse(List<Album> resultSet) {
        if (searchDialog.isShowing() && resultSet != null) {
            searchDialog.setCurrentAlbumList(resultSet);
            searchDialog.loadImages();
        } else if (resultSet == null) {
            searchDialog.toggleMessageBox(ErrorMessageBox.class);
        }
    }
}
