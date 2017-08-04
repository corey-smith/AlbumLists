package com.albumlists;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//get the toolbar out of main.xml and set it as the actionbar for the app
		DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		Toolbar main_toolbar = (Toolbar) findViewById(R.id.main_toolbar);
		setSupportActionBar(main_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		//TODO:Flesh this out more
		ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, main_toolbar, R.string.drawer_string1, R.string.drawer_string2) {
			public void onDrawerClosed(View view) {
		         supportInvalidateOptionsMenu();
		         //drawerOpened = false;
		      }
		      public void onDrawerOpened(View drawerView) {
		         supportInvalidateOptionsMenu();
		         //drawerOpened = true;
		      }
		};
		mDrawerToggle.setDrawerIndicatorEnabled(true);
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	        case R.id.search:
	        	Log.d("SEARCH","SEEEEEEEEEEEEARCH");
	        	SearchDialog searchDialog = new SearchDialog(this);
	        	searchDialog.show();
	            return true;
	        default:
	        //this should just be the overflow menu item
			return super.onOptionsItemSelected(item);
		}
	}
}
