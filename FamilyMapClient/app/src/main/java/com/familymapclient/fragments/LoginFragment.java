package com.familymapclient.fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.familymapclient.activities.MainActivity;
import com.familymapclient.R;
import com.familymapclient.net.HttpClient;

import lib.requests.LoginRequest;
import lib.requests.RegisterRequest;
import lib.responses.LoginResult;
import lib.responses.RegisterResult;

public class LoginFragment extends Fragment {

    private MainActivity main_activity;
    private static final String TAG = "LOGIN_FRAGMENT";
    public static final String ARG_TITLE = "title";

    Toast toast_message;

    private boolean success_login = false;
    private boolean success_register = false;

    private EditText host_field;
    private EditText port_field;
    private EditText user_field;
    private EditText password_field;
    private EditText first_name_field;
    private EditText last_name_field;
    private EditText email_field;
    private RadioGroup gender_field;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @SuppressLint("ShowToast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        toast_message = Toast.makeText(getContext(), "", Toast.LENGTH_SHORT);

        host_field = (EditText)view.findViewById(R.id.field_host);
        port_field = (EditText)view.findViewById(R.id.field_port);
        user_field = (EditText)view.findViewById(R.id.field_user_name);
        password_field = (EditText)view.findViewById(R.id.field_password);
        first_name_field = (EditText)view.findViewById(R.id.field_first_name);
        last_name_field = (EditText)view.findViewById(R.id.field_last_name);
        email_field = (EditText)view.findViewById(R.id.field_email);
        gender_field = (RadioGroup)view.findViewById(R.id.radio_group_gender);

        Button sign_in_button = (Button) view.findViewById(R.id.button_signin);
        Button register_button = (Button) view.findViewById(R.id.button_register);

        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInButtonClicked();
            }
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerButtonClicked();
            }
        });

        return view;
    }

    private void signInButtonClicked() {
        LoginService loginService = new LoginService();
        loginService.execute();
    }

    private void registerButtonClicked() {
        RegisterService registerService = new RegisterService();
        registerService.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public class RegisterService extends AsyncTask<Void, String, RegisterResult> {

        String ip;
        String port;
        String user;
        String password;
        String first_name;
        String last_name;
        String email;
        String gender;

        void readFields(){
            this.ip = host_field.getText().toString();
            this.port = port_field.getText().toString();
            this.user = user_field.getText().toString();
            this.password = password_field.getText().toString();
            this.first_name = first_name_field.getText().toString();
            this.last_name = last_name_field.getText().toString();
            this.email = email_field.getText().toString();
            int genderID = gender_field.getCheckedRadioButtonId();
            if (genderID == R.id.radio_male)
                this.gender = "m";
            else
                this.gender = "f";
        }

        @Override
        protected RegisterResult doInBackground(Void... voids) {

            readFields();

            RegisterRequest request = new RegisterRequest();
            request.userName = this.user;
            request.password = this.password;
            request.email = this.email;
            request.firstName = this.first_name;
            request.lastName = this.last_name;
            request.email = this.email;
            request.gender = this.gender;

            publishProgress("Registering...");

            HttpClient.start("192.168.1.100", "8080");

            return HttpClient.getInstance().register(request);
        }

        protected void onProgressUpdate(String... updateMessage) {
            toast_message.setText(updateMessage[0]);
            toast_message.show();
        }

        protected void onPostExecute(RegisterResult registerResult) {
            if (!registerResult.success) {
                toast_message.setText(registerResult.message);
                toast_message.show();
            }
            else
            {
                main_activity = (MainActivity)getContext();
                assert main_activity != null;
                main_activity.switchToMapFragment();
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class LoginService extends AsyncTask<Void, String, LoginResult> {

        String ip;
        String port;
        String user;
        String password;

        void readFields() {
            this.ip = host_field.getText().toString();
            this.port = port_field.getText().toString();
            this.user = user_field.getText().toString();
            this.password = password_field.getText().toString();
        }

        @Override
        protected LoginResult doInBackground(Void... voids) {

            readFields();

            LoginRequest request = new LoginRequest();
            request.userName = this.user;
            request.password = this.password;

            HttpClient.start("192.168.1.100", "8080");

            return HttpClient.getInstance().login(request);
        }

        protected void onProgressUpdate(String... updateMessage) {
            toast_message.setText(updateMessage[0]);
            toast_message.show();
        }

        protected void onPostExecute(LoginResult loginResult) {
            if (!loginResult.success) {
                toast_message.setText(loginResult.message);
                toast_message.show();
            }
            else
            {
                main_activity = (MainActivity)getContext();
                assert main_activity != null;
                main_activity.switchToMapFragment();
            }
        }
    }


}
