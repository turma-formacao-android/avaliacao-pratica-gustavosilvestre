package br.com.cast.turmaformacao.agendaprova.model.persistence.socialNetwork;

import android.content.ContentValues;

import br.com.cast.turmaformacao.agendaprova.model.entities.SocialNetwork;

/**
 * Created by Gustavo on 01/10/2015.
 */
public class SocialNetworkContract {

    public static final String TABLE = "socialnetwork";
    public static final String ID = "id";
    public static final String TYPE = "type";
    public static final String NAME = "name";
    public static final String ID_CONTACT = "id_contact";
    public static final String[] COLUNS = {ID,TYPE,NAME,ID_CONTACT};

    private SocialNetworkContract(){
        super();
    }

    public static String getScriptCreateTable(){

        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE "+TABLE);
        create.append(" ( ");
        create.append(ID+" INTEGER PRIMARY KEY, ");
        create.append(TYPE+" TEXT, ");
        create.append(NAME +" TEXT NOT NULL, ");
        create.append(ID_CONTACT+" INTEGER NOT NULL ");
        create.append(" ); ");

        return create.toString();

    }

    public static ContentValues getContentValues(SocialNetwork socialNetwork){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,socialNetwork.getId());
        contentValues.put(TYPE,socialNetwork.getType());
        contentValues.put(NAME,socialNetwork.getName());
        contentValues.put(ID_CONTACT,socialNetwork.getContact().getId());

        return contentValues;
    }
}
