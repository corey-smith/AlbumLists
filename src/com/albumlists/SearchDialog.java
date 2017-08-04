package com.albumlists;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class SearchDialog extends Dialog {

	public SearchDialog(Context context) {
		super(context);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.dialog_search);
		
	}
}
