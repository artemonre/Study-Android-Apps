package com.artemonre.mysimplecalc;

import java.util.ArrayList;
import java.util.Iterator;

public class MyArrayList <T> extends ArrayList {

    @Override
    public String toString() {
        Iterator<T> it = iterator();
        if (! it.hasNext())
            return "0";

        StringBuilder sb = new StringBuilder();
        for (;;) {
            T t = it.next();
            sb.append(t == this ? "(this Collection)" : t);
            if (! it.hasNext())
                return sb.toString();
        }
    }
}
