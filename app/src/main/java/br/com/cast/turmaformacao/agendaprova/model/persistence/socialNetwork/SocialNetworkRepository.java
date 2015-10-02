package br.com.cast.turmaformacao.agendaprova.model.persistence.socialNetwork;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agendaprova.model.entities.Contact;
import br.com.cast.turmaformacao.agendaprova.model.entities.SocialNetwork;
import br.com.cast.turmaformacao.agendaprova.model.persistence.database.DataBaseHelper;

/**
 * Created by Gustavo on 01/10/2015.
 */
public final class SocialNetworkRepository {

    private SocialNetworkRepository(){
        super();
    }

    public static void delete(Integer id){

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();


        String where = SocialNetworkContract.ID+ " = ? ";
        String params[] = {id.toString()};

        db.delete(SocialNetworkContract.TABLE,where,params);


        dataBaseHelper.close();
        db.close();


    }

    public static SocialNetwork getSocialNetwork(Cursor cursor){


        while(!cursor.isBeforeFirst() || cursor.moveToNext()){
            SocialNetwork socialNetwork = new SocialNetwork();
            socialNetwork.setId(cursor.getInt(cursor.getColumnIndex(SocialNetworkContract.ID)));
            socialNetwork.setType(cursor.getString(cursor.getColumnIndex(SocialNetworkContract.TYPE)));
            socialNetwork.setName(cursor.getString(cursor.getColumnIndex(SocialNetworkContract.NAME)));
            Contact contact = new Contact();
            contact.setId((cursor.getInt(cursor.getColumnIndex(SocialNetworkContract.ID_CONTACT))));
            socialNetwork.setContact(contact);
            return socialNetwork;
        }

        return null;
    }

    public static List<SocialNetwork> getSocialNetworks(Cursor cursor){

        List<SocialNetwork> socialNetworkList = new ArrayList<>();

        while(cursor.moveToNext()){
            socialNetworkList.add(getSocialNetwork(cursor));
        }

        return socialNetworkList;
    }

    public static List<SocialNetwork> findAll(){
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        Cursor cursor = db.query(SocialNetworkContract.TABLE, SocialNetworkContract.COLUNS, null, null, null, null, SocialNetworkContract.NAME);

        List<SocialNetwork> socialNetworkList = getSocialNetworks(cursor);

        cursor.close();
        dataBaseHelper.close();
        db.close();

        return socialNetworkList;

    }

    public static List<SocialNetwork> getSocialNetworkByContactId(Integer id){

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String where = SocialNetworkContract.ID_CONTACT +" = ? ";
        String params[] = {id.toString()};

        Cursor cursor = db.query(SocialNetworkContract.TABLE, SocialNetworkContract.COLUNS, where, params, null, null, SocialNetworkContract.NAME);

        List<SocialNetwork> socialNetworkList = getSocialNetworks(cursor);

        cursor.close();
        dataBaseHelper.close();
        db.close();

        return socialNetworkList;
    }

    public static void save(SocialNetwork socialNetwork) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = SocialNetworkContract.getContentValues(socialNetwork);

        if (socialNetwork.getId() == null) {
            long last_id = db.insert(SocialNetworkContract.TABLE, null, contentValues);
            socialNetwork.setId((int) last_id);
        } else {
            String where = SocialNetworkContract.ID + " = ? ";
            String params[] = {socialNetwork.getId().toString()};
            db.update(SocialNetworkContract.TABLE, contentValues, where, params);
        }

        dataBaseHelper.close();
        db.close();

    }
}
