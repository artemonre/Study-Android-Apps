package com.artemonre.mysimplecalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static String screenText = "0";
    static TextView screen;
    static TextView resultScreen;
    static TextView memoryScreen;
    Switch switch1;
    SeekBar seekBar;
    Button btnC, btnMSave, btnMLoad, btnSvn, btnEght, btnNine, btnFour, btnFive, btnSix, btnOne, btnTwo, btnThree,
            btnPlusMinus, btnZero, btnDot, btnPls, btnBckSpc, btnFrct, btnLgrfm, btnPrcnt, btnMns, btnRoot, btnMlt, btnDgr, btnDvsn, btnEqls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = findViewById (R.id.screen);
        resultScreen = findViewById (R.id.resultScreen);
        memoryScreen = findViewById (R.id.memoryScreen);
        screen.setText (screenText);
        switch1 = findViewById (R.id.switch1);
        seekBar  = findViewById (R.id.seekBar);

        btnC = findViewById (R.id.btnC);
        btnMSave = findViewById (R.id.btnMSave);
        btnMLoad = findViewById (R.id.btnMLoad);
        btnSvn = findViewById (R.id.btnSvn);
        btnEght = findViewById (R.id.btnEght);
        btnNine = findViewById (R.id.btnNine);
        btnFour = findViewById (R.id.btnFour);
        btnFive = findViewById (R.id.btnFive);
        btnSix = findViewById (R.id.btnSix);
        btnOne = findViewById (R.id.btnOne);
        btnTwo = findViewById (R.id.btnTwo);
        btnThree = findViewById (R.id.btnThree);
        btnPlusMinus = findViewById (R.id.btnPlusMinus);
        btnZero = findViewById (R.id.btnZero);
        btnDot = findViewById (R.id.btnDot);
        btnPls = findViewById (R.id.btnPls);
        btnBckSpc = findViewById (R.id.btnBckSpc);
        btnFrct = findViewById (R.id.btnFrct);
        btnLgrfm = findViewById (R.id.btnLgrfm);
        btnPrcnt = findViewById (R.id.btnPrcnt);
        btnMns = findViewById (R.id.btnMns);
        btnRoot = findViewById (R.id.btnRoot);
        btnRoot = findViewById (R.id.btnRoot);
        btnMlt = findViewById (R.id.btnMlt);
        btnDgr = findViewById (R.id.btnDgr);
        btnDvsn = findViewById (R.id.btnDvsn);
        btnEqls = findViewById (R.id.btnEqls);

        View.OnClickListener onClickListener = new MyOnClickListener ();

        btnC.setOnClickListener (onClickListener);
        btnMSave.setOnClickListener (onClickListener);
        btnMLoad.setOnClickListener (onClickListener);
        btnSvn.setOnClickListener (onClickListener);
        btnEght.setOnClickListener (onClickListener);
        btnNine.setOnClickListener (onClickListener);
        btnFour.setOnClickListener (onClickListener);
        btnFive.setOnClickListener (onClickListener);
        btnSix.setOnClickListener (onClickListener);
        btnOne.setOnClickListener (onClickListener);
        btnTwo.setOnClickListener (onClickListener);
        btnThree.setOnClickListener (onClickListener);
        btnPlusMinus.setOnClickListener (onClickListener);
        btnZero.setOnClickListener (onClickListener);
        btnDot.setOnClickListener (onClickListener);
        btnPls.setOnClickListener (onClickListener);
        btnFrct.setOnClickListener (onClickListener);
        btnLgrfm.setOnClickListener (onClickListener);
        btnBckSpc.setOnClickListener (onClickListener);
        btnPrcnt.setOnClickListener (onClickListener);
        btnMns.setOnClickListener (onClickListener);
        btnRoot.setOnClickListener (onClickListener);
        btnMlt.setOnClickListener (onClickListener);
        btnDgr.setOnClickListener (onClickListener);
        btnDvsn.setOnClickListener (onClickListener);
        btnEqls.setOnClickListener (onClickListener);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ExpressionMember.setAccuracy ((byte) progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

    }

    public static void setScreenText (String screenText) {
        screen.setText (screenText);
    }
    public static void setResultScreenText (String resultScreenText) {
        resultScreen.setText (resultScreenText);
    }

}

