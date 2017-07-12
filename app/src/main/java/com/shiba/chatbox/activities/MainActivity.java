package com.shiba.chatbox.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.shiba.chatbox.R;
import com.shiba.chatbox.views.Chatbox;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Chatbox chatbox = new Chatbox(this, (RelativeLayout) findViewById(R.id.chatbox_layout), "Eu");
    }
}
