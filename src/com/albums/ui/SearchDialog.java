package com.albums.ui;

import java.util.HashMap;
import java.util.List;
import com.albumlists.R;
import com.albums.controller.SearchController;
import com.albums.model.Album;
import com.albums.ui.mb.AlbumsMessageBox;
import com.albums.ui.mb.ErrorMessageBox;
import com.albums.ui.mb.WaitMessageBox;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
public class SearchDialog extends Dialog {
    MainActivity context;
    SearchController searchController;
    HashMap<Class<? extends AlbumsMessageBox>, AlbumsMessageBox> messageBoxMap;
    EditText searchField;

    public SearchDialog(Context context) {
        super(context);
        this.context = (MainActivity) context;
        initializeMessageBoxes();
        searchController = new SearchController(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_search);
        Spinner searchSpinner = (Spinner) findViewById(R.id.search_type_spinner);
        ArrayAdapter<CharSequence> searchTypeAdapter = ArrayAdapter.createFromResource(this.context, R.array.search_types, android.R.layout.simple_spinner_dropdown_item);
        searchTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchSpinner.setAdapter(searchTypeAdapter);
        Button searchButton = (Button) findViewById(R.id.search_button);
        searchField = (EditText) findViewById(R.id.search_text);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                searchController.search(searchField);
            }
        });
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

    //TODO: surely this can be reimplemented in a better looking way
    public void populateAlbumListView(List<Album> resultSet) {
        hideKeyBoard();
        ListView albumList = (ListView) getLayoutInflater().inflate(R.layout.album_list_view, null);
        AlbumListArrayAdapter<Album> adapter = new AlbumListArrayAdapter<Album>(context, android.R.layout.simple_list_item_1, resultSet);
        albumList.setAdapter(adapter);
        RelativeLayout searchView = (RelativeLayout) findViewById(R.id.search_view);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        Button searchButton = (Button) findViewById(R.id.search_button);
        layoutParams.addRule(RelativeLayout.BELOW, searchButton.getId());
        albumList.setLayoutParams(layoutParams);
        searchView.addView(albumList);
    }
    
    private void hideKeyBoard() {
        InputMethodManager keyBoard = context.getKeyBoard();
        keyBoard.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
}
