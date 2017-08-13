package com.albums.ui;

import java.util.ArrayList;
import java.util.List;
import com.albumlists.R;
import com.albums.model.AlbumList;
import com.albums.ui.dialog.NewListDialog;
import com.albums.ui.dialog.SearchDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

public class BaseAlbumActivity extends AppCompatActivity {
    protected static List<AlbumList> metaList = new ArrayList<AlbumList>();

    /**
     * Menu listener
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.search:
            SearchDialog searchDialog = new SearchDialog(this);
            searchDialog.show();
            return true;
        case R.id.action_new:
            NewListDialog newListDialog = new NewListDialog(this);
            newListDialog.show();
            return true;
        default:
            return false;
        }
    }

    /**
     * Getter for global meta list
     * The meta list is a list of all saved album lists
     * @return - static metalist
     */
    public static List<AlbumList> getMetaList() {
        return metaList;
    }

    /**
     * Callback from NewListDialog class, this should only be returned if a list name was input
     * @param listName
     */
    public void createNewList(String listName) {
        AlbumList newAlbumList = new AlbumList(listName);
        metaList.add(newAlbumList);
    }

    /**
     * Refresh metalist/list settings
     * This should be called when one list's settings change and should always be overridden based on activity
     * This is here to refresh UI elements corresponding to album settings
     * @throws Exception
     */
    public void refreshListSettings() throws Exception {
        throw new Exception("Must override refresh list settings in subclass");
    }

    /**
     * Get keyboard for this context. This should probably be an interface for all of the contexes once more exist
     * @return - current InputMethodManager for this context (keyboard)
     */
    public InputMethodManager getKeyBoard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        return inputMethodManager;
    }
}
