package com.familymapclient.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.familymapclient.R;
import com.familymapclient.fragments.LoginFragment;
import com.familymapclient.fragments.MapFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        LoginFragment loginFragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(LoginFragment.ARG_TITLE, "Login");
        loginFragment.setArguments(args);

        fragmentManager.beginTransaction()
                .replace(R.id.mainFrame, loginFragment)
                .commit();
    }

    public void switchToMapFragment(){
        MapFragment mapFragment = new MapFragment();

        fragmentManager.beginTransaction()
                .replace(R.id.mainFrame, mapFragment)
                .commit();
    }
}
