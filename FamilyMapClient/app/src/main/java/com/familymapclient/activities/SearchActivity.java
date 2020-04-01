package com.familymapclient.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.familymapclient.R;

import java.util.Objects;

public class SearchActivity extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.search_activity);

        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Search");
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(),MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
