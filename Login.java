package com.blessysoftware.appinstallation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blessysoftware.appinstallation.R;
import com.blessysoftware.appinstallation.fragment.RegisterFragment;
import com.blessysoftware.appinstallation.utility.Session;
import com.blessysoftware.appinstallation.welcome.WelcomeActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {
    FrameLayout framelayout;
    Button btn_login;
    TextView tv_register;
    TextInputEditText ed_useremail, ed_password;
    public static Animation shakeAnimation;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    TextInputLayout tv_passlayout, email_layout;

    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed_useremail = findViewById(R.id.ed_useremail);
        email_layout = findViewById(R.id.email_layout);
        tv_passlayout = findViewById(R.id.tv_passlayout);
        ed_password = findViewById(R.id.ed_password);
        framelayout = findViewById(R.id.framelayout);
        tv_register = findViewById(R.id.tv_register);
        btn_login = findViewById(R.id.btn_login);
        shakeAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ed_useremail.getText().toString();
                String password = ed_password.getText().toString();
                ed_useremail.clearFocus();
                ed_password.clearFocus();

                if (email.equals("")) {
                    vibrate(200);
                    email_layout.startAnimation(shakeAnimation);

                    Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
                } else if (!email.trim().matches(emailPattern)) {
                    vibrate(200);
                    email_layout.startAnimation(shakeAnimation);
                    Toast.makeText(getApplicationContext(), "Enter valid email", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    vibrate(200);
                    tv_passlayout.startAnimation(shakeAnimation);

                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    vibrate(200);
                    tv_passlayout.startAnimation(shakeAnimation);

                    Toast.makeText(getApplicationContext(), "Below six digits now allow\nEnter above six digits", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(getApplicationContext(), Dashboared.class);
                    startActivity(i);
                }

            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new RegisterFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, fragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

    public void vibrate(int duration) {
        Vibrator vibs = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vibs.vibrate(duration);
    }
}