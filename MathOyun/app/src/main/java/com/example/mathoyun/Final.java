package com.example.mathoyun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Final extends AppCompatActivity {

    TextView sonuc;

    Button tekrar;
    Button cikis;
    int skor=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        MediaPlayer music=MediaPlayer.create(Final.this,R.raw.mymusic);
        music.start();

        sonuc=findViewById(R.id.textViewSkorSon);
        tekrar=findViewById(R.id.buttonTekrar);
        cikis=findViewById(R.id.buttonCikis);

        Intent intent=getIntent();
        skor=intent.getIntExtra("skor",0);
        String userscore=String.valueOf(skor);
        sonuc.setText("Skorunuz :"+userscore);

        tekrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Final.this,MainActivity.class);
                startActivity(intent);
                finish();             //kullanıcı geri tuşuna basınca önceki sayfaya geçmesin diye kullanıyoruz
            }
        });
        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();    //kullanıcı geri tuşuna basınca önceki sayfaya geçmesin diye kullandık

            }
        });
    }
}