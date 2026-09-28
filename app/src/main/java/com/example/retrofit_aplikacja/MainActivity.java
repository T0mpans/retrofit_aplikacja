package com.example.retrofit_aplikacja;

import static android.view.View.INVISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button buttonNastepne;
    RadioButton radioButtonA, radioButtonB, radioButtonC;
    RadioGroup radioGroupPytania;
    TextView textViewTresc;
    List<Pytanie> pytaniaZInternetu;
    int index = 0;
    int ostatecznyWynik = 0;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/T0mpans/retrofit_pytania_matematyka/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        Call<List<Pytanie>> call = jsonPlaceHolder.getPytania();
        call.enqueue(
                new Callback<List<Pytanie>>() {
                    @Override
                    public void onResponse(Call<List<Pytanie>> call, Response<List<Pytanie>> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        pytaniaZInternetu = response.body();
                        wypiszPytanie(0);
                    }

                    @Override
                    public void onFailure(Call<List<Pytanie>> call, Throwable t) {

                    }
                }
        );
        buttonNastepne.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (index < pytaniaZInternetu.size()) {

                            if (radioButtonA.isChecked()) {
                                if (pytaniaZInternetu.get(index).getPoprawna() == 0) {
                                    Toast.makeText(MainActivity.this, "POPRAWNA", Toast.LENGTH_SHORT).show();
                                    ostatecznyWynik++;
                                } else {
                                    Toast.makeText(MainActivity.this, "NIE POPRAWNA", Toast.LENGTH_SHORT).show();
                                }
                            } else if (radioButtonB.isChecked()) {
                                if (pytaniaZInternetu.get(index).getPoprawna() == 1) {
                                    Toast.makeText(MainActivity.this, "POPRAWNA", Toast.LENGTH_SHORT).show();
                                    ostatecznyWynik++;
                                } else {
                                    Toast.makeText(MainActivity.this, "NIE POPRAWNA", Toast.LENGTH_SHORT).show();
                                }
                            } else if (radioButtonC.isChecked()) {
                                if (pytaniaZInternetu.get(index).getPoprawna() == 2) {
                                    Toast.makeText(MainActivity.this, "POPRAWNA", Toast.LENGTH_SHORT).show();
                                    ostatecznyWynik++;
                                } else {
                                    Toast.makeText(MainActivity.this, "NIE POPRAWNA", Toast.LENGTH_SHORT).show();
                                }
                            }
                            radioGroupPytania.clearCheck();
                            boolean ekranKoncowy = false;
                            if (index < pytaniaZInternetu.size() - 1) {
                                index++;
                                wypiszPytanie(index);
                            } else {
                                ekranKoncowy = true;
                            }
                            if (ekranKoncowy) {
                                buttonNastepne.setVisibility(INVISIBLE);
                                radioButtonA.setVisibility(INVISIBLE);
                                radioButtonB.setVisibility(INVISIBLE);
                                radioButtonC.setVisibility(INVISIBLE);
                                textViewTresc.setText("Wynik: " + ostatecznyWynik);
                            }
                        }
                    }
                }
        );
    }

    private void wypiszPytanie(int nrPytania) {
        textViewTresc.setText(pytaniaZInternetu.get(nrPytania).getTrescPytania());
        radioButtonA.setText(pytaniaZInternetu.get(nrPytania).getOdpA());
        radioButtonB.setText(pytaniaZInternetu.get(nrPytania).getOdpB());
        radioButtonC.setText(pytaniaZInternetu.get(nrPytania).getOdpC());
    }
}