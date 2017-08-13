package com.albums.ui.mb;

import android.app.Dialog;
import android.content.Context;

/**
 * This is a dialog with no input
 * This may be a little over-engineered
 */
public abstract class AlbumsMessageBox {
    Dialog messageBoxDialog = null;

    public abstract void build(Context context);

    public void toggle(Context context) {
        if (messageBoxDialog == null || !messageBoxDialog.isShowing()) {
            show(context);
        } else {
            dismiss();
        }
    }

    public void show(Context context) {
        if (messageBoxDialog == null) {
            build(context);
        }
        messageBoxDialog.show();
    }

    public void dismiss() {
        messageBoxDialog.dismiss();
    }
}
