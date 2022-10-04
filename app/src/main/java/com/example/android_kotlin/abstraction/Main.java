package com.example.android_kotlin.abstraction;

public class Main {
    public static void main(String[] args) {
        Shape p = new Circlle();
        p.draw();

        Shape p1 = new Rectangle();
        p1.draw();
    }

}
