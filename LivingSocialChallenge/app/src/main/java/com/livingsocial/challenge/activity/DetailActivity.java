package com.livingsocial.challenge.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import com.livingsocial.challenge.fragment.DetailFragment;
import com.livingsocial.challenge.model.Invoice;

/**
 * Created by Ale on 1/12/15.
 */

public class DetailActivity extends ActionbarActivity {
    public static final String BUNDLE_KEY_INVOICE = "BUNDLE_KEY_INVOICE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        Invoice invoice = (Invoice)getIntent().getSerializableExtra(BUNDLE_KEY_INVOICE);
        DetailFragment fragment = DetailFragment.newInstance(invoice);
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
    }

    public static Intent prepareIntent(Activity activity, Invoice invoice){
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(BUNDLE_KEY_INVOICE, invoice);
        return intent;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        return true;
    }
}
