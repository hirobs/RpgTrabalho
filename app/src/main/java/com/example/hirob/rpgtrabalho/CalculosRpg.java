package com.example.hirob.rpgtrabalho;

import java.util.Random;

/**
 * Created by hirob on 02/11/2017.
 */

public class CalculosRpg {
    public static int ataque(int min, int max, int defesa){
        Random rn = new Random();

        return rn.nextInt(max-min +1)+ min;
    }


}
