package br.com.cast.turmaformacao.agendaprova.controllers.aSyncTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import br.com.cast.turmaformacao.agendaprova.model.entities.Address;
import br.com.cast.turmaformacao.agendaprova.model.http.AddressService;

/**
 * Created by Administrador on 01/10/2015.
 */
public final class AddressServiceTask extends AsyncTask<String,Void,Address>{

    Activity activity;
    AddressInterfaceTask addressInterface;
    ProgressDialog progressDialog;

    public AddressServiceTask(Activity activity,AddressInterfaceTask addressInterface){
        this.activity = activity;
        this.addressInterface = addressInterface;
        progressDialog = new ProgressDialog(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Searching address by zipcode...");
    }

    @Override
    protected Address doInBackground(String... strings) {

        Address address = AddressService.getAdressByZipCode(strings[0]);
        return address;
    }

    @Override
    protected void onPostExecute(Address address) {
        super.onPostExecute(address);
        addressInterface.afterSearchZipCode(address);
    }
}
