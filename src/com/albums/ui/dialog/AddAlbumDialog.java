package com.albums.ui.dialog;

import java.util.List;
import com.albums.model.Album;
import com.albums.model.AlbumList;
import com.albums.ui.AlbumListActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class AddAlbumDialog {
    
    //this should only really be needed when adding from an open album list - not from the main activity
    AlbumList currentList;
    AlbumListActivity albumListActivity;
    List<Album> selectionAlbumList;
    
    public AddAlbumDialog(AlbumListActivity albumListActivity, List<Album> selectionAlbumList) {
        this.albumListActivity = albumListActivity;
        this.currentList = this.albumListActivity.getList();
        this.selectionAlbumList = selectionAlbumList;
    }
    
    public void show(final int position) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    currentList.add(selectionAlbumList.get(position));
                    albumListActivity.populateAlbumListView(currentList.getAlbums());
                    break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(albumListActivity);
        builder.setMessage("Add Album").setPositiveButton("Add", dialogClickListener).setNegativeButton("Cancel", dialogClickListener);
        builder.show();
    }
}
