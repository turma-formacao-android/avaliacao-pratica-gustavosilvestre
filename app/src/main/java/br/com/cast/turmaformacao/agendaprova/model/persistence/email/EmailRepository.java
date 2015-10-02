package br.com.cast.turmaformacao.agendaprova.model.persistence.email;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agendaprova.model.entities.Contact;
import br.com.cast.turmaformacao.agendaprova.model.entities.Email;
import br.com.cast.turmaformacao.agendaprova.model.persistence.database.DataBaseHelper;

/**
 * Created by Gustavo on 01/10/2015.
 */
public final class EmailRepository {

    private EmailRepository() {
        super();
    }


    public static void save(Email email) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        ContentValues contentValues = EmailContract.getContentValues(email);

        if (email.getId() == null) {
            long last_key = db.insert(EmailContract.TABLE, null, contentValues);
            email.setId((int) last_key);
        } else {

            String where = EmailContract.ID + " = ? ";
            String[] params = {email.getId().toString()};
            db.update(EmailContract.TABLE, contentValues, where, params);
        }

        dataBaseHelper.close();
        db.close();

    }

    public static List<Email> getEmailByContactId(Integer id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String where = EmailContract.ID_CONTACT + " = ? ";
        String params[] = {id.toString()};
        Cursor cursor = db.query(EmailContract.TABLE, EmailContract.COLUNS, where, params, null, null, EmailContract.EMAIL);

        List<Email> emailList = getEmails(cursor);

        dataBaseHelper.close();
        db.close();

        return emailList;


    }

    public static List<Email> findAll() {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        Cursor cursor = db.query(EmailContract.TABLE, EmailContract.COLUNS, null, null, null, null, EmailContract.EMAIL);

        List<Email> emailList = getEmails(cursor);

        dataBaseHelper.close();
        db.close();

        return emailList;

    }


    public static Email getById(Integer id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String where = EmailContract.ID + " = ? ";
        String params[] = {id.toString()};
        Cursor cursor = db.query(EmailContract.TABLE, EmailContract.COLUNS, where, params, null, null, null);

        Email email = getEmail(cursor);

        return email;

    }

    private static Email getEmail(Cursor cursor) {

        while (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Email email = new Email();
            email.setId(cursor.getInt(cursor.getColumnIndex(EmailContract.ID)));
            email.setEmail(cursor.getString(cursor.getColumnIndex(EmailContract.EMAIL)));
            Integer id_contact = cursor.getInt(cursor.getColumnIndex(EmailContract.ID_CONTACT));
            Contact contact = new Contact();
            contact.setId(id_contact);
            email.setContact(contact);

            return email;

        }

        return null;

    }

    public static List<Email> getEmails(Cursor cursor) {
        List<Email> emailList = new ArrayList<>();

        while (cursor.moveToNext()) {
            emailList.add(getEmail(cursor));
        }

        return emailList;
    }


    public static void delete(Integer id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = EmailContract.ID+" = ? ";
        String params[] = {id.toString()};
        db.delete(EmailContract.TABLE,where,params);

        dataBaseHelper.close();
        db.close();


    }
}
