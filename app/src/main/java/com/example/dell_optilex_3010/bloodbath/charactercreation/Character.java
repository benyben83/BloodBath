package com.example.dell_optilex_3010.bloodbath.charactercreation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Character {
    private int id;
    private String name;
    private int dexterity;
    private int stamina;
    private int intelligence;
    private int knowledge;
    private String style1;
    private String style2;
    private String style3;
    private String style4;
    private String style5;
    private String race;
    private int health;
    private int level;
    private String actionOne;
    private String actionTwo;
    private String actionThree;
    private String actionFour;
    private String actionFive;
    private String actionSix;
    private String actionSeven;
    private String actionEight;
    private String actionNine;
    private String actionTen;
    private SQLiteDatabase db;

    public Character(Context c) {
        Databases databases = Databases.getInstance(c);
        db = databases.getWritableDatabase();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStyle1() {
        return style1;
    }

    public void setStyle1(String style1) {
        this.style1 = style1;
    }

    public String getStyle2() {
        return style2;
    }

    public void setStyle2(String style2) {
        this.style2 = style2;
    }

    public String getStyle3() {
        return style3;
    }

    public void setStyle3(String style3) {
        this.style3 = style3;
    }

    public String getStyle4() {
        return style4;
    }

    public void setStyle4(String style4) {
        this.style4 = style4;
    }

    public String getStyle5() {
        return style5;
    }

    public void setStyle5(String style5) {
        this.style5 = style5;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getActionOne() {
        return actionOne;
    }

    public void setActionOne(String actionOne) {
        this.actionOne = actionOne;
    }

    public String getActionTwo() {
        return actionTwo;
    }

    public void setActionTwo(String actionTwo) {
        this.actionTwo = actionTwo;
    }

    public String getActionThree() {
        return actionThree;
    }

    public void setActionThree(String actionThree) {
        this.actionThree = actionThree;
    }

    public String getActionFour() {
        return actionFour;
    }

    public void setActionFour(String actionFour) {
        this.actionFour = actionFour;
    }

    public String getActionFive() {
        return actionFive;
    }

    public void setActionFive(String actionFive) {
        this.actionFive = actionFive;
    }

    public String getActionSix() {
        return actionSix;
    }

    public void setActionSix(String actionSix) {
        this.actionSix = actionSix;
    }

    public String getActionSeven() {
        return actionSeven;
    }

    public void setActionSeven(String actionSeven) {
        this.actionSeven = actionSeven;
    }

    public String getActionEight() {
        return actionEight;
    }

    public void setActionEight(String actionEight) {
        this.actionEight = actionEight;
    }

    public String getActionNine() {
        return actionNine;
    }

    public void setActionNine(String actionNine) {
        this.actionNine = actionNine;
    }

    public String getActionTen() {
        return actionTen;
    }

    public void setActionTen(String actionTen) {
        this.actionTen = actionTen;
    }

    public void saveCharacter(int saveNumber) { // used to transfer character data to database
        try {
            ContentValues values = new ContentValues();
            values.put("name", getName());
            values.put("dexterity", getDexterity());
            values.put("stamina", getStamina());
            values.put("intelligence", getIntelligence());
            values.put("knowledge", getKnowledge());
            values.put("style_one", getStyle1());
            values.put("style_two", getStyle2());
            values.put("style_three", getStyle3());
            values.put("style_four", getStyle4());
            values.put("style_five", getStyle5());
            values.put("race", getRace());
            values.put("health", getHealth());
            values.put("level", getLevel());
            values.put("action_one", getActionOne());
            values.put("action_two", getActionTwo());
            values.put("action_three", getActionThree());
            values.put("action_four", getActionFour());
            values.put("action_five", getActionFive());
            values.put("action_six", getActionSix());
            values.put("action_seven", getActionSeven());
            values.put("action_eight", getActionEight());
            values.put("action_nine", getActionNine());
            values.put("action_ten", getActionTen());


            db.update("saves", values, "id=" + getId(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadCharacter(int saveNumber) { // used to extract character data from database
        String querySql = "select * from saves where id =" + getLevel();
        Cursor cursor = db.rawQuery(querySql, null);
        try {
            cursor.moveToFirst();
            setId(cursor.getInt(0));
            setName(cursor.getString(1));
            setDexterity(cursor.getInt(2));
            setStamina(cursor.getInt(3));
            setIntelligence(cursor.getInt(4));
            setKnowledge(cursor.getInt(5));
            setStyle1(cursor.getString(6));
            setStyle2(cursor.getString(7));
            setStyle3(cursor.getString(8));
            setStyle4(cursor.getString(9));
            setStyle5(cursor.getString(10));
            setRace(cursor.getString(11));
            setHealth(cursor.getInt(12));
            setLevel(cursor.getInt(13));
            setActionOne(cursor.getString(14));
            setActionTwo(cursor.getString(15));
            setActionThree(cursor.getString(16));
            setActionFour(cursor.getString(17));
            setActionFive(cursor.getString(18));
            setActionSix(cursor.getString(19));
            setActionSeven(cursor.getString(20));
            setActionEight(cursor.getString(21));
            setActionNine(cursor.getString(22));
            setActionTen(cursor.getString(23));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

