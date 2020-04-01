package com.familymapclient.activities;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.familymapclient.R;
import com.familymapclient.UserData;
import com.familymapclient.fragments.LoginFragment;
import com.familymapclient.fragments.MapFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.main_activity);

        if (UserData.getInstance().auth_token.equals(""))
            switchToLoginFragment();
        else
            switchToMapFragment();
    }

    public void switchToMapFragment(){
        MapFragment mapFragment = new MapFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.mainFrame, mapFragment)
                .commit();
    }

    public void switchToLoginFragment(){
        LoginFragment loginFragment = new LoginFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.mainFrame, loginFragment)
                .commit();
    }
}
