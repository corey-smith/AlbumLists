package com.albums.ui.mb;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Wait message box
 * This just has one message - which is plase wait - and a spinner
 */
public class WaitMessageBox extends AlbumsMessageBox {
    @Override
    public void build(Context context) {
        messageBoxDialog = new ProgressDialog(context);
        ProgressDialog progressDialog = (ProgressDialog) messageBoxDialog;
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Searching. Please wait...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
    }
}
