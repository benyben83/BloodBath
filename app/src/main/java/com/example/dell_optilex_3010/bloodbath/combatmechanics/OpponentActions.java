package com.example.dell_optilex_3010.bloodbath.combatmechanics;

import android.widget.TextView;

import com.example.dell_optilex_3010.bloodbath.charactercreation.Character;

import java.util.Random;

public class OpponentActions {


    public void jeanMiFrappe(Character player, Character opponent, Random dice, int[] variables, TextView tvArena) {
        String statement = "Jean-Mi pue du cul et rate...\n";
        if ((opponent.getDexterity() + dice.nextInt() + variables[7]) >= (player.getStamina() + dice.nextInt() + variables[4])) {
            statement = "Jean Mi frappe et marque !\n";
            variables[1]++;

        }tvArena.append(statement);

    }
}
