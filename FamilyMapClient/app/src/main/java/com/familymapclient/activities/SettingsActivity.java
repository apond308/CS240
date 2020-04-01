package com.familymapclient.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.familymapclient.R;
import com.familymapclient.UserData;

import lib.models.User;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.settings_activity);

        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Settings");

        Switch story_lines_switch = (Switch) findViewById(R.id.switch_life_story);
        Switch tree_lines_switch = (Switch) findViewById(R.id.switch_famliy_tree);
        Switch spouse_lines_switch = (Switch) findViewById(R.id.switch_spouse);
        Switch father_switch = (Switch) findViewById(R.id.switch_father);
        Switch mother_switch = (Switch) findViewById(R.id.switch_mother);
        Switch male_switch = (Switch) findViewById(R.id.switch_male);
        Switch female_switch = (Switch) findViewById(R.id.switch_female);

        UserData user_data = UserData.getInstance();

        story_lines_switch.setChecked(user_data.life_story_lines);
        tree_lines_switch.setChecked(user_data.family_tree_lines);
        spouse_lines_switch.setChecked(user_data.spouse_lines);
        father_switch.setChecked(user_data.fathers_side);
        mother_switch.setChecked(user_data.mothers_side);
        male_switch.setChecked(user_data.male_events);
        female_switch.setChecked(user_data.female_events);

        story_lines_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UserData.getInstance().life_story_lines = isChecked;
            }
        });
        tree_lines_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UserData.getInstance().family_tree_lines = isChecked;
            }
        });
        spouse_lines_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UserData.getInstance().spouse_lines = isChecked;
            }
        });
        father_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UserData.getInstance().fathers_side = isChecked;
            }
        });
        mother_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UserData.getInstance().mothers_side = isChecked;
            }
        });
        male_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UserData.getInstance().male_events = isChecked;
            }
        });
        female_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UserData.getInstance().female_events = isChecked;
            }
        });

        LinearLayout logout_button = (LinearLayout) findViewById(R.id.layout_logout);
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserData.getInstance().auth_token = "";
                Intent myIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(),MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
