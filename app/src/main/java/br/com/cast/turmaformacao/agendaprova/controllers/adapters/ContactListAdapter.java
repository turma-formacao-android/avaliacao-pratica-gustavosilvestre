package br.com.cast.turmaformacao.agendaprova.controllers.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import br.com.cast.turmaformacao.agendaprova.model.entities.Contact;

/**
 * Created by Administrador on 01/10/2015.
 */
public class ContactListAdapter extends BaseAdapter{

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
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
