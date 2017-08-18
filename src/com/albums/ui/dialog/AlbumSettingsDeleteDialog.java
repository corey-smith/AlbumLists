package com.albums.ui.dialog;

import com.albumlists.R;
import com.albums.ui.BaseAlbumActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Class to handle Settings/Delete dialog when doing long click on album list from main activity drawer
 * This dialog has 2 buttons, Settings and Delete - these should call out to other corresponding dialogs on click
 */
public class AlbumSettingsDeleteDialog {

    BaseAlbumActivity baseAlbumActivity;
    AlertDialog dialog;
    int position;
    
    public AlbumSettingsDeleteDialog(BaseAlbumActivity baseAlbumActivity, int position) {
        this.baseAlbumActivity = baseAlbumActivity;
        this.position = position;
    }
    
    /**
     * Build dialog, add listeners, display
     */
    @SuppressLint("InflateParams")
    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(baseAlbumActivity);
        LayoutInflater inflater = baseAlbumActivity.getLayoutInflater();
        LinearLayout dialogView = (LinearLayout) inflater.inflate(R.layout.dialog_settings_delete, null);
        builder.setView(dialogView);
        dialog = builder.create();
        Button deleteButton = (Button) dialogView.findViewById(R.id.list_delete_button);
        Button settingsButton = (Button) dialogView.findViewById(R.id.list_settings_button);
        deleteButton.setOnClickListener(new DeleteButtonClickListener());
        settingsButton.setOnClickListener(new SettingsButtonClickListener());
        dialog.show();
    }
    
    /**
     * Delete button listener, dismiss this dialog and manually build/display delete dialog
     * Delete dialog is essentially just a message to make sure this list should be deleted
     * There's currently (and probably never will be) a way to recover deleted dialogs after this point
     */
    public class DeleteButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
            DeleteDialog deleteDialog = new DeleteDialog(baseAlbumActivity, BaseAlbumActivity.getMetaList().get(position), BaseAlbumActivity.getMetaList());
            deleteDialog.show();
        }
    }
    
    /**
     * Settings button listener, dismiss current dialog and display settings dialog
     */
    public class SettingsButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
            AlbumListSettingsDialog listSettingsDialog = new AlbumListSettingsDialog(baseAlbumActivity, BaseAlbumActivity.getMetaList().get(position));
            listSettingsDialog.show();
        }
    }
}
