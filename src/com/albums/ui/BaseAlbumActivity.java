package com.albums.ui;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.albumlists.R;
import com.albums.model.AlbumList;
import com.albums.ui.dialog.NewListDialog;
import com.albums.ui.dialog.SearchDialog;
import com.albums.util.JsonUtil;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

/**
 * TODO:
 * Save/Load meta list
 * Save image files of albums in metalist lists?
 * Change style of alert dialogs
 * Update search to search on top toolbar rather than its own dialog
 * --V1.0 DONE--
 * Smoother transitioning between activities?
 * Drag/Drop on lists?
 * Grid View on album lists
 * Display album info/bigger image of album?
 * Expanded search capabilities based on a user ID?
 * Export metalist? What am I going to do when I get a new phone? Might look into syncing through the Google somehow
 */
public class BaseAlbumActivity extends AppCompatActivity {
    protected static List<AlbumList> metaList;
    private static String saveLoc = "AlbumsListSave";

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
     * Get Album out of metalist based on the ID
     * This is used to pull the right list out of the metalist when serializing/deserializing from transitions between activities
     * @param id - UUID to find in the list
     * @return - AlbumList object corresponding to UUID param
     */
    public static AlbumList getAlbumFromId(UUID id) {
        for (AlbumList currentList : metaList) {
            if (currentList.getId().equals(id)) {
                return currentList;
            }
        }
        return null;
    }

    /**
     * Callback from NewListDialog class, this should only be returned if a list name was input
     * @param listName
     */
    public void createNewList(String listName) {
        AlbumList newAlbumList = new AlbumList(listName);
        metaList.add(newAlbumList);
        saveMetaList();
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
     * Save functionality, should be called any time the meta list (or any nested list) is altered
     * This should transform the metalist into a json string and save it off
     * If this starts eating up too much memory, this should be reworked to only save when needed, it should be ok though
     */
    public void saveMetaList() {
        String saveStr = JsonUtil.metaListToString(metaList);
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(saveLoc, MODE_PRIVATE);
            outputStream.write(saveStr.getBytes());
            outputStream.close();
        } catch (Exception e) {
            Log.e("AlbumsList", "Error Saving MetaList", e);
        }
    }

    /**
     * Load meta list from internal storage, should be called once on initializing the app
     * Deserialize json string using gson and set metalist
     */
    public void loadMetaList() {
        try {
            FileInputStream inputStream = openFileInput(saveLoc);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStream.close();
            String metaListStr = stringBuilder.toString();
            metaList = JsonUtil.metaListFromString(metaListStr);
        } catch (Exception e) {
            metaList = new ArrayList<AlbumList>();
            Log.e("AlbumsList", "Error Loading MetaList", e);
        }
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
