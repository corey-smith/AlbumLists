package com.albums.ui.dialog;

import com.albums.ui.BaseAlbumActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;

public class NewListDialog {
    
    BaseAlbumActivity baseAlbumActivity;
    private String listName = null;
    
    /**
     * Dialog for adding new lists/inputing list name
     * @param baseAlbumActivity - context activity, should always be BaseAlbumActivity
     */
    public NewListDialog(BaseAlbumActivity baseAlbumActivity) {
        this.baseAlbumActivity = baseAlbumActivity;
    }
    
    /**
     * Build and display dialog, capture input
     * This calls back to BaseAlbumActivity which takes the input, creates a new List, and adds it to the metalist
     */
    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(baseAlbumActivity);
        builder.setTitle("Create List");
        final EditText input = new EditText(baseAlbumActivity);
        input.setHint("Name");
        builder.setView(input);
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() { 
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listName = input.getText().toString();
                baseAlbumActivity.createNewList(listName);
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
