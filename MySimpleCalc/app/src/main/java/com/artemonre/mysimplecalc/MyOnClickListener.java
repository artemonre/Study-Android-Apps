package com.artemonre.mysimplecalc;

import android.util.Log;
import android.view.View;

public class MyOnClickListener implements View.OnClickListener {

    private MainActivity mainActivity = new MainActivity ();
    private MyCalculator myCalculator = new MyCalculator ();
    private ExpressionMember aM = new ExpressionMember ();
    private ExpressionMember bM = new ExpressionMember ();
    private ExpressionMember cM = new ExpressionMember ();
    private ExpressionMember memory = new ExpressionMember ();
    String arithmeticSygn = "";

    @Override
    public void onClick(View v) {

        try {

            switch (v.getId()) {

                case (R.id.btnC):
                    mainActivity.screen.setText("0");
                    mainActivity.resultScreen.setText ("");
                    aM.setExpMember ("clear", "void");
                    bM.setExpMember ("clear", "void");
                    arithmeticSygn = "";
                    if (!aM.isPositive())
                        aM.changePositive();
                    if (!bM.isPositive())
                        bM.changePositive();
                    break;
                case (R.id.btnMSave): setMemory (); break;
                case (R.id.btnMLoad): getMemory (); break;
                case (R.id.btnSvn): setExpression ("7"); break;
                case (R.id.btnEght): setExpression ("8"); break;
                case (R.id.btnNine): setExpression ("9"); break;
                case (R.id.btnFour): setExpression ("4"); break;
                case (R.id.btnFive): setExpression ("5"); break;
                case (R.id.btnSix): setExpression ("6"); break;
                case (R.id.btnOne): setExpression ("1"); break;
                case (R.id.btnTwo): setExpression ("2"); break;
                case (R.id.btnThree): setExpression ("3"); break;
                case (R.id.btnPlusMinus):
                    if (arithmeticSygn.equals("")) {
                        aM.changePositive();
                        updateScreen(aM);
                    }
                    else {
                        bM.changePositive();
                        updateScreen(bM);
                    }
                    break;

                case (R.id.btnZero):
                    if (arithmeticSygn.equals("")) {
                        if (!aM.getExpMember ().get (0).equals("0"))
                            aM.setExpMember ("add", "0");
                        updateScreen(aM);
                    }
                    else {
                        if (!bM.getExpMember ().get (0).equals("0"))
                            bM.setExpMember ("add", "0");
                        updateScreen(bM);
                    }
                    break;

                case (R.id.btnDot):
                    if (arithmeticSygn.equals("")) {
                        if (aM.getExpMember().isEmpty())
                            aM.setExpMember ("add", "0");
                        if (!aM.getExpMember().contains(".") )
                           aM.setExpMember ("add", ".");

                        updateScreen(aM);
                    }
                    else {
                        if (bM.getExpMember().isEmpty())
                            bM.setExpMember ("add", "0");
                        if (!bM.getExpMember().contains ("."))
                            bM.setExpMember("add", ".");

                        updateScreen(bM);
                    }
                    break;

                case (R.id.btnPls):
                    if (arithmeticSygn.equals(""))
                        setarithmeticSygn ("+");
                    else
                        addAryphmeticSygn ("+");
                    break;

                case (R.id.btnBckSpc):
                    if ((mainActivity.screen.getText ().equals (aM.getExpMember().toString()) ||
                            mainActivity.screen.getText ().equals ("-" + aM.getExpMember().toString())) && !aM.getExpMember().isEmpty()) {
                        aM.setExpMember("remove", "lenght");
                        updateScreen (aM);
                    }
                    else if (mainActivity.screen.getText ().equals (arithmeticSygn)) {
                        arithmeticSygn = "";
                        mainActivity.screen.setText (arithmeticSygn);
                    }
                    else if ((mainActivity.screen.getText ().equals (bM.getExpMember().toString()) ||
                            mainActivity.screen.getText ().equals ("-" + bM.getExpMember().toString())) && !bM.getExpMember().isEmpty()) {
                        bM.setExpMember("remove", "lenght");
                        updateScreen (bM);
                    }
                    break;

                case (R.id.btnPrcnt): setExpression ("%"); break;
                case (R.id.btnMns):
                    if (arithmeticSygn.equals(""))
                        setarithmeticSygn ("-");
                    else
                        addAryphmeticSygn ("-");
                    break;

                case (R.id.btnRoot): setExpression ("r"); break;
                case (R.id.btnMlt):
                    if (arithmeticSygn.equals(""))
                        setarithmeticSygn ("*");
                    else
                        addAryphmeticSygn ("*");
                    break;

                case (R.id.btnDgr):setExpression ("^"); break;

                case (R.id.btnDvsn):
                    if (arithmeticSygn.equals(""))
                        setarithmeticSygn ("/");
                    else
                        addAryphmeticSygn ("/");
                    break;

                case (R.id.btnEqls):
                    if (!arithmeticSygn.equals("")) {
                        takeResult (aM, bM, arithmeticSygn);
                        mainActivity.screen.setText(myCalculator.getResult ().toString());
                        mainActivity.resultScreen.setText ("");
                        arithmeticSygn = "";
                    }
                    else if (aM.getExpMember().contains ("^") || aM.getExpMember().contains ("r") || aM.getExpMember().contains ("%")) {
                        Log.d ("myLog", "precalculate");
                        String aN = myCalculator.preCalculate (aM.getExpMember().toString());
                        Log.d ("myLog", aN);
                        aM.setExpMember ("clear");
                        Log.d ("myLog", aM.getLenght() + "");
                        try {
                            for (int i = 0; i < aN.length(); i++) {
                                String temp = "" + aN.charAt(i);
                                aM.setExpMember("add", temp);
                                Log.d("myLog", temp);
                            }
                        } catch (Exception e) {
                            Log.d ("myLog", "error message");
                            Log.d ("myLog", e.getClass().getSimpleName());
                        }
                        Log.d ("myLog", aM.getExpMember().toString());
                        Log.d ("myLog", "parampampam");
                        mainActivity.screen.setText(aM.getExpMember().toString());
                        mainActivity.resultScreen.setText ("");
                        arithmeticSygn = "";
                    }
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setExpression (String number) {
        if (arithmeticSygn.equals("")) {
            if (!cM.getExpMember().isEmpty())
                cM.setExpMember ("clear", "void");
            aM.setExpMember ("add", number);
            updateScreen(aM);
        }
        else {
            if (!cM.getExpMember().isEmpty()) {
                createAfromC ();
                cM.setExpMember ("clear", "void");
            }
            bM.setExpMember ("add", number);
            updateScreen(bM);
        }
    }

    private void takeResult (ExpressionMember a, ExpressionMember b, String sygn) {
        String aN = a.getExpMember().toString();
        String bN = b.getExpMember().toString();

        if (aN.contains ("^") || aN.contains ("r") || aN.contains ("%")) {
            Log.d ("myLog", "precalculate a");
            aN = myCalculator.preCalculate(aN);
        }
        if (bN.contains ("^") || bN.contains ("r") || bN.contains ("%")) {
            if (bN.contains ("%") && bN.split ("%").length < 2) {
                bN = aN.concat ("%").concat (bN.split ("%") [0]);
            }
            Log.d("myLog", "precalculate b");
            Log.d("myLog", "bN = " + bN);
            bN = myCalculator.preCalculate(bN);
            Log.d ("myLog", "bN = " + bN);
        }

        try {
            if (!aM.isPositive())
                aN = "-" + aN;
            if (!bM.isPositive())
                bN = "-" + bN;
            myCalculator.calculate(aN, bN, sygn);
            aM.setExpMember ("clear", "void");
            bM.setExpMember ("clear", "void");
            cM.reSetExpMember (myCalculator.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateScreen (ExpressionMember expMember) {
        if (!expMember.isPositive())
            mainActivity.screen.setText ("-" + expMember.getExpMember().toString());
        else
            mainActivity.screen.setText (expMember.getExpMember().toString());
    }
    
    private void createAfromC () {
        aM.reSetExpMember (cM.getExpMember());
        aM.setLenght (cM.getLenght());
        if (cM.isPositive() != aM.isPositive())
            aM.changePositive();
        cM.setExpMember ("clear");
    }

    private void setarithmeticSygn (String sygn) {
        arithmeticSygn = sygn;
        mainActivity.screen.setText(arithmeticSygn);
    }

    private void addAryphmeticSygn (String sygn) {
        if (!cM.getExpMember().isEmpty())
            createAfromC();
        takeResult(aM, bM, arithmeticSygn);
        arithmeticSygn = sygn;
        mainActivity.screen.setText(arithmeticSygn);
        mainActivity.resultScreen.setText(cM.getExpMember().toString());
    }

    private void setMemory () {
        memory.setExpMember ("clear");
        memory.reSetExpMember (cM.getExpMember());
        memory.setLenght (cM.getLenght());
        if (cM.isPositive() != memory.isPositive())
            memory.changePositive();
        cM.setExpMember ("clear");
        mainActivity.screen.setText ("");
        mainActivity.memoryScreen.setText ("Memory: " + memory.getExpMember().toString());
    }

    private void getMemory () {
        if (arithmeticSygn.equals("")) {
            aM.reSetExpMember (memory.getExpMember());
            aM.setLenght (memory.getLenght());
            if (aM.isPositive() != memory.isPositive())
                aM.changePositive();
            updateScreen(aM);
        }
        else {
            bM.reSetExpMember (memory.getExpMember());
            bM.setLenght (memory.getLenght());
            if (bM.isPositive() != memory.isPositive())
                bM.changePositive();
            updateScreen(bM);
        }
    }

}
