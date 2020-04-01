package com.familymapclient.fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.familymapclient.UserData;
import com.familymapclient.activities.MainActivity;
import com.familymapclient.R;
import com.familymapclient.net.HttpClient;

import lib.models.Person;
import lib.models.User;
import lib.requests.LoginRequest;
import lib.requests.RegisterRequest;
import lib.responses.LoginResult;
import lib.responses.PersonResult;
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

    private Button sign_in_button;
    private Button register_button;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    private void checkIfButtonsAvailable(){
        if (host_field.getText().toString().equals("") ||
            port_field.getText().toString().equals("") ||
            user_field.getText().toString().equals("") ||
            password_field.getText().toString().equals(""))
        {
            sign_in_button.setEnabled(false);
            register_button.setEnabled(false);
        }
        else if (first_name_field.getText().toString().equals("") ||
                last_name_field.getText().toString().equals("") ||
                email_field.getText().toString().equals(""))
        {
            sign_in_button.setEnabled(true);
            register_button.setEnabled(false);
        }
        else{
            sign_in_button.setEnabled(true);
            register_button.setEnabled(true);
        }

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

        sign_in_button = (Button) view.findViewById(R.id.button_signin);
        register_button = (Button) view.findViewById(R.id.button_register);

        sign_in_button.setEnabled(true);
        register_button.setEnabled(false);

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

        TextWatcher text_edit_watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkIfButtonsAvailable();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        };

        host_field.addTextChangedListener(text_edit_watcher);
        port_field.addTextChangedListener(text_edit_watcher);
        user_field.addTextChangedListener(text_edit_watcher);
        password_field.addTextChangedListener(text_edit_watcher);
        first_name_field.addTextChangedListener(text_edit_watcher);
        last_name_field.addTextChangedListener(text_edit_watcher);
        email_field.addTextChangedListener(text_edit_watcher);

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

            HttpClient.start(this.ip, this.port);

            return HttpClient.getInstance().register(request);
        }

        protected void onProgressUpdate(String... updateMessage) {
            toast_message.setText(updateMessage[0]);
            toast_message.show();
        }

        protected void onPostExecute(RegisterResult registerResult) {

            UserData user = UserData.getInstance();
            user.auth_token = registerResult.authToken;

            if (!registerResult.success) {
                if (registerResult.message == null)
                    registerResult.message = "Couldn't connect to server!";
                toast_message.setText(registerResult.message);
                toast_message.show();
            }
            else
            {
                GetPersonService getPersonService = new GetPersonService();
                getPersonService.execute(registerResult.personID);
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
            publishProgress("Logging in...");

            readFields();

            LoginRequest request = new LoginRequest();
            request.userName = this.user;
            request.password = this.password;

            HttpClient.start(this.ip, this.port);

            return HttpClient.getInstance().login(request);
        }

        protected void onProgressUpdate(String... updateMessage) {
            toast_message.setText(updateMessage[0]);
            toast_message.show();
        }

        protected void onPostExecute(LoginResult loginResult) {
            UserData user = UserData.getInstance();
            user.auth_token = loginResult.authToken;

            if (!loginResult.success) {
                if (loginResult.message == null)
                    loginResult.message = "Couldn't connect to server!";
                toast_message.setText(loginResult.message);
                toast_message.show();
            }
            else
            {
                GetPersonService getPersonService = new GetPersonService();
                getPersonService.execute(loginResult.personID);
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class GetPersonService extends AsyncTask<String, String, PersonResult> {

        public String id = "";

        @Override
        protected PersonResult doInBackground(String... id_list) {
            UserData.getInstance().person_list = HttpClient.getInstance().getPersons();
            UserData.getInstance().event_list = HttpClient.getInstance().getEvents();
            return HttpClient.getInstance().getPerson(id_list[0]);
        }

        protected void onPostExecute(PersonResult person_result) {
            UserData.getInstance().person = new Person(person_result.personID, person_result.associatedUsername, person_result.firstName, person_result.lastName,
                    person_result.gender, person_result.fatherID, person_result.motherID, person_result.spouseID);
//            toast_message.setText("User full name: " + person_result.firstName + " " + person_result.lastName);
            toast_message.cancel();
            main_activity = (MainActivity)getContext();
            assert main_activity != null;
            main_activity.switchToMapFragment();
        }

    }


}
