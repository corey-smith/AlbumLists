package com.albums.ui;

import android.app.Dialog;
import android.content.Context;

/**
 * This is essentially a dialog, but there's so much common code that I wanted to centralize most of it
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
