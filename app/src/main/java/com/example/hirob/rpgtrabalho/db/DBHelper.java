package com.example.hirob.rpgtrabalho.db;

/**
 * Created by hirob on 05/11/2017.
 */

//import android.content.ClipData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.hirob.rpgtrabalho.Personagem;

import java.io.File;

/**
 * Created by hirob on 05/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "rpg.db";
    private Context contextoTT;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        contextoTT = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        if (doesDbExist() == false) {
            db.execSQL(PersonagemDAO.createTable());
            PersonagemDAO.insertPrimario();
            System.out.println("ENTROU NO FALSEEEEE, NAO POSSUI DB");
            Log.d("banco de dados","ENTROU NO FALSEEEEE, NAO POSSUI DB" );
            System.exit(0);

        }
        System.out.println("ENTROU NO TRUEEEEE, POSSUI DB");
        Log.d("Banco de dados","ENTROU NO TRUEEEEE, POSSUI DB") ;
        System.exit(0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + Personagem.TABLE_NAME;
        // String sql2 = "DROP TABLE IF EXISTS " +  Item.
        db.execSQL(sql);
    }

    public boolean doesDbExist() {
        File dbFile = contextoTT.getDatabasePath(DATABASE_NAME);
        return dbFile.exists();
    }
    //    public class DBHelper extends SQLiteOpenHelper {
//    private static final int DATABASE_VERSION = 1;
//    private static final String DATABASE_NAME = "contacts.db";
//
//    public DBHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String sql = "CREATE TABLE IF NOT EXISTS " + Cartao.TABLE_NAME + " (" +
//                Cartao.COLUMN_NAME_NAME + " VARCHAR(255) NOT NULL PRIMARY KEY, " +
//                Cartao.COLUMN_NAME_LIMITE + " VARCHAR(255) NOT NULL, " +
//                Cartao.COLUMN_NAME_VENCIMENTO + " VARCHAR(255) NOT NULL, " +
//                Cartao.COLUMN_NAME_BANDEIRA + " VARCHAR(255) NOT NULL)";
//        db.execSQL(sql);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String sql = "DROP TABLE IF EXISTS " + Cartao.TABLE_NAME;
//        db.execSQL(sql);
//    }
//}



}