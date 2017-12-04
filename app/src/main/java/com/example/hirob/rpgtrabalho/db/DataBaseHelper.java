package com.example.hirob.rpgtrabalho.db;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hirob.rpgtrabalho.Personagem;



public class DataBaseHelper extends SQLiteOpenHelper {
    private static String TAG = "DataBaseHelper"; // Tag just for the LogCat window
    //destination path (location) of our database on device
    public static String DB_PATH = "/data/data/" + "com.example.hirob.rpgtrabalho" + "/databases/";
    public static String DB_NAME = "Personagem";// Database name
    private SQLiteDatabase mDataBase;
    private final Context mContext;


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    //
//    public DataBaseHelper(Context context)
//    {
//        super(context, DB_NAME, null, 1);// 1? Its database Version
//        if(android.os.Build.VERSION.SDK_INT >= 17){
//            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
//        }
//        else
//        {
//            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
//        }
//        this.mContext = context;
//    }
//
//    public void createDataBase() throws IOException
//    {
//        //If the database does not exist, copy it from the assets.
//
//        boolean mDataBaseExist = checkDataBase();
//        if(!mDataBaseExist)
//        {
//            this.getReadableDatabase();
//            this.close();
//            try
//            {
//                //Copy the database from assests
//                copyDataBase();
//                Log.e(TAG, "createDatabase database created");
//            }
//            catch (IOException mIOException)
//            {
//                throw new Error("ErrorCopyingDataBase");
//            }
//        }
//    }
//
//    //Check that the database exists here: /data/data/your package/databases/Da Name
//    private boolean checkDataBase()
//    {
//        File dbFile = new File(DB_PATH + DB_NAME);
//        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
//        return dbFile.exists();
//    }
//
//    //Copy the database from assets
//    private void copyDataBase() throws IOException
//    {
//        InputStream mInput = mContext.getAssets().open(DB_NAME);
//        String outFileName = DB_PATH + DB_NAME;
//        OutputStream mOutput = new FileOutputStream(outFileName);
//        byte[] mBuffer = new byte[1024];
//        int mLength;
//        while ((mLength = mInput.read(mBuffer))>0)
//        {
//            mOutput.write(mBuffer, 0, mLength);
//        }
//        mOutput.flush();
//        mOutput.close();
//        mInput.close();
//    }
//
//    //Open the database, so we can query it
//    public boolean openDataBase() throws SQLException
//    {
//        String mPath = DB_PATH + DB_NAME;
//        //Log.v("mPath", mPath);
//        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
//        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
//        return mDataBase != null;
//    }
//
//    @Override
//    public synchronized void close()
//    {
//        if(mDataBase != null)
//            mDataBase.close();
//        super.close();
//    }
    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);// 1? Its database Version
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            //   DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            //    DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.mContext = context;
    }

    public void openDataBase() {
        String mPath = DB_PATH + DB_NAME;
        if (mDataBase != null && mDataBase.isOpen()) {
            return;
        }
        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDataBase() {
        if (mDataBase != null) {
            mDataBase.close();
        }
    }

    public List<Personagem> allPessoa() {
        openDataBase();
        mDataBase = this.getWritableDatabase();
        List<Personagem> listPersonagem = new ArrayList<>();
        String sql = "SELECT * FROM personagem";
        Cursor cursor = mDataBase.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    Personagem p = new Personagem();
                    p.setID(cursor.getInt(0));
                    p.setAtkMax(cursor.getInt(1));
                    p.setAtkMin(cursor.getInt(2));
                    p.setGold(cursor.getInt(3));
                    p.setDefense(cursor.getInt(4));
                    p.setNmPersonagem(cursor.getString(8));
                    p.setHpTotal(cursor.getInt(9));
                    p.setHpAtual(cursor.getInt(10));

                    listPersonagem.add(p);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        mDataBase.close();
        return listPersonagem;

    }

    public List<Personagem> allInimigo() {
        openDataBase();
        mDataBase = this.getWritableDatabase();
        List<Personagem> listPersonagem = new ArrayList<Personagem>();
        String sql = "SELECT * FROM personagem where jogavel = 0";
        Cursor cursor = mDataBase.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    Personagem p = new Personagem();
                    p.setID(cursor.getInt(0));
                    p.setAtkMax(cursor.getInt(1));
                    p.setAtkMin(cursor.getInt(2));
                    p.setGold(cursor.getInt(3));
                    p.setDefense(cursor.getInt(4));
                    p.setNmPersonagem(cursor.getString(8));
                    p.setHpTotal(cursor.getInt(9));
                    p.setHpAtual(cursor.getInt(10));

                    listPersonagem.add(p);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        mDataBase.close();
        return listPersonagem;

    }


    public void salvarPersonagem(Personagem perso) {
        openDataBase();
        mDataBase = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("ID", 100);
        values.put("atkMax", perso.getAtkMax());
        values.put("atkMin", perso.getAtkMin());
        values.put("gold", perso.getGold());
        values.put("defense", perso.getDefense());
        values.put("nmPersonagem", perso.getNmPersonagem());
        values.put("hpTotal", perso.getHpTotal());
        values.put("hpAtual", perso.getHpAtual());

        int id = (int) mDataBase.insertWithOnConflict("personagem", null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if (id == -1) {
            mDataBase.update("personagem", values, "ID=?", new String[]{"100"});  // number 1 is the _id here, update to variable for your code
        } else {

            mDataBase.insert("personagem", null, values);
        }


        mDataBase.close();


    }

    public Personagem pegarPersonagemPronto() {
        openDataBase();
        mDataBase = this.getWritableDatabase();
        String sql = "SELECT * FROM personagem where id = 100";
        Cursor cursor = mDataBase.rawQuery(sql, null);
        Personagem p = new Personagem();
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    p.setID(cursor.getInt(0));
                    p.setAtkMax(cursor.getInt(1));
                    p.setAtkMin(cursor.getInt(2));
                    p.setGold(cursor.getInt(3));
                    p.setDefense(cursor.getInt(4));
                    p.setNmPersonagem(cursor.getString(8));
                    p.setHpTotal(cursor.getInt(9));
                    p.setHpAtual(cursor.getInt(10));

                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        mDataBase.close();
        return p;
    }

    public void resetar(){
        openDataBase();
        mDataBase = this.getWritableDatabase();

        String whereClause = "ID=?";
        String[] whereArgs = new String[] { String.valueOf(100) };
        mDataBase.delete("personagem", whereClause, whereArgs);


        mDataBase.close();
    }


//    private boolean copiaBanco(Context context) {
//        try {
//            InputStream inputStream = context.getAssets().open(DataBaseHelper.DB_NAME);
//            String outFile = DataBaseHelper.DB_PATH + DataBaseHelper.DB_NAME;
//            OutputStream outputStream = new FileOutputStream(outFile);
//            byte[] buff = new byte[1024];
//            int legth = 0;
//            while ((legth = inputStream.read(buff))>0){
//                outputStream.write(buff,0,legth);
//            }
//            outputStream.flush();
//            outputStream.close();
//            return true;
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return false;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//    }

}