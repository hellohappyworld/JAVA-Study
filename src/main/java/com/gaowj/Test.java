package com.gaowj;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random random = new Random();
        for (int index = 0; index < 100; index++) {
            int i = random.nextInt(100000);
            System.out.println(i);
        }
    }
}
