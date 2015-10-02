package br.com.cast.turmaformacao.agendaprova.model.persistence.telephone;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agendaprova.model.entities.Contact;
import br.com.cast.turmaformacao.agendaprova.model.entities.Telephone;
import br.com.cast.turmaformacao.agendaprova.model.persistence.contact.ContactContract;
import br.com.cast.turmaformacao.agendaprova.model.persistence.database.DataBaseHelper;

/**
 * Created by Gustavo on 01/10/2015.
 */
public final class TelephoneRepostitory {

    private TelephoneRepostitory() {
        super();
    }

    public static void save(Telephone telephone) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = TelephoneContract.getContentValues(telephone);

        if (telephone.getId() == null) {
            long last_id = db.insert(TelephoneContract.TABLE, null, contentValues);
            telephone.setId((int) last_id);
        } else {
            String where = TelephoneContract.ID + " = ? ";
            String params[] = {telephone.getId().toString()};
            db.update(TelephoneContract.TABLE, contentValues, where, params);
        }

        dataBaseHelper.close();
        db.close();

    }

    public static List<Telephone> getTelephoneByContactId(Integer id){

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();


        String where = TelephoneContract.ID_CONTACT+" = ? ";
        String params[] = {id.toString()};

        Cursor cursor = db.query(TelephoneContract.TABLE, TelephoneContract.COLUNS, where, params, null, null, TelephoneContract.TYPE);

        List<Telephone> telephoneList = getTelephones(cursor);

        cursor.close();
        dataBaseHelper.close();
        db.close();

        return telephoneList;

    }

    private static Telephone getTelephone(Cursor cursor) {

        while (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Telephone telephone = new Telephone();
            telephone.setId(cursor.getInt(cursor.getColumnIndex(ContactContract.ID)));
            telephone.setType(cursor.getString(cursor.getColumnIndex(TelephoneContract.TYPE)));
            telephone.setNumber(cursor.getString(cursor.getColumnIndex(TelephoneContract.NUMBER)));
            Contact contact = new Contact();
            contact.setId(cursor.getInt(cursor.getColumnIndex(TelephoneContract.ID_CONTACT)));
            telephone.setContact(contact);
            return telephone;
        }

        return null;

    }


    private static List<Telephone> getTelephones(Cursor cursor) {
        List<Telephone> telephoneList = new ArrayList<>();

        while (cursor.moveToNext()) {
            telephoneList.add(getTelephone(cursor));
        }


        return telephoneList;
    }

    public static List<Telephone> findAll() {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();


        Cursor cursor = db.query(ContactContract.TABLE, TelephoneContract.COLUNS, null, null, null, null, TelephoneContract.TYPE);

        List<Telephone> telephoneList = getTelephones(cursor);

        cursor.close();
        dataBaseHelper.close();
        db.close();

        return telephoneList;


    }

    public static void delete(Integer id) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();


        String where = TelephoneContract.ID+ " = ? ";
        String params[] = {id.toString()};

        db.delete(TelephoneContract.TABLE,where,params);


        dataBaseHelper.close();
        db.close();

    }
}
