package com.artemonre.mysimplecalc;

import android.util.Log;
import android.view.View;

public class MyOnClickListener implements View.OnClickListener {

    private Expression expression = new Expression ();

    @Override
    public void onClick(View v) {

        try {

            switch (v.getId()) {

                case (R.id.btnC): expression.toClear (); break;
                case (R.id.btnMSave): expression.setMemory (); break;
                case (R.id.btnMLoad): expression.getMemory (); break;
                case (R.id.btnSvn): expression.setExpression ("7"); break;
                case (R.id.btnEght): expression.setExpression ("8"); break;
                case (R.id.btnNine): expression.setExpression ("9"); break;
                case (R.id.btnFour): expression.setExpression ("4"); break;
                case (R.id.btnFive): expression.setExpression ("5"); break;
                case (R.id.btnSix): expression.setExpression ("6"); break;
                case (R.id.btnOne): expression.setExpression ("1"); break;
                case (R.id.btnTwo): expression.setExpression ("2"); break;
                case (R.id.btnThree): expression.setExpression ("3"); break;
                case (R.id.btnPlusMinus): expression.plusMinus (); break;
                case (R.id.btnZero): expression.addZero (); break;
                case (R.id.btnDot): expression.addDot (); break;
                case (R.id.btnPls):
                    if (expression.arithmeticSign.equals(""))
                        expression.setArithmeticSign ("+");
                    else
                        expression.addArithmeticSign ("+");
                    break;

                case (R.id.btnBckSpc): expression.backSpace (); break;
                case (R.id.btnFrct): expression.toFraction (); break;
                case (R.id.btnLgrfm): expression.addSpecialSign ("L"); break;
                case (R.id.btnPrcnt): expression.addSpecialSign ("%"); break;
                case (R.id.btnMns):
                    if (expression.arithmeticSign.equals(""))
                        expression.setArithmeticSign ("-");
                    else
                        expression.addArithmeticSign ("-");
                    break;

                case (R.id.btnRoot): expression.addSpecialSign ("r"); break;
                case (R.id.btnMlt):
                    if (expression.arithmeticSign.equals(""))
                        expression.setArithmeticSign ("*");
                    else
                        expression.addArithmeticSign ("*");
                    break;

                case (R.id.btnDgr):expression.addSpecialSign ("^"); break;
                case (R.id.btnDvsn):
                    if (expression.arithmeticSign.equals(""))
                        expression.setArithmeticSign ("/");
                    else
                        expression.addArithmeticSign ("/");
                    break;

                case (R.id.btnEqls): expression.arithmeticEqual (); break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
