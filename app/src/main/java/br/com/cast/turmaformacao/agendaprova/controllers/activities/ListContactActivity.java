package br.com.cast.turmaformacao.agendaprova.controllers.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.agendaprova.R;
import br.com.cast.turmaformacao.agendaprova.controllers.adapters.ContactListAdapter;
import br.com.cast.turmaformacao.agendaprova.model.entities.Contact;
import br.com.cast.turmaformacao.agendaprova.model.entities.ContatosAndroid;
import br.com.cast.turmaformacao.agendaprova.model.service.ContactBusinessService;


public class ListContactActivity extends AppCompatActivity {

    private ListView listView;
    private ImageButton imageButtonSearchByName;
    private EditText editTextSearchByName;
    private Contact selectContact;
    public static final String PARAM_CONTACT = "PARAM_CONTACT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);
        bindListView();
        bindButtonSearchByName();
        bindEditTextSearchByName();

        ContatosAndroid c = new ContatosAndroid(this);
        List<Contact> lista = c.getContatos();

        ContactBusinessService.saveContactAndroid(lista);
    }

    private void bindEditTextSearchByName() {
        editTextSearchByName = (EditText) findViewById(R.id.editTextSearch);
    }

    private void bindButtonSearchByName() {
        imageButtonSearchByName = (ImageButton) findViewById(R.id.buttonSearchByName);
        imageButtonSearchByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameSearch = editTextSearchByName.getText().toString();
                List<Contact> contactList;
                contactList = nameSearch.isEmpty() ? ContactBusinessService.findAll() : ContactBusinessService.getByName(nameSearch);
                updateList(contactList);

            }
        });
    }

    private void bindListView() {
        listView = (ListView) findViewById(R.id.lv);
        registerForContextMenu(listView);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectContact = (Contact) listView.getAdapter().getItem(position);
                return false;
            }
        });
        updateList(null);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.action_edit:
                onMenuContextEditClick();
                break;
            case R.id.action_delete:
                onMenuContextDeleteClick();
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void onMenuContextDeleteClick() {

        new AlertDialog.Builder(ListContactActivity.this)
                .setTitle("Confirmation")
                .setMessage("Really want to delete this contact?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            ContactBusinessService.delete(selectContact);
                            Toast.makeText(ListContactActivity.this,"Contact delete successfully!",Toast.LENGTH_SHORT).show();
                            updateList(null);
                        } catch (Exception e) {
                            Toast.makeText(ListContactActivity.this,"Error : "+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .setNeutralButton("No", null).show();
    }

    private void onMenuContextEditClick() {
        Intent gotoForm = new Intent(ListContactActivity.this, FormContactActivity.class);
        gotoForm.putExtra(PARAM_CONTACT, selectContact.getId());
        startActivity(gotoForm);
    }

    private void updateList(List<Contact> contactList) {

        List<Contact> all;

        all = contactList == null ? ContactBusinessService.findAll() : contactList;

        listView.setAdapter(new ContactListAdapter(this, all));
        ContactListAdapter adapter = (ContactListAdapter) listView.getAdapter();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            onMenuAddClick();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context_list_contact,menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList(null);
    }

    private void onMenuAddClick() {
        startActivity(new Intent(this,FormContactActivity.class));
    }
}
