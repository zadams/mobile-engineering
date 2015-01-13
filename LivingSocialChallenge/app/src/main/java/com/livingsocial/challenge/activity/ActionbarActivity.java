package com.livingsocial.challenge.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

/**
 * Created by Ale on 1/12/15.
 */

public class ActionbarActivity extends FragmentActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();                
        		break;
        	default:
        		break;
    	}
        return super.onOptionsItemSelected(item);
    }
}
