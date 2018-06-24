package com.artemonre.mysimplecalc;

import android.util.Log;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static java.lang.String.valueOf;

public class MyCalculator {

    private double a, b;
    MyArrayList <Character> resultList;
    String result = "";
    static DecimalFormat df = new DecimalFormat ("0.00", new DecimalFormatSymbols(Locale.ENGLISH));

    public static void main(String args[]) throws IOException {

    }

    public MyArrayList <Character> getResult() {
        return resultList;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    public static DecimalFormat getDf() {
        return df;
    }

    public static void setDf(byte accuracy) {
        switch (accuracy) {
            case (0): df = new DecimalFormat ("0"); break;
            case (1): df = new DecimalFormat ("0.0", new DecimalFormatSymbols(Locale.ENGLISH)); break;
            case (2): df = new DecimalFormat ("0.00", new DecimalFormatSymbols(Locale.ENGLISH)); break;
            case (3): df = new DecimalFormat ("0.00000", new DecimalFormatSymbols(Locale.ENGLISH)); break;
            case (4): df = new DecimalFormat ("0.0000000000", new DecimalFormatSymbols(Locale.ENGLISH)); break;
        }
    }

    public String preCalculate (String expMember) {
        try {
            if (expMember.contains("^")) {
                Double a = degree(Double.parseDouble(expMember.split("\\^")[0]), Integer.parseInt(expMember.split("\\^")[1]));
                Log.d ("myLog", "a = " + a.toString());
                return df.format (a);

            } else if (expMember.contains("r")) {
                Double a = root(Double.parseDouble(expMember.split("r")[0]), Integer.parseInt(expMember.split("r")[1]));
                return df.format (a);
            } else if (expMember.contains("%")) {
                Double a = percent(Double.parseDouble(expMember.split("%")[0]), Double.parseDouble(expMember.split("%")[1]));
                return df.format (a);
            } else if (expMember.contains("L")) {
                Double a = logarifm (Double.parseDouble(expMember.split("L")[0]), Integer.parseInt(expMember.split("L")[1]));
                return df.format (a);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d ("myLog", e.getClass().getSimpleName());
        }
        return "error";
    }

    public void calculate (String a, String b, String ariphmeticSygn) throws Exception{
        byte resLenght;
        resultList = new MyArrayList <> ();

        try {
            try {
                this.a = Double.parseDouble(a);
            } catch (Exception e) {
                Log.d ("myLog", "error");
            }
            Log.d ("myLog", Double.parseDouble (a) + "");
            Log.d ("myLog", this.a + "");
            this.b = Double.parseDouble (b);

            switch (ariphmeticSygn) {
                case ("+"):
                    result = df.format(addition (this.a, this.b));
                    resLenght = (byte) result.length();
                    for (int i = 0; i < resLenght; i++) {
                        resultList.add (result.charAt (i));
                    }
                    break;
                case ("-"):
                    result = df.format(substraction (this.a, this.b));
                    resLenght = (byte) result.length();
                    for (int i = 0; i < resLenght; i++) {
                        resultList.add (result.charAt (i));
                    }
                    break;
                case ("*"):
                    result = df.format(mulciplication (this.a, this.b));
                    resLenght = (byte) result.length();
                    for (int i = 0; i < resLenght; i++) {
                        resultList.add (result.charAt (i));
                    }
                    break;
                case ("/"):
                    result = df.format(division (this.a, this.b));
                    resLenght = (byte) result.length();
                    for (int i = 0; i < resLenght; i++) {
                        resultList.add (result.charAt (i));
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public double addition (double a, double b) throws Exception {
        Log.d ("myLog", 1.0 * a + b + "");
        return 1.0 * a + b;
    }
    public double substraction (double a, double b) throws Exception {
        return 1.0 * a - b;
    }
    public double mulciplication (double a, double b) throws Exception {
        return 1.0 * a * b;
    }
    public double division (double a, double b) throws Exception {
        return 1.0 * a / b;
    }
    public double percent (double a, double b) throws Exception {
        return 1.0 * a / 100 * b;
    }
    public double root(double a, int b) throws Exception {
        return Math.exp (Math.log (a)/b);
    }
    public double degree(double a, int b) throws Exception {
        return Math.pow (a, b);
    }
    public double logarifm (double a, int b) throws Exception {
        return Math.log (a)/Math.log (b);
    }
}