package main.api;

import java.util.ArrayList;
import java.util.Random;

public class DamageCalculation {


    public static int damageCalculation(int amount, int dice)
    {
        int damage = 0;
        ArrayList<Integer> amountDices = new ArrayList<>();
        Random random = new Random();
        int int_random;
        for (int i = 0; i < amount; i++)
        {
            int_random = random.nextInt(dice) + 1;
            amountDices.add(int_random);
        }
        for (Integer amountDice : amountDices) {
            damage += amountDice;
        }
        return damage;
    }

}
