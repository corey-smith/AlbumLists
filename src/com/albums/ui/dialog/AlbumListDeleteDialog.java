package com.albums.ui.dialog;

import com.albums.model.AlbumList;
import com.albums.ui.BaseAlbumActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

/**
 * Dialog for deleting items, this is essentially just an "Are you sure?" message
 */
public class AlbumListDeleteDialog {
    
    BaseAlbumActivity baseAlbumActivity;
    AlbumList currentList;
    
    public AlbumListDeleteDialog(BaseAlbumActivity baseAlbumActivity, AlbumList currentList) {
        this.baseAlbumActivity = baseAlbumActivity;
        this.currentList = currentList;
    }
    
    /**
     * Build dialog, add click listener
     * Selecting the positive button will remove the list from the static metalist
     */
    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(baseAlbumActivity);
        builder.setTitle("Delete List");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() { 
            @Override
            public void onClick(DialogInterface localDialog, int which) {
                BaseAlbumActivity.getMetaList().remove(currentList);
                try {
                    baseAlbumActivity.refreshLists();
                } catch (Exception e) {
                    Log.e("AlbumsList", "Unoveridden list refresh error", e);
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() { 
            @Override
            public void onClick(DialogInterface localDialog, int which) {
                localDialog.cancel();
            }
        });
        builder.show();
    }
}
