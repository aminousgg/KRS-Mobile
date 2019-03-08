package com.example.d2a.aplikasisqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tambahkrs(View view) {
        Intent pindah = new Intent(MainActivity.this, ProgramUtama.class);
        startActivity(pindah);
        finish();
    }
}
