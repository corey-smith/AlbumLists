package com.albums.ui.dialog;

import java.util.HashMap;
import java.util.List;
import com.albumlists.R;
import com.albums.controller.AlbumLoadable;
import com.albums.controller.ImageLoadController;
import com.albums.controller.SearchController;
import com.albums.model.Album;
import com.albums.ui.AlbumListActivity;
import com.albums.ui.AlbumListArrayAdapter;
import com.albums.ui.BaseAlbumActivity;
import com.albums.ui.mb.AlbumsMessageBox;
import com.albums.ui.mb.ErrorMessageBox;
import com.albums.ui.mb.WaitMessageBox;
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
    HashMap<Class<? extends AlbumsMessageBox>, AlbumsMessageBox> messageBoxMap;
    SearchController searchController;
    RelativeLayout searchView = null;
    ListView albumListView = null;
    EditText searchField = null;
    List<Album> currentAlbumList = null;

    public SearchDialog(BaseAlbumActivity context) {
        super(context);
        this.context = (BaseAlbumActivity) context;
        initializeMessageBoxes();
        searchController = new SearchController(this);
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
                searchController.search(searchField);
            }
        });
    }

    /**
     * Create/toggle off and on a new type of message box given the type
     * @param MessageBoxClass - a class that extends AlbumsMessageBox class
     */
    public <T extends AlbumsMessageBox> void toggleMessageBox(Class<T> MessageBoxClass) {
        AlbumsMessageBox messageBox = null;
        messageBox = messageBoxMap.get(MessageBoxClass);
        if (messageBox != null) {
            messageBox.toggle(context);
        }
    }

    /**
     * Set current list - this should correspond to what's in the ListView for the search results
     * @param currentAlbumList - currentAlbumList
     */
    public void setCurrentAlbumList(List<Album> currentAlbumList) {
        this.currentAlbumList = currentAlbumList;
    }

    /**
     * Initialize ImageLoadController instance and execute to load all album images
     */
    public void loadImages() {
        ImageLoadController imageLoadController = new ImageLoadController(context, this);
        imageLoadController.loadImages(this.currentAlbumList);
    }

    /**
     * Callback method originating from search API response and passing through search controller
     * Create/populate listview
     * @param resultSet - List of Albums returned from search
     */
    @Override
    public void populateAlbumListView() {
        toggleMessageBox(WaitMessageBox.class);
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
                    System.out.println(currentAlbumList.get(0));
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
     * This is a bit of a convoluted way of doing this, but this screen has 2 possible message boxes
     * The map holds the type/instance for this screen, so outside classes can just call toggle with a class type
     * and this map will get the correct instance and figure out whether to turn it on/off
     */
    private void initializeMessageBoxes() {
        WaitMessageBox waitMessageBox = new WaitMessageBox();
        ErrorMessageBox errorMessageBox = new ErrorMessageBox();
        messageBoxMap = new HashMap<Class<? extends AlbumsMessageBox>, AlbumsMessageBox>();
        messageBoxMap.put(WaitMessageBox.class, waitMessageBox);
        messageBoxMap.put(ErrorMessageBox.class, errorMessageBox);
    }

    /**
     * Method to ... hide the keyboard.
     */
    private void hideKeyBoard() {
        InputMethodManager keyBoard = context.getKeyBoard();
        keyBoard.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
}
