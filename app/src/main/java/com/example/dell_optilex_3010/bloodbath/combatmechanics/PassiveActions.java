package com.example.dell_optilex_3010.bloodbath.combatmechanics;

import android.widget.TextView;

import com.example.dell_optilex_3010.bloodbath.charactercreation.Character;

import java.util.Random;

public class PassiveActions {


    TestSortControl test = new TestSortControl();

    public void passivePowerOne(Character player, Character opponent, int[] variables, Random dice, TextView tvArena) {  //"Loudness" = opponents intel permanent -6
        if ((dice.nextInt() + player.getDexterity() + variables[3]) >= (dice.nextInt() + opponent.getStamina() + variables[9])) {
            variables[10] -= 6;
            tvArena.append("BWAAAARRRH ! goes " + player.getName() + "  and " + opponent.getName() + " shits is paralysed in fear !\n");
        } else {
            tvArena.append(player.getName() + " can't seem to scream loud enough...\n");
        }
    }

    public void passivePowerTwo(Character player, Character opponent, int[] variables, Random dice, TextView tvArena) {  // "Fetish" = free attack at -6 versus magical opponents
        if (test.testingStyle(opponent, "Magic")) {
            if ((dice.nextInt() + player.getDexterity() + variables[3]) >= (dice.nextInt() + opponent.getStamina() + variables[9])) {
                variables[2]++;
                tvArena.append("Modern magic stands no chance against the old ways, " + player.getName() + " great grandfather's fetish protects you against magic and strikes back at " + opponent.getName() + " !\n");
            } else {
                tvArena.append(player.getName() + " brought to wrong necklace this morning and has no protection aainst magic, duh...\n");
            }
        }
    }

    public void passivePowerThree(Character player, Character opponent, int[] variables, Random dice, TextView tvArena) {  // "Vandalism" = dext and knowledge increase by 2 every round against science
        if (test.testingStyle(opponent, "Science")) {
            variables[4] += 2;
            variables[6] += 2;
            tvArena.append(player.getName() + " can't stand built up stuff and decides to go and destroy  " + opponent.getName() + " things !\n");
        } else {
            tvArena.append(player.getName() + " looks like a stubborn helpless child, raging and kicking stuff with no effect...\n");
        }
    }

    public void passivePowerFour(Character player, Character opponent, int[] variables, Random dice, TextView tvArena) {  // "Warming up" = +2 stmn/rd
        tvArena.append(player.getName() + " is just getting started !\n");
        variables[5] += 2;
    }
}
