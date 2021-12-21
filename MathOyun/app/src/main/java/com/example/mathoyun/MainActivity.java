package com.example.mathoyun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button toplama,cikarma,carpma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toplama=findViewById(R.id.buttonadd);
        cikarma=findViewById(R.id.buttonsub);
        carpma=findViewById(R.id.buttonmult);

        toplama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Oyun.class);
                startActivity(intent);
                finish();
            }
        });
        cikarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,cikarma.class);
                startActivity(intent);
                finish();
            }
        });
        carpma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,carpma.class);
                startActivity(intent);
                finish(); //kullanıcı geri tuşuna basınca önceki sayfaya geçmesin diye kullandık
            }
        });
    }
}