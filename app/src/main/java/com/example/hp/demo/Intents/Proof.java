package com.example.hp.demo.Intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.hp.demo.Adapters.EventsAdapter;
import com.example.hp.demo.R;
import com.squareup.picasso.Picasso;

public class Proof extends AppCompatActivity {

    ImageView image;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proof);

        image=findViewById(R.id.proofImage);
        toolbar=findViewById(R.id.tool);
        setSupportActionBar(toolbar);

        Intent intent=getIntent();
        String path=intent.getStringExtra(EventsAdapter.Proof_Url);

        Picasso.get().load(new String(path)).into(image);

    }
}
