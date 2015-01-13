package com.livingsocial.challenge.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import com.livingsocial.challenge.R;
import com.livingsocial.challenge.manager.DataListener;
import com.livingsocial.challenge.manager.DataManager;

/**
 * Created by Ale on 1/12/15.
 */

public class SplashActivity extends Activity implements DataListener, View.OnClickListener {
    public static final String INVOICE_FILE = "feed.json";
    public static final int ACTIVITY_REQUEST_CODE_MAIN = 100000;

    private Button button;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ls_activity_splash_screen);
        button = (Button)findViewById(R.id.ls_button);
        button.setOnClickListener(this);
        progressBar = (ProgressBar)findViewById(R.id.ls_progressbar);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
        button.setText(R.string.ls_continue);
        button.setEnabled(false);
        DataManager.getInstance(this).loadInvoice(INVOICE_FILE, this);
    }

    @Override
    public void onClick(View v) {
        if(v == button){
            Intent intent = MainActivity.prepareIntent(this);
            startActivityForResult(intent,ACTIVITY_REQUEST_CODE_MAIN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ACTIVITY_REQUEST_CODE_MAIN){
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        DataManager.getInstance(this).clearListeners();
        super.onDestroy();
    }

    @Override
    public void onSuccess() {
        progressBar.setVisibility(View.INVISIBLE);
        button.setEnabled(true);
    }

    @Override
    public void onError(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.ls_general_data_error);
        builder.setNegativeButton(R.string.ls_close, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}
