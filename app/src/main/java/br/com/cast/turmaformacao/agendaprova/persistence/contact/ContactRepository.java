package br.com.cast.turmaformacao.agendaprova.persistence.contact;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agendaprova.model.entities.Contact;
import br.com.cast.turmaformacao.agendaprova.persistence.database.DataBaseHelper;

/**
 * Created by Administrador on 01/10/2015.
 */
public final class ContactRepository {

    private ContactRepository() {
        super();
    }


    public static void save(Contact contact) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = ContactContract.getContentValues(contact);

        if (contact.getId() == null) {

            db.insert(ContactContract.TABLE, null, contentValues);
        } else {
            String where = ContactContract.ID + " = ? ";
            String params[] = {contact.getId().toString()};
            db.update(ContactContract.TABLE, contentValues, where, params);
        }


        dataBaseHelper.close();
        db.close();
    }

    public static Contact getById(Integer id){

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = ContactContract.ID+" = ? ";
        String params[] = {id.toString()};

        Cursor cursor = db.query(ContactContract.TABLE, ContactContract.COLUNS, where, params, null, null, null);

        Contact contact = getContact(cursor);

        dataBaseHelper.close();
        db.close();

        return contact;

    }

    public static List<Contact> getContacts(Cursor cursor){

        List<Contact> contacts = new ArrayList<>();

        while(cursor.moveToNext()){
            contacts.add(getContact(cursor));
        }

        return contacts;
    }

    private static Contact getContact(Cursor cursor) {


        while(!cursor.isBeforeFirst() || cursor.moveToNext()){
            Contact contact = new Contact();
            contact.setId(cursor.getInt(cursor.getColumnIndex(ContactContract.ID)));
            contact.setName(cursor.getString(cursor.getColumnIndex(ContactContract.NAME)));
            contact.getAddress().setZipCode(cursor.getString(cursor.getColumnIndex(ContactContract.ZIPE_CODE)));
            contact.getAddress().setStreet(cursor.getString(cursor.getColumnIndex(ContactContract.STREET)));
            contact.getAddress().setNumber(cursor.getString(cursor.getColumnIndex(ContactContract.NUMBER)));
            contact.getAddress().setComplement(cursor.getString(cursor.getColumnIndex(ContactContract.COMPLEMENT)));
            contact.getAddress().setNeighborhood(cursor.getString(cursor.getColumnIndex(ContactContract.NEIGHBORHOOD)));
            contact.getAddress().setCity(cursor.getString(cursor.getColumnIndex(ContactContract.CITY)));
            contact.getAddress().setState(cursor.getString(cursor.getColumnIndex(ContactContract.STATE)));

            return contact;
        }
        return null;
    }
}
