package com.example.ecohomepro;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class contactNumber extends AppCompatActivity implements View.OnClickListener {
    private PinView pinView;
    private Button btn;
    private EditText name, mobile;
    ConstraintLayout first, second;
    TextView tv;
    FirebaseAuth auth;
    private String VerificationId;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        auth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_contact_number);
        btn = findViewById(R.id.button2);
        first = findViewById(R.id.first);
        name = findViewById(R.id.nameED);
        mobile = findViewById(R.id.mobileED);
        second = findViewById(R.id.second);
        second.setVisibility(View.GONE);
        tv = findViewById(R.id.code);
        first.setVisibility(View.VISIBLE);
        pinView = findViewById(R.id.pinView7);
        btn.setOnClickListener(this);
        db = new DataBase(getApplicationContext()).getReadableDatabase();


    }


    @Override
    public void onClick(View v) {
        if (btn.getText().equals("Send")) {
            String Name = name.getText().toString();
            String Mobile = mobile.getText().toString();
            if (!TextUtils.isEmpty(Name) && !TextUtils.isEmpty(Mobile)) {
                if (Mobile.length() < 10) {
                    Toast.makeText(getApplicationContext(), "Invalid phone number", Toast.LENGTH_SHORT).show();
                } else {
                    sendVerificationCode(Mobile);
                    btn.setText("Verify");
                    first.setVisibility(View.GONE);
                    second.setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(getApplicationContext(), "please enter your details", Toast.LENGTH_SHORT).show();
            }
        } else if (btn.getText().toString().equals("Verify")) {
            String code = pinView.getText().toString();
            verifyCode(code);
        }
    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(VerificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = task.getResult().getUser();

                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void sendVerificationCode(String number) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number, 60, TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD,
                mCallback

        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            verifyCode(code);
        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            VerificationId = s;
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Log.d("sad", "onVerificationFailed: " + e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    protected void onStart() {

        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {


            Cursor cursor = db.query("admins", null, null, null, null, null, null);
            cursor.moveToFirst();
            Boolean found = false;

            while (cursor.isAfterLast() == false) {

                if (mobile.getText().toString().equals(cursor.getString(cursor.getColumnIndex("phone")))) {
                    found = true;
                    Intent i = new Intent(getApplicationContext(), Admin_home.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }


                cursor.moveToNext();
            }
            if (!found) {
                Intent i = new Intent(getApplicationContext(), Admin_home.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }


        }
    }
}
