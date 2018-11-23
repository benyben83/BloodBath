package com.example.dell_optilex_3010.bloodbath.combatmechanics;
import com.example.dell_optilex_3010.bloodbath.combatmechanics.TestSortControl;
import android.widget.TextView;

import java.util.Random;

import com.example.dell_optilex_3010.bloodbath.charactercreation.Character;

public class DirectActions {


    public DirectActions() {
    }

    public void directPowerOne(Character player, Character opponent, int[] variables, Random dice, TextView tvArena) {  // "Rush" = simple attack
        if ((dice.nextInt() + player.getDexterity() + variables[3]) >= (dice.nextInt() + opponent.getStamina() + variables[8])) {
            variables[2]++;
            tvArena.append("Why do complicated ? " + player.getName() + "  just charges forward beats the shit out of " + opponent.getName() + " !\n");
        } else {
            tvArena.append(player.getName() + " poorly misses...\n");
        }
    }

    public void directPowerTwo(Character player, Character opponent, int[] variables, Random dice, TextView tvArena) {  // "Frenetic bashing" = dext attack with +6 bonus if the player is wounded
        TestSortControl test = new TestSortControl();
        if (test.testingWounds(player, player, variables)) // 3 arguments needed for testing
            if ((dice.nextInt() + player.getDexterity() + variables[3]) >= (dice.nextInt() + opponent.getStamina() + variables[8])) {
                variables[2]++;
                tvArena.append(player.getName() + "  goes for some good old fashioned battle rage and it fits like a charm, " + opponent.getName() + "gets wacked !\n");
            } else {
                tvArena.append(player.getName() + " frenetically swings her weapon in the wrong direction, how dumb...\n");
            }
    }
}