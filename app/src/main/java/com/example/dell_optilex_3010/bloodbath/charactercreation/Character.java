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
    private SQLiteDatabase db ;

    public Character(Context c) {
        Databases databases =  Databases.getInstance(c);
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

    public void saveCharacter() {
        try {
            ContentValues values = new ContentValues();
            values.put("name", getName());
            values.put("dexterity", getDexterity());
            values.put("stamina", getStamina());
            values.put("intelligence", getIntelligence());
            values.put("knowledge", getKnowledge());
            values.put("style1", getStyle1());
            values.put("style2", getStyle2());
            values.put("style3", getStyle3());
            values.put("style4", getStyle4());
            values.put("style5", getStyle5());
            values.put("race", getRace());
            db.update("saves", values, "id=1", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadCharacter() {
        String querySql = "select * from saves where id =1";
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

