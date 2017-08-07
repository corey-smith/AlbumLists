package com.albums.ui.mb;

import android.app.AlertDialog;
import android.content.Context;

public class ErrorMessageBox extends AlbumsMessageBox {
    @Override
    public void build(Context context) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle("ERROR").setMessage("There was an error processing the search.");
        messageBoxDialog = alertBuilder.create();
    }
}
