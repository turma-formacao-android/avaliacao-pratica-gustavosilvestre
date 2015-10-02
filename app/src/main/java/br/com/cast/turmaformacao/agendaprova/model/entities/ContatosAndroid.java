package br.com.cast.turmaformacao.agendaprova.model.entities;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 02/10/2015.
 */
public class ContatosAndroid {

    private Context ctx;

    public ContatosAndroid(Context contexto) {
        this.ctx = contexto;
    }

    public List<Contact> getContatos() {

        Cursor cursor = this.ctx.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.Contacts.DISPLAY_NAME);
        //pega os index das colunnas
        int IndexID = cursor.getColumnIndex(ContactsContract.Contacts._ID);
        int IndexTemTelefone = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
        int indexTelefone = cursor.getColumnIndex(ContactsContract.PhoneLookup.NUMBER);
        int indexTypeTelefone = cursor.getColumnIndex(ContactsContract.PhoneLookup.TYPE);
        int IndexName = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);

        List<Contact> Contatos = new ArrayList<Contact>();
        Contact contact;
        while (cursor.moveToNext()) {
            contact = new Contact();
            contact.setName(cursor.getString(IndexName));

            if (IndexTemTelefone > 0) {
                Cursor phones = ctx.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                + " = " + IndexID, null, null);

                while (phones.moveToNext()) {
                    String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    String phoneType = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                    Telephone telephone = new Telephone();
                    telephone.setNumber(phoneNumber);
                    telephone.setType(phoneType);
                    contact.getTelephonesList().add(telephone);
                }
            }


            Contatos.add(contact);
        }
        cursor.close();

        return Contatos;
    }
}



