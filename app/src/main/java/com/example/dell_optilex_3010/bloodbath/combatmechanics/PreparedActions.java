package com.example.dell_optilex_3010.bloodbath.combatmechanics;

import android.widget.TextView;

import com.example.dell_optilex_3010.bloodbath.charactercreation.Character;

import java.util.Random;

public class PreparedActions {
    TestSortControl test = new TestSortControl();

    public void preparedPowerOne(Character player, Character opponent, int[] variables, Random dice, TextView tvArena) { // "Barbaric fire" : free attack at +6 dext if opponents uses science

        if (test.testingStyle(opponent, "Science'")) {

            if ((dice.nextInt() + player.getDexterity() + 6+variables[3]) >= (dice.nextInt() + opponent.getStamina())+variables[8]) {
                tvArena.append("Science is wrong, " + opponent.getName() + " shall burn\n");
                variables[2]++;
            }
        }
    }

    public void preparedPowerTwo(Character player, Character opponent, int[] variables, Random dice, TextView tvArena, String[]actionsStored) { // "Battle rage" : free attack if player wounded
        String statement = player.getName() + " is too healthy to react...\n";
        if (test.testingWounds(player, player, variables)) {
            statement = player.getName() + " gets mad at the sight of blood and totally freaks out !\n";
            if ((player.getDexterity() + dice.nextInt()) >= (opponent.getStamina() + dice.nextInt())) {
                statement = player.getName() + " gets mad at the sight of blood and preventively attacks " + opponent.getName() + " !\n";
                variables[2]++;

            }
        }
    }
}
