package br.com.cast.turmaformacao.agendaprova.model.persistence.email;

import android.content.ContentValues;

import br.com.cast.turmaformacao.agendaprova.model.entities.Email;

/**
 * Created by Gustavo on 01/10/2015.
 */
public final class EmailContract {

    public static final String TABLE = "email";
    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String ID_CONTACT = "id_contact";

    public static final String[] COLUNS = {ID,EMAIL,ID_CONTACT};

    private EmailContract(){
        super();
    }

    public static String getScriptCreateTable(){
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE "+TABLE);
        create.append(" ( ");
        create.append(ID+" INTEGER PRIMARY KEY, ");
        create.append(EMAIL+" TEXT NOT NULL, ");
        create.append(ID_CONTACT+" INTEGER NOT NULL ");
        create.append(" ); ");

        return create.toString();

    }

    public static ContentValues getContentValues(Email email){
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID,email.getId());
        contentValues.put(EMAIL,email.getEmail());
        contentValues.put(ID_CONTACT,email.getContact().getId());

        return contentValues;
    }




}
