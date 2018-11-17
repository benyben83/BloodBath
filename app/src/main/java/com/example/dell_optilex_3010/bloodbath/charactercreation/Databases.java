package com.example.dell_optilex_3010.bloodbath.charactercreation;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Databases extends SQLiteOpenHelper {
    private static Databases instance ;
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
                ",  name TEXT" +
                ",  dexterity INTEGER" +
                ",  stamina INTEGER " +
                ",  intelligence INTEGER" +
                ",  knowledge INTEGER" +
                ",  style1 TEXT" +
                ",  style2 TEXT" +
                ",  style3 TEXT" +
                ",  style4 TEXT" +
                ",  style5 TEXT" +
                ",  race TEXT" +
                ")";
        db.execSQL(sql);
        db.execSQL("INSERT INTO saves (id,name,dexterity,stamina,intelligence,knowledge,style1,style2, style3, style4, style5, race ) values ('1','momo','10','10','10','10','rien','rien', 'rien', 'rien', 'rien', 'juif')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}