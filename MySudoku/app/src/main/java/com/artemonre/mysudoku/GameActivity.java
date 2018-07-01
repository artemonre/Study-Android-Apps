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

public class GameActivity extends AppCompatActivity {

    final int TABLE_ROW = 100;
    public static final String NUMBER =
            "com.artemonre.mysudoku.keyPad";

    Button btnRestart, btnMainMenu, btnTest;
    Game game = new Game(this);
    int widhtPixels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btnRestart = findViewById (R.id.btnRestart);
        btnTest = findViewById (R.id.btnTest);
        btnMainMenu = findViewById (R.id.btnMainMenu);
        SudokuOnClickListener sudokuOnClickListener = new SudokuOnClickListener ();
        btnRestart.setOnClickListener(sudokuOnClickListener);
        btnTest.setOnClickListener(sudokuOnClickListener);
        btnMainMenu.setOnClickListener(sudokuOnClickListener);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        widhtPixels = displayMetrics.widthPixels;

        LinearLayout.LayoutParams fieldParams = new LinearLayout.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, widhtPixels);
        TableLayout.LayoutParams linesParams = new TableLayout.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, 0);
        linesParams.weight = 1;
        TableRow.LayoutParams btnParamsMrgCommon = new TableRow.LayoutParams (0, ViewGroup.LayoutParams.MATCH_PARENT);
        TableRow.LayoutParams btnParamsMrgTop = new TableRow.LayoutParams (0, ViewGroup.LayoutParams.MATCH_PARENT);
        TableRow.LayoutParams btnParamsMrgRight = new TableRow.LayoutParams (0, ViewGroup.LayoutParams.MATCH_PARENT);
        TableRow.LayoutParams btnParamsMrgCorner = new TableRow.LayoutParams (0, ViewGroup.LayoutParams.MATCH_PARENT);
        btnParamsMrgCommon.weight = 1;
        btnParamsMrgCommon.setMargins (2, 2, 2, 2);
        btnParamsMrgTop.weight = 1;
        btnParamsMrgTop.setMargins (2, 6, 2, 2);
        btnParamsMrgRight.weight = 1;
        btnParamsMrgRight.setMargins (2, 2, 6, 2);
        btnParamsMrgCorner.weight = 1;
        btnParamsMrgCorner.setMargins (2, 6, 6, 2);

        game.setField ((TableLayout) findViewById (R.id.field), fieldParams);

        for (int i = 0; i < game.getLines().length; i++) {
            TableRow tableRow = new TableRow (this);
            tableRow.setId (TABLE_ROW + i);
            game.setLines (tableRow, i);
        }
        int color = 0;
        for (int i = 0; i < game.getLines().length; i++) {
            for (int j = 0; j < 9; j++) {
                Button button = new Button (this);
                button.setOnClickListener (sudokuOnClickListener);
                button.setId (j * 10 + i);
                if (color == 0) {
                    button.setBackgroundColor(ContextCompat.getColor (this, R.color.myColor1)/*0*/);
                    color++;
                }
                else {
                    button.setBackgroundColor(ContextCompat.getColor (this, R.color.myColor2));
                    color--;
                }
                if (i == 3 || i == 6)
                    game.setSpaces (button, btnParamsMrgTop, btnParamsMrgCorner, i);
                else
                    game.setSpaces (button, btnParamsMrgCommon, btnParamsMrgRight, i);
            }
            game.getField().addView (game.getLines()[i], linesParams);
        }
        game.createGameField ();
    }
    protected void endGame (Context context) {
        Log.d ("myLog", "You are the winner!");
        try {
            Toast toast = Toast.makeText(context,
                    "Победка!", Toast.LENGTH_SHORT);
            Log.d ("myLog", "Toast!");
            toast.show();
        } catch (NullPointerException e) {
            game.createGameField();
        }
    }
    class SudokuOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String id;
            int oldId = v.getId();
            if (oldId < 10)
                id = "0" + oldId;
            else
                id = "" + oldId;
            final String [] splitId = id.split ("");
            switch (v.getId()) {
                case (R.id.btnRestart) : game.createGameField(); break;
                case (R.id.btnTest) : game.createTestFieldAbstraction (); break;
                case (R.id.btnMainMenu) :
                    Intent maunActivity = new Intent (GameActivity.this, MainActivity.class);
                    startActivity (maunActivity);
                    break;
                default:
                    new AlertDialog.Builder(GameActivity.this)
                            .setItems(R.array.keyPad,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialoginterface,
                                                            int i) {
                                            game.setFieldAbstraction(i, Integer.parseInt(splitId[1]), Integer.parseInt(splitId[2]));
                                        }
                                    })
                            .show();
            }
        }
    }

}
