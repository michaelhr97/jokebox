package es.jokerbox;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = String.valueOf(R.string.tagRegister);
    private FirebaseAuth auth;

    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.initialize();
    }

    private void initialize() {

        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        etConfirmPassword = findViewById(R.id.confirm_password);

        findViewById(R.id.register).setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
    }

    private boolean isFormDataCorrect(String email, String password, String confirmPassword) {
        if (TextUtils.isEmpty(email)) {
            etEmail.setError(getString(R.string.inputEmpty));
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError(getString(R.string.inputEmpty));
            return false;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            etConfirmPassword.setError(getString(R.string.inputEmpty));
            return false;
        }

        if (!password.equals(confirmPassword)) {
            etPassword.setError(getString(R.string.inputEmpty));
            etConfirmPassword.setError(getString(R.string.inputEmpty));
            return false;
        }

        return true;
    }

    private void createUserWithEmailAndPassword() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        if (!isFormDataCorrect(email, password, confirmPassword)) {
            return;
        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == (R.id.register)) {
            this.createUserWithEmailAndPassword();
        }
    }
}
