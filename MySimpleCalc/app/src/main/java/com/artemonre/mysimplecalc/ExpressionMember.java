package com.artemonre.mysimplecalc;

public class ExpressionMember {

    private MyArrayList <Character> expMember = new MyArrayList <> ();
    private boolean positive = true;
    private byte lenght = 0;

    public MyArrayList<Character> getExpMember() {
        return expMember;
    }

    public void setExpMember(String ... args) {
        switch (args [0]) {
            case ("clear"): this.expMember.clear(); this.lenght = 0; positive = true; break;
            case ("add"):
                switch (args [1]) {
                    case ("^"):
                    case ("r"):
                    case ("%"):
                        if (this.expMember.contains ("^") || this.expMember.contains ("r") || this.expMember.contains ("%"))
                            break;
                    default: this.expMember.add(args[1]); this.lenght++;
                }
                break;
            case ("addToInd"): this.expMember.add (Integer.parseInt (args [2]), args [1]); break;
            case ("remove"): this.expMember.remove (this.lenght - 1); this.lenght--; break;
        }
    }

    public void reSetExpMember (MyArrayList <Character> newList) {
        for (Object element : newList) {
            this.setExpMember ("add", element.toString());
        }
    }

    public boolean isPositive () {
        return this.positive;
    }

    public void changePositive () {
        if (this.positive)
            this.positive = false;
        else
            this.positive = true;
    }

    public void setLenght (byte lenght) {
        this.lenght = lenght;
    }

    public byte getLenght () {
        return this.lenght;
    }

}
