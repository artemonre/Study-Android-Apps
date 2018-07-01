package com.artemonre.mysudoku;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Matrix <T> {

    private Class <T> clazz;
    private T [] matrix;
    private int columns, lines;
    private int [] placeOfElement = new int [2];
    private int size;

    @SuppressWarnings("unchecked")
    public Matrix(Class <T> clazz, int x){
        this.clazz = clazz;
        matrix = (T[]) Array.newInstance(clazz, x * x);
        columns = x;
        lines = x;
        size = columns * lines;
    }
    @SuppressWarnings("unchecked")
    public Matrix(Class <T> clazz, int x, int y){
        this.clazz = clazz;
        matrix = (T[]) Array.newInstance(clazz, x * y);
        columns = x;
        lines = y;
        size = columns * lines;
    }
    public int getColumn(T element) {
        int index = Arrays.asList (matrix).indexOf (element);
        if (index == -1)
            return -1;
        else
            return index - index/columns * columns;
    }
    public Set <T> getColumnValues (int column) {
        Set <T> columnValues = new HashSet <> ();
        for (int i = 0; i < lines; i++) {
            columnValues.add (getElement (i, column));
        }
        return columnValues;
    }
    public int getColumns() {
        return columns;
    }
    @SuppressWarnings("unchecked")
    public void setColumns(int columns) {
        this.columns = columns;
        size = columns * lines;
        matrix = Arrays.copyOf (matrix, size);
    }
    public int getLine(T element) {
        int index = Arrays.asList (matrix).indexOf (element);
        if (index == -1)
            return -1;
        else
            return index/columns;
    }
    public Set <T> getLineValues (int line) {
        Set <T> lineValues = new HashSet <> ();
        for (int i = 0; i < columns; i++) {
            lineValues.add (getElement (i, line));
        }
        return lineValues;
    }
    public int getLines() {
        return lines;
    }
    public void setLines(int lines) {
        this.lines = lines;
        size = columns * lines;
        matrix = Arrays.copyOf (matrix, size);
    }
    public Set <T> getAreaValues (int columnStart,int columnEnd, int lineStart, int lineEnd) {
        Set <T> areaValues = new HashSet <> ();
        for (int i = lineStart; i <= lineEnd; i++) {
            for (int j = columnStart; j <= columnEnd; j++) {
                areaValues.add (getElement (j, i));
            }
        }
        return areaValues;
    }
    public int getSize () {
        return size;
    }
    public void addElement (T element) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix [i] == null) {
                matrix[i] = element;
                return;
            }
        }
    }
    public void addElement (T element, int column, int line) {
        matrix [column + line * columns] = element;
    }
    public void addElement (T element, int index) {
        matrix [index] = element;
    }
    public void removeElement (T element) {
        int index = Arrays.asList (matrix).indexOf (element);
        matrix [index] = null;
    }
    public void removeElement (int column, int line) {
        matrix [column + line * columns] = null;
    }
    public T getElement (int column, int line) {
        return matrix [column + line * columns];
    }
    public T getElement (int index) {
        return matrix [index];
    }
    public int [] getPlaceOfElement (T element) {
        placeOfElement [0] = getColumn (element);
        placeOfElement [1] = getLine (element);
        return placeOfElement;
    }
    public int getIndex (int column, int line) {
        return column + line * columns;
    }
    public void copyFrom (T [] originalArray) {
        matrix = Arrays.copyOf (originalArray, size);
    }
}
