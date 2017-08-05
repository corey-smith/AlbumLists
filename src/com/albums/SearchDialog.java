package com.albums;

import com.albumlists.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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
		ArrayAdapter<CharSequence> searchTypeAdapter = ArrayAdapter.createFromResource(this.context,
				R.array.search_types, android.R.layout.simple_spinner_dropdown_item);
		searchTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		searchSpinner.setAdapter(searchTypeAdapter);
	}
}
