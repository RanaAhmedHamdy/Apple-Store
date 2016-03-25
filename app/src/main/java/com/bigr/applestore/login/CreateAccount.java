package com.bigr.applestore.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bigr.applestore.R;
import com.bigr.applestore.utils.Constants;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class CreateAccount extends AppCompatActivity {

    EditText name_edittext;
    EditText password_edittext;
    EditText email_edittext;
    Button createAccount;
    private Firebase mFirebaseRef;
    private String mUserName, mUserEmail, mPassword;
    private ProgressDialog mAuthProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        initialize();

        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);
    }

    private void initialize() {
        name_edittext = (EditText) findViewById(R.id.username_edittext);
        password_edittext = (EditText) findViewById(R.id.password_edittext);
        email_edittext = (EditText) findViewById(R.id.email_edittext);
        createAccount = (Button) findViewById(R.id.create_account_btn);

        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle(getResources().getString(R.string.progress_dialog_loading));
        mAuthProgressDialog.setMessage(getResources().getString(R.string.progress_dialog_creating_user_with_firebase));
        mAuthProgressDialog.setCancelable(false);
    }

    public void onCreateAccountPressed(View view) {
        mUserName = name_edittext.getText().toString();
        mUserEmail = email_edittext.getText().toString().toLowerCase();
        mPassword = password_edittext.getText().toString();

        /**
         * Check that email and user name are okay
         */
        boolean validEmail = isEmailValid(mUserEmail);
        boolean validUserName = isUserNameValid(mUserName);
        boolean validPassword = isPasswordValid(mPassword);
        if (!validEmail || !validUserName || !validPassword) return;

        /**
         * If everything was valid show the progress dialog to indicate that
         * account creation has started
         */
        mAuthProgressDialog.show();

        /**
         * Create new user with specified email and password
         */
        mFirebaseRef.createUser(mUserEmail, mPassword, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                /* Dismiss the progress dialog */
                mAuthProgressDialog.dismiss();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                mAuthProgressDialog.dismiss();
                /* Display the appropriate error message */
                if (firebaseError.getCode() == FirebaseError.EMAIL_TAKEN) {
                    email_edittext.setError(getString(R.string.error_email_taken));
                } else {
                    showErrorToast(firebaseError.getMessage());
                }

            }
        });


    }

    /**
     * Creates a new user in Firebase from the Java POJO
     */
    private void createUserInFirebaseHelper(final String encodedEmail) {
    }

    private boolean isEmailValid(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            email_edittext.setError(String.format(getString(R.string.error_invalid_email_not_valid),
                    email));
            return false;
        }
        return isGoodEmail;
    }

    private boolean isUserNameValid(String userName) {
        if (userName.equals("")) {
            name_edittext.setError(getResources().getString(R.string.error_cannot_be_empty));
            return false;
        }
        return true;
    }

    private boolean isPasswordValid(String password) {
        if (password.length() < 6) {
            password_edittext.setError(getResources().getString(R.string.error_invalid_password_not_valid));
            return false;
        }
        return true;
    }

    /**
     * Show error toast to users
     */
    private void showErrorToast(String message) {
        Toast.makeText(CreateAccount.this, message, Toast.LENGTH_LONG).show();
    }
}
