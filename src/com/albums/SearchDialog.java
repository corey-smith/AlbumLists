package com.albums;

import com.albumlists.R;
import com.albums.controller.SearchController;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * UI class for SearchDialog
 * This should handle most UI logic while SearchDialogController handles processing logic
 */
public class SearchDialog extends Dialog {
    Context context;
    SearchController searchController;
    ProgressDialog progressDialog = null;
    AlertDialog errorDialog = null;

    public SearchDialog(Context context) {
        super(context);
        this.context = context;
        searchController = new SearchController(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_search);
        Spinner searchSpinner = (Spinner) findViewById(R.id.search_type_spinner);
        ArrayAdapter<CharSequence> searchTypeAdapter = ArrayAdapter.createFromResource(this.context, R.array.search_types, android.R.layout.simple_spinner_dropdown_item);
        searchTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchSpinner.setAdapter(searchTypeAdapter);
        final Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                searchController.search();
            }
        });
    }

    /**
     * Error Dialog related methods
     */
    public void createErrorDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle("ERROR").setMessage("There was an error processing the search.");
        errorDialog = alertBuilder.create();
    }

    public void showErrorDialog() {
        if (errorDialog == null) {
            createErrorDialog();
        }
        errorDialog.show();
    }

    public void hideErrorDialog() {
        errorDialog.hide();
    }

    /**
     * Progress Dialog related methods
     */
    public void createProgressDialog() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Searching. Please wait...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    public void showPleaseWait() {
        if (progressDialog == null) {
            createProgressDialog();
        }
        progressDialog.show();
    }

    public void hidePleaseWait() {
        progressDialog.hide();
    }
}
