package com.albums;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import com.albumlists.R;
import com.albums.api.ApiUrls;
import albums.util.URLUtils;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SearchDialog extends Dialog {
    Context context;

    public SearchDialog(Context context) {
        super(context);
        this.context = context;
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
                search();
            }
        });
    }

    public void search() {
        HashMap<String, String> valueMap = AppSettings.getBaseValuesMap();
        EditText searchField = (EditText) findViewById(R.id.search_text);
        String searchValue = searchField.getText().toString();
        if (searchValue.length() > 0) {
            try {
                String encodedValue = URLEncoder.encode(searchValue, "UTF-8");
                valueMap.put("search_value", encodedValue);
                String searchUrl = URLUtils.populate(ApiUrls.SEARCH.toString(), valueMap);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
