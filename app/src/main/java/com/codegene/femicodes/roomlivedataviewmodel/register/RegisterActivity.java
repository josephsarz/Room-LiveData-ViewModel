package com.codegene.femicodes.roomlivedataviewmodel.register;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codegene.femicodes.roomlivedataviewmodel.R;
import com.codegene.femicodes.roomlivedataviewmodel.db.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEmailET;
    private EditText mPasswordET;
    private Button mRegisterBTN;

    RegisterViewModel mRegisterViewModel;

    public static Intent getStartedIntent(Context context){
        return new Intent(context, RegisterActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmailET = findViewById(R.id.ET_email);
        mPasswordET = findViewById(R.id.ET_password);
        mRegisterBTN = findViewById(R.id.BTN_register);

        mRegisterViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);

        mRegisterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mEmailET.getText().toString();
                String password = mPasswordET.getText().toString();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
                    mRegisterViewModel.addUser(new User(email, password));
                    finish();
                }else{
                    Toast.makeText(RegisterActivity.this, "some fields are empty", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
