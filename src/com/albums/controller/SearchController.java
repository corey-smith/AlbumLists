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
}
