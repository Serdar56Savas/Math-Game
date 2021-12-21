package com.example.mathoyun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class cikarma extends AppCompatActivity {

    TextView skor;
    TextView life;
    TextView zaman;

    TextView soru;
    EditText cevap;
    Button kabul,digerSoru;
    Random random=new Random();
    int sayi1;
    int sayi2;
    int kullaniciCevap;
    int gercekCevap;
    int kullanıcıSkor=0;
    int userLife = 3;

    //saat için tanımlamalar
    CountDownTimer timer;
    private static final long START_TIMER_IN_MILIS = 60000;  //1000 = 1 saniye
    Boolean timerContinue;
    Long time_left_in_milis=START_TIMER_IN_MILIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun);

        skor=findViewById(R.id.textViewSkor);
        life=findViewById(R.id.textViewLife);
        zaman=findViewById(R.id.textViewSure);
        soru=findViewById(R.id.textViewSoru);
        cevap=findViewById(R.id.editTextCevap);
        kabul=findViewById(R.id.buttonKabul);
        digerSoru=findViewById(R.id.buttonDigersoru);

        oyunDevam();


        kabul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                kullaniciCevap = Integer.valueOf(cevap.getText().toString());

                pauseTimer();

                if (kullaniciCevap == gercekCevap)
                {
                    kullanıcıSkor=kullanıcıSkor+10;
                    skor.setText(""+kullanıcıSkor);
                    soru.setText("Tebrikler, Cevabınız doğru.");
                }
                else
                {
                    userLife=userLife-1;
                    life.setText(""+userLife);
                    soru.setText("Üzgünüm, Cevabınız yanlış.");

                }

            }
        });

        digerSoru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cevap.setText("");      //edit text butonu doğrudan temizlenir
                oyunDevam();             //diğer soruya basınca yeni sorunun gelmesi için çağrıldı
                resetTimer();

                if (userLife == 0)
                {
                    Toast.makeText(getApplicationContext(),"OYUN BİTTİ",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(cikarma.this,Final.class);
                    intent.putExtra("skor",kullanıcıSkor);
                    startActivity(intent);
                    finish();

                }
                else
                {
                    oyunDevam();

                }

            }
        });
    }
    public void oyunDevam()
    {
        sayi1=random.nextInt(5000);
        sayi2=random.nextInt(1000);

        gercekCevap=sayi1-sayi2;

        soru.setText(sayi1+ " - " + sayi2);
        startTimer();

    }

    public void startTimer()
    {
        timer = new CountDownTimer(time_left_in_milis,1000) {  //1000 yazılma sebebi azalışı belirlemek yani 1 saniye azalacak 2000 yazılırsa 2 saniye azalacak
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_in_milis = millisUntilFinished;
                updateText();

            }

            @Override
            public void onFinish() {
                timerContinue = false;
                pauseTimer();
                resetTimer();
                updateText();
                userLife=userLife-1;
                life.setText(""+userLife);
                soru.setText("Üzgünüm ,Süre doldu!");

            }
        }.start();

        timerContinue =true;
    }
    public void resetTimer()
    {
        time_left_in_milis = START_TIMER_IN_MILIS;
        updateText();
    }
    public void  updateText()
    {
        int second = (int) (time_left_in_milis/1000) % 60;  //long türü olduğu için int a çevirmek gerekli
        //String time_left=String.format(Locale.getDefault(),"%02d",second);
        zaman.setText("" + second);
    }
    public void pauseTimer()
    {
        timer.cancel();
        timerContinue = false;
    }
}