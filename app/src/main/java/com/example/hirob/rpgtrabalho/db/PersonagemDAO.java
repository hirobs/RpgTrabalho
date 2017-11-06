package com.example.hirob.rpgtrabalho.db;

import android.content.Context;

import com.example.hirob.rpgtrabalho.Personagem;

/**
 * Created by hirob on 05/11/2017.
 */

public class PersonagemDAO {

    private Personagem personagem1;
    private DBHelper dbHelper;

    public PersonagemDAO(Context contexto){
        personagem1 = new Personagem();
        dbHelper = new DBHelper(contexto);
    }


    public static String createTable() {
        //Personagem p = new Personagem();
        //"CREATE TABLE IF NOT EXISTS " + Cartao.TABLE_NAME +
        String sql = "CREATE TABLE IF NOT EXISTS "+ Personagem.TABLE_NAME +"("
                + Personagem.COLUMN_NAME_ID + "INTEGER PRIMARY KEY,"
                + Personagem.COLUMN_NAME_ATKMAX + "INTEGER,"
                + Personagem.COLUMN_NAME_ATKMIN + "INTEGER,"
                + Personagem.COLUMN_NAME_DEFENSE + "INTEGER,"
                + Personagem.COLUMN_NAME_GOLD + "INTEGER,"
                + Personagem.COLUMN_NAME_HPTOTAL + "INTEGER,"
                + Personagem.COLUMN_NAME_HPATUAL + "INTEGER,"
                + Personagem.COLUMN_NAME_JOGAVEL + "INTEGER,"
                + Personagem.COLUMN_NAME_NOME + "VARCHAR(255),"
                + Personagem.COLUMN_NAME_ITEMUM + "INTEGER,"
                + Personagem.COLUMN_NAME_ITEMDOIS + "INTEGER)";

        return sql;
    }

    public static void insertPrimario(){


       // return "";
    }
}


//////////////////////////////////////////////////////////////
//    private DBHelper dbHelper;
//    private List<Cartao> cartoes;
//    public List<String> names;
//
//    public CartaoDAO(Context context) {
//        dbHelper = new DBHelper(context);
//        cartoes = new ArrayList<>();
//        names = new ArrayList<>();
//        load();
//    }
//
//    public void load() {
//        names.clear();
//        cartoes.clear();
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.query(Cartao.TABLE_NAME, null, null, null, null, null, null);
//        while (cursor.moveToNext()) {
//            String name = cursor.getString(cursor.getColumnIndex(Cartao.COLUMN_NAME_NAME));
//            String vencimento = cursor.getString(cursor.getColumnIndex(Cartao.COLUMN_NAME_VENCIMENTO));
//            String limite = cursor.getString(cursor.getColumnIndex(Cartao.COLUMN_NAME_LIMITE));
//            String bandeira = cursor.getString(cursor.getColumnIndex(Cartao.COLUMN_NAME_BANDEIRA));
//            cartoes.add(new Cartao(name, vencimento, limite, bandeira));
//            names.add(name);
//        }
//        cursor.close();
//    }
//
//    public void insert(Cartao cartao) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(Cartao.COLUMN_NAME_NAME, cartao.getNome());
//        values.put(Cartao.COLUMN_NAME_VENCIMENTO, cartao.getVencimento());
//        values.put(Cartao.COLUMN_NAME_LIMITE, cartao.getLimite());
//        values.put(Cartao.COLUMN_NAME_BANDEIRA, cartao.getBandeira());
//        db.insert("cartao", null, values);
//
//        //db.inse
//        cartoes.add(cartao);
//        names.add(cartao.getNome());
//    }

