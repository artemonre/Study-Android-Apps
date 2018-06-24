package com.artemonre.mysimplecalc;

import android.util.Log;

public class Expression {

    private MyCalculator myCalculator = new MyCalculator ();
    private ExpressionMember a = new ExpressionMember ();
    private ExpressionMember b = new ExpressionMember ();
    private ExpressionMember c = new ExpressionMember ();
    private ExpressionMember memory = new ExpressionMember ();
    String arithmeticSign = "";

    public void setExpression (String number) {
        if (arithmeticSign.equals("")) {
            if (!c.getExpMember().isEmpty())
                c.setExpMember ("clear", "void");
            a.setExpMember ("add", number);
            updateScreen(a);
        }
        else {
            if (!c.getExpMember().isEmpty()) {
                createAfromC ();
            }
            b.setExpMember ("add", number);
            Log.d ("myLog", "setExpression b = " + b.getExpMember().toString());
            updateScreen(b);
        }
    }

    public void addZero () {
        if (arithmeticSign.equals("")) {
            if (!a.getExpMember ().get (0).equals("0") || a.getExpMember().contains ("."))
                a.setExpMember ("add", "0");
            updateScreen(a);
        }
        else {
            if (!b.getExpMember ().get (0).equals("0") || b.getExpMember().contains ("."))
                b.setExpMember ("add", "0");
            updateScreen(b);
        }
    }

    public void addDot () {
        if (arithmeticSign.equals("")) {
            if (a.getExpMember().isEmpty())
                a.setExpMember ("add", "0");
            if (!a.getExpMember().contains(".") && !a.getExpMember ().contains("L") &&
                    !a.getExpMember ().contains("^") && !a.getExpMember ().contains("r"))
                a.setExpMember ("add", ".");

            updateScreen(a);
        }
        else {
            if (b.getExpMember().isEmpty())
                b.setExpMember ("add", "0");
            if (!b.getExpMember().contains (".") && !b.getExpMember ().contains("L") &&
                    !b.getExpMember ().contains("^") && !b.getExpMember ().contains("r"))
                b.setExpMember("add", ".");

            updateScreen(b);
        }
    }

    public void addSpecialSign (String sign) {
        if (arithmeticSign.equals("")) {
            if (!c.getExpMember().isEmpty()) {
                createAfromC ();
            }
            else {
                if (a.getExpMember().isEmpty())
                    return;
            }
            a.setExpMember ("add", sign);
            updateScreen(a);
        }
        else {
            if (!c.getExpMember().isEmpty()) {
                createAfromC ();
            }
            if (b.getExpMember().isEmpty())
                return;
            b.setExpMember ("add", sign);
            updateScreen(b);
        }
    }

    public void setArithmeticSign (String sign) {
        arithmeticSign = sign;
        MainActivity.screen.setText(arithmeticSign);
    }

    public void addArithmeticSign (String sign) {
        Log.d ("myLog", "addSign");
        if (!c.getExpMember().isEmpty())
            createAfromC();
        Log.d ("myLog", "in addSign a = " + a.getExpMember().toString());
        Log.d ("myLog", "in addSign c = " + c.getExpMember().toString());
        takeResult ();
        arithmeticSign = sign;
        MainActivity.screen.setText(arithmeticSign);
        MainActivity.resultScreen.setText(c.getExpMember().toString());
    }

    public void plusMinus () {
        if (arithmeticSign.equals("")) {
            a.changePositive();
            updateScreen(a);
        }
        else {
            b.changePositive();
            updateScreen(b);
        }
    }
    
    public void arithmeticEqual () {
        if (!arithmeticSign.equals("")) {
            takeResult ();
            MainActivity.screen.setText(myCalculator.getResult ().toString());
            MainActivity.resultScreen.setText ("");
            arithmeticSign = "";
        }
        else if (a.getExpMember().contains ("^") || a.getExpMember().contains ("r") || a.getExpMember().contains ("%") || a.getExpMember().contains ("L")) {
            Log.d ("myLog", "precalculate");
            String aN = myCalculator.preCalculate (a.getExpMember().toString());
            Log.d ("myLog", aN);
            a.setExpMember ("clear");
            Log.d ("myLog", a.getLenght() + "");
            try {
                for (int i = 0; i < aN.length(); i++) {
                    String temp = "" + aN.charAt(i);
                    if (temp.equalsIgnoreCase (","))
                        temp = ".";
                    a.setExpMember("add", temp);
                    Log.d("myLog", temp);
                }
            } catch (Exception e) {
                Log.d ("myLog", "error message");
                Log.d ("myLog", e.getClass().getSimpleName());
            }
            Log.d ("myLog", a.getExpMember().toString());
            Log.d ("myLog", "parapapa");
            MainActivity.screen.setText(a.getExpMember().toString());
            MainActivity.resultScreen.setText ("");
            arithmeticSign = "";
        }
    }

    private void createAfromC () {
        Log.d ("myLog", "AfromC");
        a.reSetExpMember (c.getExpMember());
        a.setLenght (c.getLenght());
        if (c.isPositive() != a.isPositive())
            a.changePositive();
        c.setExpMember ("clear");
        Log.d ("myLog", "in AfromC a = " + a.getExpMember().toString());
        Log.d ("myLog", "in AfromC c = " + c.getExpMember().toString());
    }

    public void toClear () {
        MainActivity.setScreenText("0");
        MainActivity.setResultScreenText ("");
        a.setExpMember ("clear");
        b.setExpMember ("clear");
        arithmeticSign = "";
        if (!a.isPositive())
            a.changePositive();
        if (!b.isPositive())
            b.changePositive();
    }

    public void backSpace () {
        if ((MainActivity.screen.getText ().equals (a.getExpMember().toString()) ||
                MainActivity.screen.getText ().equals ("-" + a.getExpMember().toString())) && !a.getExpMember().isEmpty()) {
            a.setExpMember("remove", "lenght");
            updateScreen (a);
        }
        else if (MainActivity.screen.getText ().equals (arithmeticSign)) {
            arithmeticSign = "";
            MainActivity.screen.setText (arithmeticSign);
        }
        else if ((MainActivity.screen.getText ().equals (b.getExpMember().toString()) ||
                MainActivity.screen.getText ().equals ("-" + b.getExpMember().toString())) && !b.getExpMember().isEmpty()) {
            b.setExpMember("remove", "lenght");
            updateScreen (b);
        }
    }

    public void getMemory() {
        if (arithmeticSign.equals("")) {
            a.reSetExpMember (memory.getExpMember());
            a.setLenght (memory.getLenght());
            if (a.isPositive() != memory.isPositive())
                a.changePositive();
            updateScreen(a);
        }
        else {
            b.reSetExpMember (memory.getExpMember());
            b.setLenght (memory.getLenght());
            if (b.isPositive() != memory.isPositive())
                b.changePositive();
            updateScreen(b);
        }
    }

    public void setMemory() {
        memory.setExpMember ("clear");
        memory.reSetExpMember (c.getExpMember());
        memory.setLenght (c.getLenght());
        if (c.isPositive() != memory.isPositive())
            memory.changePositive();
        c.setExpMember ("clear");
        MainActivity.screen.setText ("");
        MainActivity.memoryScreen.setText ("Memory: " + memory.getExpMember().toString());
    }

    private void updateScreen (ExpressionMember expMember) {
        if (!expMember.isPositive())
            MainActivity.setScreenText ("-" + expMember.getExpMember().toString());
        else
            MainActivity.setScreenText (expMember.getExpMember().toString());
    }

    private void takeResult () {
        String aN = a.getExpMember().toString();
        String bN = b.getExpMember().toString();
        Log.d ("myLog", "takeResult aN = " + aN);
        Log.d ("myLog", "takeResult bN = " + bN);

        if (!a.isPositive())
            aN = "-" + aN;
        if (!b.isPositive())
            bN = "-" + bN;

        if (aN.contains ("^") || aN.contains ("r") || aN.contains ("%") || aN.contains ("L")) {
            Log.d ("myLog", "precalculate a");
            aN = myCalculator.preCalculate(aN);
        }
        if (bN.contains ("^") || bN.contains ("r") || bN.contains ("%") || bN.contains ("L")) {
            if (bN.contains ("%") && bN.split ("%").length < 2) {
                bN = aN.concat ("%").concat (bN.split ("%") [0]);
            }
            Log.d("myLog", "precalculate b");
            Log.d("myLog", "bN = " + bN);
            bN = myCalculator.preCalculate(bN);
            Log.d ("myLog", "bN = " + bN);
        }

        try {
            Log.d ("myLog", aN + arithmeticSign + bN + " =");
            myCalculator.calculate(aN, bN, arithmeticSign);
            a.setExpMember ("clear", "void");
            b.setExpMember ("clear", "void");
            c.reSetExpMember (myCalculator.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toFraction () {
        Log.d ("myLog", "to Fraction");
        int numerator, denominator;
        String strDenom = "1";
        String decimal = MainActivity.screen.getText ().toString();
        String [] decArray = decimal.split ("\\.");
        for (int i = 0; i < decArray[1].length(); i++) {
            strDenom = strDenom.concat ("0");
        }
        numerator = (int) (Double.parseDouble (decimal) * Integer.parseInt (strDenom));
        denominator = Integer.parseInt (strDenom);

        long divisioner = numerator;
        while (true) {
            if (numerator % divisioner == 0 && denominator % divisioner == 0) {
                String temp = numerator/divisioner + "/" + denominator/divisioner + "";
                if (!c.getExpMember().isEmpty())
                    c.setExpMember ("clear");
                for (int i = 0; i < temp.length(); i++) {
                    c.setExpMember ("add", temp.charAt (i) + "");
                }
                MainActivity.screen.setText (c.getExpMember().toString());
                break;
            }
            else
                divisioner--;
        }
    }

}
