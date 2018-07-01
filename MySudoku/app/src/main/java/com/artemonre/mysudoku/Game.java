package com.artemonre.mysudoku;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Set;

public class Game {

    private TableLayout field;
    private boolean win = false;
    private int filling = 0;

    private Context context;
    private Matrix<Integer> fieldAbstraction;
    private final TableRow[] lines = new TableRow[9];
    private final Button[] spaces = new Button[81];
    private final int FILLING_LINES = 1;
    private final int FILLING_COLUMNS = 2;
    private final int FILLING_AREAS = 3;

    private int btnCount = 0;
    private int marginCounter = 1;

    private final String easyPuzzle =
            "360000000004230800000004200" +
            "070460003820000014500013020" +
            "001900000007048300000000045";
    private final String mediumPuzzle =
            "650000070000506000014000005" +
            "007009000002314700000700800" +
            "500000630000201000030000097";
    private final String hardPuzzle =
            "009000000080605020501078000" +
            "000000700706040102004000000" +
            "000720903090301080000000600";
    private final String testPuzzle =
            "123456789456789123789123456" +
            "234567891567891234891234567" +
            "345678912678912345912345678";

    public Game (Context context) {
        this.context = context;
    }
    public void createGameField () {
        fieldAbstraction = new Matrix(Integer.class, 9);
        filling = 0;
        Log.d ("myLog", "CreateGameField");
        String [] newField = easyPuzzle.split ("");
        for (int i = 1; i < newField.length; i++) {
            if (!newField [i].equals ("0")) {
                fieldAbstraction.addElement(Integer.parseInt(newField[i]), i - 1);
                spaces[i - 1].setText(newField[i]);
                filling++;
                spaces [i-1].setOnClickListener (null);
                Log.d ("myLog", "onClickListener deleted");
                spaces [i-1].setTextSize (16);
                Log.d ("myLog", "textSize = 16");
                spaces [i-1].setTextColor (ContextCompat.getColor (context, android.R.color.holo_red_dark));
                Log.d ("myLog", "color = red");
            }
            else {
                spaces[i - 1].setText("");
            }
        }
    }

    public void setField (TableLayout field, LinearLayout.LayoutParams fieldParams) {
        field.setLayoutParams (fieldParams);
        this.field = field;
    }
    public TableLayout getField() {
        return field;
    }
    public void setFieldAbstraction (int number, int column, int line) {
        if (number == 0) {
            if (fieldAbstraction.getElement (column, line) == null)
                return;
            else {
                fieldAbstraction.removeElement(column, line);
                spaces[fieldAbstraction.getIndex(column, line)].setText("");
                filling--;
            }
        }
        else {
            if (fieldAbstraction.getElement (column, line) != null) {
                fieldAbstraction.addElement(number, column, line);
                spaces[fieldAbstraction.getIndex(column, line)].setText("" + fieldAbstraction.getElement(column, line));
            }
            else {
                fieldAbstraction.addElement(number, column, line);
                spaces[fieldAbstraction.getIndex(column, line)].setText("" + fieldAbstraction.getElement(column, line));
                filling++;
            }
        }
        isWin ();
    }

    public Matrix<Integer> getFieldAbstraction() {
        return fieldAbstraction;
    }

    public void createTestFieldAbstraction() {
        fieldAbstraction = new Matrix(Integer.class, 9);
        Log.d ("myLog", "CreateGameField");
        String [] newField = testPuzzle.split ("");
        for (int i = 1; i < newField.length; i++) {
            if (!newField[i].equals("0")) {
                fieldAbstraction.addElement(Integer.parseInt(newField[i]), i - 1);
                spaces[i - 1].setText(newField[i]);
                filling = 81;
            }
        }
        isWin ();
    }

    public void setLines (TableRow line, int i) {
        lines [i] = line;
    }
    public TableRow[] getLines() {
        return lines;
    }
    public void setSpaces (Button space, TableRow.LayoutParams buttonParamsCommon, TableRow.LayoutParams buttonParamsSpecial, int i) {
        spaces [btnCount] = space;
        if (marginCounter == 3 || marginCounter == 6) {
            lines[i].addView(spaces[btnCount], buttonParamsSpecial);
            btnCount++;
        }
        else {
            lines[i].addView(spaces[btnCount], buttonParamsCommon);
            btnCount++;
        }
        ++marginCounter;
        if (marginCounter == 10)
            marginCounter = 1;
    }
    public Button [] getSpaces() {
        return spaces;
    }
    private void isWin () {
        Log.d("myLog", "isWin");
        if (filling == 81) {
            Log.d("myLog", "81 fool");
            if (isFillingRight(FILLING_LINES)) {
                Log.d("myLog", "lines");
                if (isFillingRight(FILLING_COLUMNS)) {
                    Log.d("myLog", "columns");
                    if (isFillingRight(FILLING_AREAS)) {
                        Log.d("myLog", "areas");
                        win = true;
                    }
                }
            }
        }
        if (win) {
            Log.d("myLog", "Yay! Win");
            GameActivity gameActivity = new GameActivity ();
            gameActivity.endGame(context);
            win = false;
        }
    }
    private boolean isFillingRight (int what) {
        switch (what) {
            case (FILLING_LINES) :
                for (int i = 0; i < 9; i++) {
                    Set <Integer> lineValues = fieldAbstraction.getLineValues (i);
                    if (lineValues.size () != 9)
                        return false;
                }
                return true;
            case (FILLING_COLUMNS) :
                for (int i = 0; i < 9; i++) {
                    Set <Integer> columnValues = fieldAbstraction.getColumnValues (i);
                    if (columnValues.size () != 9)
                        return false;
                }
                return true;
            case (FILLING_AREAS) :
                int c = 0;
                int l = 0;
                for (; l < 9;) {
                    for (; c < 9;) {
                        Set <Integer> areaValues = fieldAbstraction.getAreaValues (c, c + 2, l, l + 2);
                        if (areaValues.size () != 9)
                            return false;
                        c += 3;
                    }
                    l += 3;
                }
                return true;
            default: return false;
        }
    }
}
