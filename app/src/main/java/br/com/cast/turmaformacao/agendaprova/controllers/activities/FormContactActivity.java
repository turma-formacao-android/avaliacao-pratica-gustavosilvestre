package br.com.cast.turmaformacao.agendaprova.controllers.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.agendaprova.R;
import br.com.cast.turmaformacao.agendaprova.controllers.aSyncTask.AddressInterfaceTask;
import br.com.cast.turmaformacao.agendaprova.controllers.aSyncTask.AddressServiceTask;
import br.com.cast.turmaformacao.agendaprova.model.entities.Address;
import br.com.cast.turmaformacao.agendaprova.model.entities.Contact;
import br.com.cast.turmaformacao.agendaprova.model.entities.Email;
import br.com.cast.turmaformacao.agendaprova.model.entities.SocialNetwork;
import br.com.cast.turmaformacao.agendaprova.model.entities.Telephone;
import br.com.cast.turmaformacao.agendaprova.model.service.ContactBusinessService;
import br.com.cast.turmaformacao.agendaprova.model.service.TelephoneBusinessService;
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
    private ImageButton buttonSearchZipCode;
    private ImageButton buttonAddTelephone;
    private ImageButton buttonDeleteTelephone;
    private Spinner spinnerTelephone;

    private ImageButton buttonAddEmail;
    private ImageButton buttonDeleteEmail;
    private Spinner spinnerEmail;

    private ImageButton buttonAddSocialNetwork;
    private ImageButton buttonDeleteSocialNetwork;
    private Spinner spinnerSocialNetwork;

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
        bindButtonSearchZipCode();
        bindSpinnerTelephone();
        bindButtonAddTelephone();
        bindButtonDeleteTelephone();
        bindButtonAddEmail();
        bindButtonDeleteEmail();
        bindButtonAddSocialNetwork();
        bindButtonDeleteSocialNetwork();
        bindSpinnerEmail();
        bindSpinnerSocialNetwork();

    }

    private void bindSpinnerSocialNetwork() {
        spinnerSocialNetwork = (Spinner) findViewById(R.id.spinner_socialNetwork);
    }

    private void bindSpinnerEmail() {
        spinnerEmail = (Spinner) findViewById(R.id.spinner_email);
    }

    private void bindButtonDeleteSocialNetwork() {
        buttonDeleteSocialNetwork = (ImageButton) findViewById(R.id.buttonDeleteSocialNetwork);
    }

    private void bindButtonAddSocialNetwork() {
        buttonAddSocialNetwork = (ImageButton) findViewById(R.id.buttonAddSocialNetwork);
        buttonAddSocialNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FormContactActivity.this);
                builder.setTitle("Add Social Network");
                final EditText socialNetworkEditText = new EditText(FormContactActivity.this);
                socialNetworkEditText.setHint("Social Network");
                LinearLayout lay = new LinearLayout(FormContactActivity.this);
                lay.setOrientation(LinearLayout.VERTICAL);
                lay.addView(socialNetworkEditText);
                builder.setView(lay);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SocialNetwork socialNetwork = new SocialNetwork();
                        socialNetwork.setName(socialNetworkEditText.getText().toString());
                        contact.getSocialNetworksList().add(socialNetwork);
                        onUpdateSpinnerSocialNetwork();
                    }
                }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();
            }
        });
    }

    private void bindButtonDeleteEmail() {
        buttonDeleteEmail = (ImageButton) findViewById(R.id.buttonDeleteEmail);
        buttonDeleteEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(FormContactActivity.this)
                        .setTitle("Confirmation")
                        .setMessage("Really want to delete this email?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int selectedItemPosition = spinnerEmail.getSelectedItemPosition();
                                contact.getEmailList().remove(selectedItemPosition);
                                onUpdateSpinnerEmail();
                            }
                        }).setNeutralButton("No",null).show();
            }
        });
    }

    private void bindButtonAddEmail() {
        buttonAddEmail = (ImageButton) findViewById(R.id.buttonAddEmail);
        buttonAddEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FormContactActivity.this);

                builder.setTitle("Add Email");

                final EditText emailEditText = new EditText(FormContactActivity.this);
                emailEditText.setHint("Email");
                LinearLayout lay = new LinearLayout(FormContactActivity.this);
                lay.setOrientation(LinearLayout.VERTICAL);
                lay.addView(emailEditText);
                builder.setView(lay);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Email email = new Email();
                        email.setEmail(emailEditText.getText().toString());
                        contact.getEmailList().add(email);
                        onUpdateSpinnerEmail();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

    }

    private void bindButtonDeleteTelephone() {
        buttonDeleteTelephone = (ImageButton) findViewById(R.id.buttonDeleteTelephone);
        buttonDeleteTelephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Telephone selectedItem = (Telephone) spinnerTelephone.getSelectedItem();

                new AlertDialog.Builder(FormContactActivity.this)
                        .setTitle("Confirmation")
                        .setMessage("Really want to delete this telephone?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                contact.getTelephonesList().remove(selectedItem);
                                TelephoneBusinessService.delete(selectedItem);
                                onUpdateSpinnerTelephone();
                            }
                        }).setNeutralButton("No", null).show();

            }
        });
    }

    private void bindButtonAddTelephone() {
        buttonAddTelephone = (ImageButton) findViewById(R.id.buttonAddTelephone);
        buttonAddTelephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = getLayoutInflater().inflate(R.layout.layout_add_telephone, null);
                final EditText editTextTypeTelephone = (EditText) view.findViewById(R.id.typeTelephone);
                final EditText editTextTelephone = (EditText) view.findViewById(R.id.telephone);
                new AlertDialog.Builder(FormContactActivity.this)
                        .setTitle("Add Telephone")
                        .setView(view)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Telephone telephone = new Telephone();
                                telephone.setType(editTextTypeTelephone.getText().toString());
                                telephone.setNumber(editTextTelephone.getText().toString());
                                contact.getTelephonesList().add(telephone);
                                onUpdateSpinnerTelephone();
                            }
                        }).setNeutralButton("Cancel", null).show();
            }
        });

    }

    private void onUpdateSpinnerSocialNetwork(){
        List<SocialNetwork> socialNetworkList = contact.getSocialNetworksList();
        ArrayAdapter<SocialNetwork> adapter = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,socialNetworkList);
        spinnerSocialNetwork.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        buttonDeleteSocialNetwork.setEnabled(socialNetworkList.size() > 0);
    }

    private void onUpdateSpinnerEmail(){
        List<Email> emailList = contact.getEmailList();
        ArrayAdapter<Email> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,emailList);
        spinnerEmail.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        buttonDeleteEmail.setEnabled(emailList.size() > 0);
    }

    private void onUpdateSpinnerTelephone() {

        List<Telephone> telephonesList = contact.getTelephonesList();
        ArrayAdapter<Telephone> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, telephonesList);
        spinnerTelephone.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        buttonDeleteTelephone.setEnabled(telephonesList.size() > 0);
    }

    private void bindSpinnerTelephone() {
        spinnerTelephone = (Spinner) findViewById(R.id.spinner_telephone);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onUpdateSpinnerTelephone();
        onUpdateSpinnerEmail();
        onUpdateSpinnerSocialNetwork();
    }

    private void bindButtonSearchZipCode() {
        buttonSearchZipCode = (ImageButton) findViewById(R.id.buttonSearchZipCode);
        buttonSearchZipCode.setOnClickListener(new View.OnClickListener() {
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
            contact = extras.getParcelable(ListContactActivity.PARAM_CONTACT);
        }

        this.contact = contact == null ? new Contact() : ContactBusinessService.getDependencies(contact);

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
        getMenuInflater().inflate(R.menu.menu_form_contact, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.action_done:
                onMenuDoneClick();
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuDoneClick() {
        bindContact();
        ContactBusinessService.save(contact);
        this.finish();
    }

    private void bindContact() {
        contact.setName(editTextName.getText().toString());
        bindAddress();
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
        editTextCity.setText(address.getCity());
        editTextState.setText(address.getState());
        editTextNeighborhood.setText(address.getNeighborhood());
        editTextNumber.requestFocus();

    }

    private void bindAddress() {

        contact.getAddress().setNumber(editTextNumber.getText().toString());
        contact.getAddress().setCity(editTextCity.getText().toString());
        contact.getAddress().setComplement(editTextComplement.getText().toString());
        contact.getAddress().setNeighborhood(editTextNeighborhood.getText().toString());
        contact.getAddress().setState(editTextState.getText().toString());
        contact.getAddress().setZipCode(editTextZipCode.getText().toString());
        contact.getAddress().setStreet(editTextStreet.getText().toString());

    }
}
