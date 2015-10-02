package br.com.cast.turmaformacao.agendaprova.model.persistence.telephone;

import android.content.ContentValues;

import br.com.cast.turmaformacao.agendaprova.model.entities.Telephone;

/**
 * Created by Gustavo on 01/10/2015.
 */
public final class TelephoneContract {

    public static final String TABLE = "telephone";
    public static final String ID = "id";
    public static final String NUMBER = "number";
    public static final String TYPE = "type";
    public static final String ID_CONTACT = "id_contact" ;

    public static final String[] COLUNS = {ID, NUMBER,TYPE,ID_CONTACT};

    private TelephoneContract(){
        super();
    }

    public static String getScriptCreateTable(){
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE "+TABLE);
        create.append(" ( ");
        create.append(ID+" INTEGER PRIMARY KEY, ");
        create.append(TYPE+" TEXT NOT NULL, ");
        create.append(NUMBER +" TEXT NOT NULL, ");
        create.append(ID_CONTACT+" INTEGER NOT NULL ");
        create.append(" ); ");

        return create.toString();

    }


    public static ContentValues getContentValues(Telephone telephone){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,telephone.getId());
        contentValues.put(NUMBER,telephone.getNumber());
        contentValues.put(TYPE,telephone.getType());
        contentValues.put(ID_CONTACT, telephone.getContact().getId());

        return contentValues;

    }


}
