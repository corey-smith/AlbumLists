package com.albums.ui;

import com.albumlists.R;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

public class BaseAlbumActivity extends AppCompatActivity {
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
        default:
            // this should just be the overflow menu item
            return super.onOptionsItemSelected(item);
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
