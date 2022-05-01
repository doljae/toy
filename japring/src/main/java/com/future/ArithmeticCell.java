package com.future;

public class ArithmeticCell extends SimpleCell {

    private int left;
    private int right;

    public ArithmeticCell(String name) {
        super(name);
    }

    public void setLeft(int left) {
        this.left = left;
        onNext(this.left + right);
    }

    public void setRight(int right) {
        this.right = right;
        onNext(this.right + left);
    }

}
