package com.example.retrofit_aplikacja;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button buttonNastepne;
    RadioButton radioButtonA, radioButtonB, radioButtonC;
    RadioGroup radioGroupPytania;
    TextView textViewTresc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNastepne = findViewById(R.id.button);
        radioButtonA = findViewById(R.id.radioButton);
        radioButtonB = findViewById(R.id.radioButton2);
        radioButtonC = findViewById(R.id.radioButton3);
        textViewTresc = findViewById(R.id.textViewPytanie);
        radioGroupPytania = findViewById(R.id.radioGroupPytania);
    }
}