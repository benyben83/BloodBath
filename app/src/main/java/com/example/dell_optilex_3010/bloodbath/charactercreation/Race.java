package com.example.dell_optilex_3010.bloodbath.charactercreation;

public class Race {
    private  String name;
    private int dexterity;
    private int stamina;
    private int intelligence;
    private int knowledge;

    public Race(String name, int dexterity, int stamina, int intelligence, int knowledge) {
        this.name = name;
        this.dexterity = dexterity;
        this.stamina = stamina;
        this.intelligence = intelligence;
        this.knowledge = knowledge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(int knowledge) {
        this.knowledge = knowledge;
    }
}
