package br.com.cast.turmaformacao.agendaprova.model.persistence.contact;

import android.content.ContentValues;

import br.com.cast.turmaformacao.agendaprova.model.entities.Contact;

/**
 * Created by Administrador on 01/10/2015.
 */
public final class ContactContract {

    public final static String TABLE = "contact";
    public final static String ID = "id";
    public final static String NAME = "name";
    public final static String ZIPE_CODE = "zipcode";
    public final static String STREET = "street";
    public final static String NUMBER = "number";
    public final static String COMPLEMENT = "complement";
    public final static String NEIGHBORHOOD = "neighborhood";
    public final static String CITY = "city";
    public final static String STATE = "state";

    public final static String[] COLUNS = {ID, NAME, ZIPE_CODE,STREET, NUMBER, COMPLEMENT,NEIGHBORHOOD, CITY, STATE};

    public static String getScriptCreateTable(){

       final StringBuilder create = new StringBuilder();
        create.append(" CREATE TABLE "+TABLE);
        create.append("( ");
        create.append(ID+" INTEGER PRIMARY KEY, ");
        create.append(NAME+" TEXT NOT NULL, ");
        create.append(ZIPE_CODE+" TEXT, ");
        create.append(STREET+" TEXT, ");
        create.append(NUMBER+" TEXT, ");
        create.append(COMPLEMENT+" TEXT, ");
        create.append(NEIGHBORHOOD+" TEXT,");
        create.append(CITY+" TEXT ,");
        create.append(STATE+" TEXT ");
        create.append(" ) ");

        return create.toString();

    }

    public static ContentValues getContentValues(Contact contact){

        ContentValues contentValues = new ContentValues();

        contentValues.put(ID,contact.getId());
        contentValues.put(NAME,contact.getName());
        contentValues.put(ZIPE_CODE,contact.getAddress().getZipCode());
        contentValues.put(STREET,contact.getAddress().getStreet());
        contentValues.put(NUMBER,contact.getAddress().getNumber());
        contentValues.put(COMPLEMENT,contact.getAddress().getComplement());
        contentValues.put(NEIGHBORHOOD,contact.getAddress().getNeighborhood());
        contentValues.put(CITY,contact.getAddress().getCity());
        contentValues.put(STATE,contact.getAddress().getState());

       return contentValues;

    }



    private ContactContract() {
        super();
    }


}
