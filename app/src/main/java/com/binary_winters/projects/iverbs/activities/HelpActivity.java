package com.binary_winters.projects.iverbs.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.binary_winters.projects.iverbs.R;

public class HelpActivity extends AppCompatActivity {

    private Button imageButtonAcercaDeIverbs;
    private Button imageButtonComoJugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        imageButtonAcercaDeIverbs = (Button) findViewById(R.id.imageButtonAcercaDeIverbs);
        imageButtonComoJugar = (Button) findViewById(R.id.imageButtonComoJugar);

        imageButtonAcercaDeIverbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HelpActivity.this, SobreIverbsActivity.class);
                startActivity(intent);
            }
        });

        imageButtonComoJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HelpActivity.this, ComoJugarActivity.class);
                startActivity(intent);
            }
        });

    }
}
