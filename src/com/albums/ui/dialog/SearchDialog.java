package com.albums.ui.dialog;

import java.util.List;
import com.albumlists.R;
import com.albums.api.API;
import com.albums.controller.AlbumLoadable;
import com.albums.controller.AlbumLoader;
import com.albums.model.Album;
import com.albums.ui.AlbumListActivity;
import com.albums.ui.AlbumListArrayAdapter;
import com.albums.ui.BaseAlbumActivity;
import com.albums.ui.mb.ErrorMessageBox;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Spinner;

/**
 * UI class for SearchDialog
 * This should handle most UI logic while SearchDialogController handles processing logic
 */
public class SearchDialog extends Dialog implements AlbumLoadable {
    BaseAlbumActivity context;
    RelativeLayout searchView = null;
    ListView albumListView = null;
    EditText searchField = null;
    List<Album> currentAlbumList = null;
    AlbumLoader albumLoader;

    public SearchDialog(BaseAlbumActivity context) {
        super(context);
        this.context = (BaseAlbumActivity) context;
        this.albumLoader = new AlbumLoader(context, this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_search);
        searchView = (RelativeLayout) findViewById(R.id.search_view);
        buildSearchTypeSpinner();
        buildSearchListener();
    }

    /**
     * Load values into search type spinner
     */
    private void buildSearchTypeSpinner() {
        Spinner searchSpinner = (Spinner) findViewById(R.id.search_type_spinner);
        ArrayAdapter<CharSequence> searchTypeAdapter = ArrayAdapter.createFromResource(this.context, R.array.search_types, android.R.layout.simple_spinner_dropdown_item);
        searchTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchSpinner.setAdapter(searchTypeAdapter);
    }

    /**
     * Create listener for search button
     */
    private void buildSearchListener() {
        Button searchButton = (Button) findViewById(R.id.search_button);
        searchField = (EditText) findViewById(R.id.search_text);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                search(searchField);
            }
        });
    }

    /**
     * Set current list - this should correspond to what's in the ListView for the search results
     * @param currentAlbumList - currentAlbumList
     */
    public void setCurrentAlbumList(List<Album> currentAlbumList) {
        this.currentAlbumList = currentAlbumList;
    }

    /**
     * Callback method originating from search API response and passing through search controller
     * Create/populate listview
     * @param resultSet - List of Albums returned from search
     */
    @Override
    public void populateAlbumListView() {
        hideKeyBoard();
        if (albumListView == null) {
            albumListView = createAlbumListView();
            addListViewListeners();
            searchView.addView(albumListView);
        }
        AlbumListArrayAdapter adapter = new AlbumListArrayAdapter(context, R.layout.album_list_item, this.currentAlbumList);
        albumListView.setAdapter(adapter);
    }

    // TODO: Make this work with AlbumListActivity
    private void addListViewListeners() {
        if (this.context instanceof AlbumListActivity) {
            albumListView.setOnItemLongClickListener(new OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    AlbumListActivity albumListActivity = (AlbumListActivity) context;
                    AddAlbumDialog addAlbumDialog = new AddAlbumDialog(albumListActivity, currentAlbumList);
                    addAlbumDialog.show(position);
                    return true;
                }
            });
        }
    }

    /**
     * Create and position empty ListView
     * @return - empty ListView in correct position
     */
    private ListView createAlbumListView() {
        ListView returnView = (ListView) View.inflate(context, R.layout.album_list_view, null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        Button searchButton = (Button) findViewById(R.id.search_button);
        layoutParams.addRule(RelativeLayout.BELOW, searchButton.getId());
        returnView.setLayoutParams(layoutParams);
        return returnView;
    }

    /**
     * Perform actual search, should be executed on clicking the search button from the dialog
     */
    public void search(EditText searchField) {
        String searchValue = searchField.getText().toString();
        if (searchValue.length() > 0) {
            API.searchAlbums(this, searchValue);
        }
    }

    /**
     * Could make this more generic, but right now this will only support a List of Albums
     * @param resultSet - result list of Album objects
     */
    public void processSearchResponse(List<Album> resultSet) {
        if (this.isShowing() && resultSet != null) {
            setCurrentAlbumList(resultSet);
            albumLoader.loadImages(resultSet);
        } else if (resultSet == null) {
            ErrorMessageBox errorMessage = new ErrorMessageBox();
            errorMessage.show(context);
        }
    }

    /**
     * Method to ... hide the keyboard.
     */
    private void hideKeyBoard() {
        InputMethodManager keyBoard = context.getKeyBoard();
        keyBoard.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
}
