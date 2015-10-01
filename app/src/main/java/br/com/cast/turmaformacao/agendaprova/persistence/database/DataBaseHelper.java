package br.com.cast.turmaformacao.agendaprova.persistence.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.cast.turmaformacao.agendaprova.util.ApplicationUtil;

/**
 * Created by Administrador on 01/10/2015.
 */
public final class DataBaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "agendadb";
    private static final int DATABASE_VERSION = 1;


    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }


    public static DataBaseHelper getInstance(){
        return new DataBaseHelper(ApplicationUtil.getContext());
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
