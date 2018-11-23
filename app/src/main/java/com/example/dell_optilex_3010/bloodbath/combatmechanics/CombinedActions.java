package com.example.dell_optilex_3010.bloodbath.combatmechanics;

import android.widget.TextView;

import com.example.dell_optilex_3010.bloodbath.charactercreation.Character;

import java.util.Random;

public class CombinedActions {
    TestSortControl test = new TestSortControl();

    public void combinedPowerOne(Character player, Character opponent, int[] variables,  String[] actionsstored, TextView tvArena) { // "Inquisition" : if the player has prepared barbaric fire and his opponent is magical, the latter suffers 1 automatic wound/rd
        if ((test.testingPreparedAction(player, actionsstored, "Burn the witch !")) && test.testingStyle(opponent, "Magic")) {
            variables[2]++;
            tvArena.append(player.getName() + " has brought the local inquisition for barbecue, as it just so happens that we found a witch !\n");
        } else {
            tvArena.append("No witch to burn here, let's go home and have dinner...\n");
        }
    }

    public void combinedPowerTwo(Character player, Character opponent, int[] variables, String[] actionsstored, TextView tvArena) { // "Ancient rites" : if the player has prepared fetish and his opponent is magic, he gets +6 in every stat
        if ((test.testingPreparedAction(player, actionsstored, "Fetish")) && test.testingStyle(opponent, "Magic")) {
            variables[3] += 6;
            variables[4] += 6;
            variables[5] += 6;
            variables[6] += 6;
            tvArena.append(player.getName() + " has better than magic : old gods FTW !\n");
        }else{
            tvArena.append(player.getName() + " completely forgot what ancient rites meant\n");
        }
    }
}
