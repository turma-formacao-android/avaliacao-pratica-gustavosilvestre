package br.com.cast.turmaformacao.agendaprova.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import br.com.cast.turmaformacao.agendaprova.R;
import br.com.cast.turmaformacao.agendaprova.controllers.aSyncTask.AddressInterfaceTask;
import br.com.cast.turmaformacao.agendaprova.controllers.aSyncTask.AddressServiceTask;
import br.com.cast.turmaformacao.agendaprova.model.entities.Address;
import br.com.cast.turmaformacao.agendaprova.model.entities.Contact;
import br.com.cast.turmaformacao.agendaprova.util.FormerHelper;

/**
 * Created by Administrador on 01/10/2015.
 */
public class FormContactActivity extends AppCompatActivity implements AddressInterfaceTask {

    private EditText editTextName;
    private EditText editTextZipCode;
    private EditText editTextStreet;
    private EditText editTextNumber;
    private EditText editTextNeighborhood;
    private EditText editTextCity;
    private EditText editTextComplement;
    private EditText editTextState;
    private Contact contact;
    private ImageButton buttonSearch;
    private static final String PARAM_CONTACT = "PARAM_CONTACT";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_contact);

        init();
        bindEditTextName();
        bindEditTextZipCode();
        bindEditTextStreet();
        bindEditTextNumber();
        bindEditTextComplement();
        bindEditTextNeighborhood();
        bindEditTextCity();
        bindEditTextState();
        bindButtonSearch();

    }

    private void bindButtonSearch() {
        buttonSearch = (ImageButton) findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String zipcode = editTextZipCode.getText().toString();
                new AddressServiceTask(FormContactActivity.this, FormContactActivity.this).execute(zipcode);
            }
        });
    }

    public void init() {
        Bundle extras = getIntent().getExtras();
        Contact contact = null;

        if (extras != null) {
            contact = extras.getParcelable(PARAM_CONTACT);
        }

        this.contact = contact == null ? new Contact() : contact;
    }

    private void bindEditTextState() {
        editTextState = (EditText) findViewById(R.id.contact_form_state);
        editTextState.setText(contact.getAddress().getState() == null ? "" : contact.getAddress().getState());
    }

    private void bindEditTextCity() {
        editTextCity = (EditText) findViewById(R.id.contact_form_city);
        editTextCity.setText(contact.getAddress().getCity() == null ? "" : contact.getAddress().getCity());
    }

    private void bindEditTextNeighborhood() {
        editTextNeighborhood = (EditText) findViewById(R.id.contact_form_neighborhood);
        editTextNeighborhood.setText(contact.getAddress().getNeighborhood() == null ? "" : contact.getAddress().getNeighborhood());
    }

    private void bindEditTextComplement() {
        editTextComplement = (EditText) findViewById(R.id.contact_form_complement);
        editTextComplement.setText(contact.getAddress().getComplement() == null ? "" : contact.getAddress().getComplement());
    }

    private void bindEditTextNumber() {
        editTextNumber = (EditText) findViewById(R.id.contact_form_number);
        editTextNumber.setText(contact.getAddress().getNumber() == null ? "" : contact.getAddress().getNumber());
    }

    private void bindEditTextStreet() {
        editTextStreet = (EditText) findViewById(R.id.contact_form_street);
        editTextStreet.setText(contact.getAddress().getStreet() == null ? "" : contact.getAddress().getStreet());
    }

    private void bindEditTextZipCode() {
        editTextZipCode = (EditText) findViewById(R.id.contact_form_zipcode);
        editTextZipCode.setText(contact.getAddress().getZipCode() == null ? "" : contact.getAddress().getZipCode());
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.contact_form_name);
        editTextName.setText(contact.getName() == null ? "" : contact.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void afterSearchZipCode(Address address) {

        if (address == null) {
            Toast.makeText(this, "Cep não encontrado", Toast.LENGTH_SHORT).show();
            FormerHelper.clearEditTextFields(editTextCity, editTextComplement, editTextNeighborhood, editTextNumber, editTextState, editTextStreet, editTextZipCode);
            return;
        }

        contact.setAddress(address);
        editTextZipCode.setText(address.getZipCode());
        editTextStreet.setText(address.getStreet());
        editTextComplement.setText(address.getComplement());
        editTextNumber.setText(address.getNumber());
        editTextCity.setText(address.getCity());
        editTextState.setText(address.getState());
        editTextNeighborhood.setText(address.getNeighborhood());

    }
}
