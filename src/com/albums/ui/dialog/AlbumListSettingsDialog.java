package com.albums.ui.dialog;

import com.albums.model.AlbumList;
import com.albums.ui.BaseAlbumActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;

public class AlbumListSettingsDialog {
    private BaseAlbumActivity baseAlbumActivity;
    private AlbumList albumList;
    String listName;

    /**
     * Dialog holding album list settings
     * This is basically the same as the NewListDialog, but will likely be added to and eventually will probably need an actual layout
     * @param baseAlbumActivity
     */
    public AlbumListSettingsDialog(BaseAlbumActivity baseAlbumActivity, AlbumList albumList) {
        this.baseAlbumActivity = baseAlbumActivity;
        this.albumList = albumList;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(baseAlbumActivity);
        builder.setTitle("List Settings");
        final EditText input = new EditText(baseAlbumActivity);
        input.setHint("Name");
        builder.setView(input);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listName = input.getText().toString();
                if(listName.length() > 0) {
                    albumList.setName(listName);
                    try {
                        baseAlbumActivity.refreshListSettings();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
