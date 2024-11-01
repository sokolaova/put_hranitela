package com.example.put_hranitelya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editTextTextEmailAddress, editTextTextPassword;
    Button registration_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registration), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        registration_button = findViewById(R.id.registration_button);

        DBConnect dbConnect = new DBConnect(this);

        registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Получаем значение из EditText
                String email = editTextTextEmailAddress.getText().toString();
                String password = editTextTextPassword.getText().toString();

                // Записываем значение в базу данных
                Users newUser = new Users(email, password);
                dbConnect.addUser(newUser);
                Intent intent = new Intent(MainActivity.this, viewProfile.class);
                startActivity(intent);
            }
        });

    }

    public void goToViewProfile(View v){

        Intent intent = new Intent(this, viewProfile.class);
        startActivity(intent);
    }
    public void goToAutorization(View v){
        Intent intent = new Intent(this, authorization.class);
        startActivity(intent);
    }
}