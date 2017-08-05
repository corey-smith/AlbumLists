package com.albums;

import com.albumlists.R;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	private DrawerLayout drawerLayout;
	private Toolbar mainToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// get the toolbar out of main.xml and set it as the actionbar for the app
		this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		this.mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
		setSupportActionBar(mainToolbar);
		createDrawer();
		AppSettings.initialize(this);
	}
	
	/**
	 * Function to create drawer/menu items related to drawer
	 */
	private void createDrawer() {
		// TODO:Flesh this out more, probably break this out into an actual class
		ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mainToolbar,
				R.string.drawer_open, R.string.drawer_closed) {
			public void onDrawerClosed(View view) {
				supportInvalidateOptionsMenu();
				// drawerOpened = false;
			}

			public void onDrawerOpened(View drawerView) {
				supportInvalidateOptionsMenu();
				// drawerOpened = true;
			}
		};
		drawerLayout.addDrawerListener(drawerToggle);
		drawerToggle.syncState();
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
			SearchDialog searchDialog = new SearchDialog(this);
			searchDialog.show();
			return true;
		default:
			// this should just be the overflow menu item
			return super.onOptionsItemSelected(item);
		}
	}
}
