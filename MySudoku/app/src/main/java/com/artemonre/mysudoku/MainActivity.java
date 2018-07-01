package com.artemonre.mysudoku;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnOptions, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById (R.id.btnStart);
        MenuOnClickListener menuOnClickListener = new MenuOnClickListener ();
        btnStart.setOnClickListener(menuOnClickListener);
    }

    class MenuOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case (R.id.btnStart) :
                    Intent gameActivity = new Intent (MainActivity.this, GameActivity.class);
                    startActivity (gameActivity);
                    break;
                case (R.id.btnOptns) : break;
                case (R.id.btnAbout) : break;
            }
        }
    }

}
