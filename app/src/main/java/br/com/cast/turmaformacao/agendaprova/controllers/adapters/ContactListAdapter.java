package br.com.cast.turmaformacao.agendaprova.controllers.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.agendaprova.R;
import br.com.cast.turmaformacao.agendaprova.model.entities.Contact;

/**
 * Created by Administrador on 01/10/2015.
 */
public class ContactListAdapter extends BaseAdapter {


    private Activity context;

    public ContactListAdapter(Activity context, List<Contact> list) {
        this.context = context;
        this.list = list;
    }

    private List<Contact> list;


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Contact getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int postion, View view, ViewGroup viewGroup) {

        Contact contact = getItem(postion);

        View v = context.getLayoutInflater().inflate(R.layout.list_item_contact, viewGroup, false);

        TextView name = (TextView) v.findViewById(R.id.list_item_name);
        name.setText(contact.getName());

        TextView street = (TextView) v.findViewById(R.id.list_item_street);
        street.setText(contact.getAddress().getStreet());

        TextView number = (TextView) v.findViewById(R.id.list_item_number);
        number.setText(contact.getAddress().getNumber());

        TextView type_telephone = (TextView) v.findViewById(R.id.list_item_type_telephone);
        type_telephone.setText(contact.getTelephonesList().size() == 0 ? "" : contact.getTelephonesList().get(0).getType());

        TextView telephone = (TextView) v.findViewById(R.id.list_item_telephone);
        telephone.setText(contact.getTelephonesList().size() == 0 ? "" : contact.getTelephonesList().get(0).getNumber());

        TextView email = (TextView) v.findViewById(R.id.list_item_email);
        email.setText(contact.getEmailList().size() == 0 ?  "" : contact.getEmailList().get(0).getEmail());


        return v;
    }
}
