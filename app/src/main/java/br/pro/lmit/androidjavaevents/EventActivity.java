package br.pro.lmit.androidjavaevents;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Navigation.findNavController(this, R.id.nav_host);
    }
}
