package com.example.dell_optilex_3010.bloodbath.charactercreation;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Databases extends SQLiteOpenHelper {
    private static Databases instance;
    public static Context ctxt;


    private Databases(Context ctxt) {
        super(ctxt, "characters", null, 1);
        this.ctxt = ctxt;
    }

    public final static Databases getInstance(Context ctxt) {
        if (instance == null) {
            instance = new Databases(ctxt);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE saves (" +
                "   id int PRIMARY KEY NOT NULL" +
                ",  name TEXT,  dexterity INTEGER,  stamina INTEGER ,  intelligence INTEGER,  knowledge INTEGER,  style_one TEXT,  style_two TEXT,  style_three TEXT,  style_four TEXT" +
                ",  style_five TEXT,  race TEXT, health INTEGER, level INTEGER, action_one TEXT, action_two TEXT, action_three TEXT, action_four TEXT, action_five TEXT, action_six TEXT" +
                ", action_seven TEXT, action_eight TEXT, action_nine TEXT, action_ten TEXT" +
                ")";
        db.execSQL(sql);
        db.execSQL("INSERT INTO saves (id,name,dexterity,stamina,intelligence,knowledge,style_one,style_two, style_three, style_four, style_five, race, health, level, action_one, action_two, action_three, action_four, action_five, action_six, action_seven, action_eight, action_nine, " +
                "action_ten ) values ('1','momo','20','20','20','20','Power','', '', '', '', 'Jew', '3', '6', 'Inquisition', 'Ancient rites', 'Barbaric fire', 'Battle rage', 'Loudness', 'Fetish', 'Vandalism', 'Warming up', 'Rush', 'Frenetic bashing')");
        db.execSQL("INSERT INTO saves (id,name,dexterity,stamina,intelligence,knowledge,style_one,style_two, style_three, style_four, style_five, race, health, level, action_one, action_two, action_three, action_four, action_five, action_six, action_seven, action_eight, action_nine, " +
                "action_ten ) values ('2','Jacky','0','0','0','0','Magic','', 'rien', 'rien', 'rien', 'Troll', '3', '6', '', ' ', ' ', ' ', '', '', '', ' ', '', ' ')");
        db.execSQL("INSERT INTO saves (id,name,dexterity,stamina,intelligence,knowledge,style_one,style_two, style_three, style_four, style_five, race, health, level, action_one, action_two, action_three, action_four, action_five, action_six, action_seven, action_eight, action_nine, " +
                "action_ten ) values ('3','Michel','0','0','0','0','Magic','', 'rien', 'rien', 'rien', 'Orc', '3', '6', '', ' ', ' ', ' ', '', '', '', ' ', '', ' ')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}