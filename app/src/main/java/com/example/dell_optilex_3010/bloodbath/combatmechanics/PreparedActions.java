package com.example.dell_optilex_3010.bloodbath.combatmechanics;

import android.widget.TextView;

import com.example.dell_optilex_3010.bloodbath.charactercreation.Character;

import java.util.Random;

public class PreparedActions {


    public PreparedActions() {
    }

    public void preparedPowerOne(Character player, Character opponent, int[] variables, Random dice, TextView tvArena) { // "Barbaric fire" : free attack at +6 dext if opponents uses science
                 if ((dice.nextInt() + player.getDexterity() + 6+variables[3]) >= (dice.nextInt() + opponent.getStamina())+variables[8]) {
                tvArena.append("Science is wrong, " + opponent.getName() + " shall burn\n");
                variables[2]++;
            }else{
                tvArena.append(player.getName()+" can't even set all this science stuff on fire...");
            }

    }

    public void preparedPowerTwo(Character player, Character opponent, int[] variables, Random dice, TextView tvArena) { // "Battle rage" : free attack if player wounded
        TestSortControl test = new TestSortControl();
        String statement = player.getName() + " is too healthy to react...\n";
        if (test.testingWounds(player, player, variables)) {
            statement = player.getName() + " gets mad at the sight of blood and totally freaks out !\n";
            if ((player.getDexterity() + dice.nextInt()) >= (opponent.getStamina() + dice.nextInt())) {
                statement = player.getName() + " gets mad at the sight of blood and preventively attacks " + opponent.getName() + " !\n";
                variables[2]++;

            }
        }tvArena.append(statement);
    }
}
