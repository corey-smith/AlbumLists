package com.albums;

import java.util.HashMap;
import com.albumlists.R;
import com.albums.controller.SearchController;
import com.albums.ui.AlbumsMessageBox;
import com.albums.ui.ErrorMessageBox;
import com.albums.ui.WaitMessageBox;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * UI class for SearchDialog
 * This should handle most UI logic while SearchDialogController handles processing logic
 */
public class SearchDialog extends Dialog {
    Context context;
    SearchController searchController;
    HashMap<Class<? extends AlbumsMessageBox>, AlbumsMessageBox> messageBoxMap;

    public SearchDialog(Context context) {
        super(context);
        this.context = context;
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
        final Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                searchController.search();
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
}
