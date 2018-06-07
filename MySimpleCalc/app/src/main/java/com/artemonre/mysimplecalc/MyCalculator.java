package com.artemonre.mysimplecalc;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.String.valueOf;

public class MyCalculator {

    private double a, b;
    MyArrayList <Character> resultList;
    String result = "";

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

    public String preCalculate (String expMember) {
        try {
            if (expMember.contains("^")) {
                Double a = degree(Double.parseDouble(expMember.split("\\^")[0]), Integer.parseInt(expMember.split("\\^")[1]));
                return a.toString();

            } else if (expMember.contains("r")) {
                Double a = root(Double.parseDouble(expMember.split("r")[0]), Integer.parseInt(expMember.split("r")[1]));
                return a.toString();
            } else if (expMember.contains("%")) {
                Double a = percent(Double.parseDouble(expMember.split("%")[0]), Double.parseDouble(expMember.split("%")[1]));
                return a.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    public void calculate (String a, String b, String ariphmeticSygn) throws Exception{
        byte resLenght = 0;
        resultList = new MyArrayList <> ();

        try {
            this.a = Double.parseDouble (a);
            this.b = Double.parseDouble (b);

            switch (ariphmeticSygn) {
                case ("+"):
                    result = valueOf(addition (this.a, this.b));
                    resLenght = (byte) result.length();
                    for (int i = 0; i < resLenght; i++) {
                        resultList.add (result.charAt (i));
                    }
                    break;
                case ("-"):
                    result = valueOf(substraction (this.a, this.b));
                    resLenght = (byte) result.length();
                    for (int i = 0; i < resLenght; i++) {
                        resultList.add (result.charAt (i));
                    }
                    break;
                case ("*"):
                    result = valueOf(mulciplication (this.a, this.b));
                    resLenght = (byte) result.length();
                    for (int i = 0; i < resLenght; i++) {
                        resultList.add (result.charAt (i));
                    }
                    break;
                case ("/"):
                    result = valueOf(division (this.a, this.b));
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
}