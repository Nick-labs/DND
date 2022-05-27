package com.example.dnd;

import java.util.Random;

public class Dice {
    public static int randomDice(int k) {
        Random rand = new Random();
        int rand_number;
        switch (k) {
            case 4:
                rand_number = rand.nextInt(4) + 1;
                break;
            case 6:
                rand_number = rand.nextInt(6) + 1;
                break;
            case 8:
                rand_number = rand.nextInt(8) + 1;
                break;
            case 10:
                rand_number = rand.nextInt(10) + 1;
                break;
            case 12:
                rand_number = rand.nextInt(12) + 1;
                break;
            case 20:
                rand_number = rand.nextInt(20) + 1;
                break;
            default:
                rand_number = 0;
                break;
        }
        return rand_number;
    }
}
