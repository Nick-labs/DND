package com.example.dnd;

import java.util.Random;

public class Dice {
    public static void main(String args[])
    {
        // create instance of Random class
        Random rand = new Random();

        // Generate random integers in range 0 to 999
        int rand_int1 = rand.nextInt(1000);
        int rand_int2 = rand.nextInt(1000);



        // Generate Random doubles
        double rand_dub1 = rand.nextDouble();
        double rand_dub2 = rand.nextDouble();



        System.out.println(randomDies(2));
    }

    public static int randomDies(int k){
        Random rand = new Random();
        int rand_number;
        switch (k) {

            case 4: rand_number = rand.nextInt(4) + 1;
                break;
            case 6: rand_number = rand.nextInt(6) + 1;
                break;
            case 8: rand_number = rand.nextInt(8) + 1;
                break;
            case 10: rand_number = rand.nextInt(10) + 1;
                break;
            case 12: rand_number = rand.nextInt(12) + 1;
                break;
            case 20: rand_number = rand.nextInt(20) + 1;
                break;
            default: rand_number = 0;
                break;
        }
        return rand_number;
    }

}
